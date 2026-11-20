package com.cobblemonvoid.mixin;

import com.cobblemonvoid.CosmicLightTypes;
import com.github.yajatkaul.mega_showdown.MegaShowdown;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MegaShowdown.class)
public class MegaShowdownMixin {
    @Inject(method = "init()V", at = @At("TAIL"), remap = false)
    private static void cosmiclight$init(CallbackInfo ci) {
        CosmicLightTypes.register();
    }
}
