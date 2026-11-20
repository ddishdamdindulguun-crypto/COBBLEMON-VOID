package com.cobblemonvoid;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.api.types.tera.elemental.ElementalTypeTeraType;
import com.cobblemon.mod.common.battles.runner.graal.GraalShowdownService;
import com.cobblemon.mod.common.Cobblemon;
import com.github.yajatkaul.mega_showdown.mixin.TeraTypesAccessor;
import net.minecraft.network.chat.Component;
import static com.cobblemon.mod.common.util.MiscUtilsKt.cobblemonResource;

/** Mirrors what Lost Lore's CustomTypeRegistryImpl does (decompiled). Names are Mojang-mapped here. */
public final class CosmicLightTypes {
    public static ElementalType COSMIC, LIGHT;

    public static void register() {
        COSMIC = create("Cosmic", "cobblemon.type.cosmic", 0x5B3FA0, "cosmic", "Max Starfall", "Astral Burst");
        LIGHT  = create("Light",  "cobblemon.type.light",  0xFFE066, "light",  "Max Radiance", "Radiant Flash");
    }

    static ElementalType create(String name, String langKey, int color, String id, String gmaxA, String gmaxB) {
        ElementalType t = ElementalTypes.register(name, Component.translatable(langKey), color, 0, 0, 0, 1000);
        ElementalTypeTeraType tera = new ElementalTypeTeraType(t);
        TeraTypesAccessor.getTypes().put(cobblemonResource(id), tera);
        Cobblemon.INSTANCE.getShowdownThread().queue(svc -> {
            if (svc instanceof GraalShowdownService g)
                g.context.getBindings("js").getMember("receiveNewTypeData").execute(name, gmaxA, gmaxB);
            return kotlin.Unit.INSTANCE;
        });
        return t;
    }
}
