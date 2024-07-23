package com.mcgamer.xadian_magic.abilities;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;

import java.util.UUID;

public abstract class Ability {

    private final String type;
    private final String name;
    private final String desc;
    private final int cooldown;


    public Ability(String type, String name, String desc, int cooldown) {
        this.type = type;
        this.name = name;
        this.desc = desc;
        this.cooldown = cooldown;


    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

    public UUID getType() {
        return UUID.fromString(this.type);
    }

    public int getWithItemCooldown() {
        int cooldown;
        if(Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag()
                .contains(type)) {
            cooldown = (int)Math.round(this.cooldown * 0.75);
        } else {
            cooldown = this.cooldown;
        }
        return cooldown;
    }

    public int getDefaultCooldown() {
        return this.cooldown;
    }

    public abstract void execute();
}
