package com.mcgamer.xadian_magic.spells.mana;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana implements IMana{
    private int mana;
    private final int MIN_MANA = 0;
    public static int MAX_MANA = 50;

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void addMana(int add) {
        this.mana = Math.min(mana + add, MAX_MANA);
    }

    @Override
    public void subMana(int sub) {
        this.mana = Math.max(mana - sub, MIN_MANA);
    }

    @Override
    public void setMana(int amount) {
        this.mana = amount;
    }

    @Override
    public void copyFrom(PlayerMana source) {
        this.mana = source.mana;
    }

    @Override
    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("mana", mana);
    }

    @Override
    public void loadNBTData(CompoundTag nbt) {
        mana = nbt.getInt("mana");
    }
}