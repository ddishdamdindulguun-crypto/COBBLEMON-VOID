package com.cobblemonvoid.mixin.client;

import static com.cobblemon.mod.common.api.gui.GuiUtilsKt.blitk;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.client.gui.TypeIcon;
import com.cobblemonvoid.CosmicLightTypes;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Ported from Lost Lore's TypeIconMixin (decompiled). Only takes over when Cosmic/Light is involved. */
@Mixin(TypeIcon.class)
public abstract class TypeIconMixin {
    @Shadow private static ResourceLocation typesResource;
    @Shadow private static ResourceLocation smallTypesResource;
    @Shadow private Number x;
    @Shadow private Number y;
    @Shadow private ElementalType type;
    @Shadow private ElementalType secondaryType;
    @Shadow private boolean centeredX;
    @Shadow private boolean small;
    @Shadow private float secondaryOffset;
    @Shadow private float doubleCenteredOffset;
    @Shadow private float opacity;

    private static ResourceLocation cobblemonvoid$tex(ElementalType t, boolean small) {
        return ResourceLocation.fromNamespaceAndPath("mega_showdown",
                "textures/gui/types/" + t.getShowdownId().toLowerCase() + (small ? "_small" : "") + ".png");
    }

    @Inject(method = "render(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At("HEAD"), cancellable = true)
    private void cobblemonvoid$render(GuiGraphics ctx, CallbackInfo ci) {
        if (!CosmicLightTypes.isCustom(type) && !CosmicLightTypes.isCustom(secondaryType)) return;
        ci.cancel();

        float s = small ? 18.0f : 36.0f;
        float off = centeredX ? (s / 2.0f * 0.5f + (secondaryType != null ? doubleCenteredOffset : 0.0f)) : 0.0f;

        if (secondaryType != null) {
            float sx = (x.floatValue() + secondaryOffset - off) / 0.5f;
            float sy = y.floatValue() / 0.5f;
            if (CosmicLightTypes.isCustom(secondaryType)) {
                blitk(ctx.pose(), cobblemonvoid$tex(secondaryType, small), sx, sy, s, s, s, 0, s, s, 0, 1, 1, 1,
                        opacity, true, 0.5f);
            } else {
                blitk(ctx.pose(), small ? smallTypesResource : typesResource, sx, sy, s, s,
                        (double) (s * secondaryType.getTextureXMultiplier()) + 0.1d, 0, s * 18.0f, s, 0, 1, 1, 1,
                        opacity, true, 0.5f);
            }
        }

        float px = (x.floatValue() - off) / 0.5f;
        float py = y.floatValue() / 0.5f;
        if (CosmicLightTypes.isCustom(type)) {
            blitk(ctx.pose(), cobblemonvoid$tex(type, small), px, py, s, s, s, 0, s, s, 0, 1, 1, 1,
                    opacity, true, 0.5f);
        } else {
            blitk(ctx.pose(), small ? smallTypesResource : typesResource, px, py, s, s,
                    s * type.getTextureXMultiplier() + 0.1f, 0.0f, s * 18.0f, s, 0.0f, 1.0f, 1.0f, 1.0f,
                    opacity, true, 0.5f);
        }
    }
}
