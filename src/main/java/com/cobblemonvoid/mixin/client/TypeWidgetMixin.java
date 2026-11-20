package com.cobblemonvoid.mixin.client;

import static com.cobblemon.mod.common.api.gui.GuiUtilsKt.blitk;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.client.gui.summary.widgets.type.TypeWidget;
import com.cobblemonvoid.CosmicLightTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Ported from Lost Lore's TypeWidgetMixin (decompiled): summary-screen type icon. */
@Mixin(TypeWidget.class)
public abstract class TypeWidgetMixin {
    @Inject(method = "renderType", at = @At("HEAD"), cancellable = true)
    private void cobblemonvoid$renderType(ElementalType type, PoseStack ms, int x, int y, CallbackInfo ci) {
        if (!CosmicLightTypes.isCustom(type)) return;
        ci.cancel();
        ResourceLocation tex = ResourceLocation.fromNamespaceAndPath("mega_showdown",
                "textures/gui/types/" + type.getShowdownId().toLowerCase() + ".png");
        float s = 36.0f;
        blitk(ms, tex, (double) (x * 2) + 0.5d, y * 2, s, s, s, 0, s, s, 0, 1, 1, 1, 1, true, 0.5f);
    }
}
