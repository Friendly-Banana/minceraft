package me.banana.minceraft.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.banana.minceraft.Minceraft;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    private static final float[] duck = {4.932F, 4.444F, 0.624F};

    private float hue = 0;

    @Shadow
    @Final
    private boolean fading;
    @Shadow
    private long fadeInStart;

    protected TitleScreenMixin(Component component) {
        super(component);
    }

    @ModifyConstant(method = "<init>(Z)V", constant = @Constant(doubleValue = 1.0E-4))
    private double chance(double constant) {
        return Minceraft.config.minceraftChance;
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V", ordinal = 1, shift = At.Shift.AFTER))
    private void activateRGB(PoseStack poseStack, int mouseX, int mouseY, float tickDelta, CallbackInfo ci) {
        if (Minceraft.config.rgbSpeed != 0) {
            hue += Minceraft.config.rgbSpeed * tickDelta;
            // don't let it get too high
            hue %= 100;
            float[] rgb = Minceraft.HsvToRgb(hue, 1, 1);
            RenderSystem.setShaderColor(rgb[0], rgb[1], rgb[2], 1);
        }
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V", ordinal = 2, shift = At.Shift.BEFORE))
    private void deactivateRGB(PoseStack poseStack, int mouseX, int mouseY, float tickDelta, CallbackInfo ci) {
        if (Minceraft.config.rgbSpeed != 0) {
            float f = this.fading ? (float) (Util.getMillis() - fadeInStart) / 1000.0F : 1.0F;
            float f1 = fading ? Mth.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f1);
        }
    }
}
