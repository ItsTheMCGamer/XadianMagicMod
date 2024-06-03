package com.mcgamer.xadian_magic.spells;

import com.google.common.collect.Maps;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.Level;

import java.util.Map;

public class BasicSpells {
    private static final Map<ResourceKey<Level>, ServerLevel> levels = Maps.newLinkedHashMap();

    public final ServerLevel overworld() {
        return this.levels.get(Level.OVERWORLD);
    }

    private static int getDuration(CommandSourceStack pSource, int pTime, IntProvider pTimeProvider) {
        return pTime == -1 ? pTimeProvider.sample(pSource.getLevel().getRandom()) : pTime;
    }

    public static int setClear(CommandSourceStack pSource, int pTime) {
        pSource.getLevel().setWeatherParameters(getDuration(pSource, pTime, ServerLevel.RAIN_DELAY), 0, false, false);
        return pTime;
    }
}




