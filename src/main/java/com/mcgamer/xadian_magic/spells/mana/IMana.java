package com.mcgamer.xadian_magic.spells.mana;

import net.minecraft.nbt.CompoundTag;

public interface IMana {

    int getMana();

    void addMana(int add);

    void subMana(int sub);

    void copyFrom(PlayerMana source);

    void saveNBTData(CompoundTag nbt);

    void loadNBTData(CompoundTag nbt);
}
