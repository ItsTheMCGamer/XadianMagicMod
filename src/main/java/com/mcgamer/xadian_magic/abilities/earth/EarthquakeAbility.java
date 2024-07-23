package com.mcgamer.xadian_magic.abilities.earth;

import com.mcgamer.xadian_magic.abilities.Ability;
import com.mcgamer.xadian_magic.networking.packet.earth.EarthquakeSpellC2SPacket;

import java.util.UUID;

public class EarthquakeAbility extends Ability {

    public EarthquakeAbility() {
        super("earth", "earthquake",
                "This spell summons a an earthquake which knocks back your enemies.", 15);
    }


    @Override
    public void execute() {
        new EarthquakeSpellC2SPacket();
    }
}
