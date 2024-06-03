package com.mcgamer.xadian_magic.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.checkerframework.checker.units.qual.K;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_XADIAN_MAGIC = "key.category.xadian_magic.xadian_magic";
    public static final String KEY_CAST_SPELL = "key.xadian_magic.cast_spell";
    public static final String KEY_OPEN_GUI = "key.xadian_magic.open_gui";

    public static final KeyMapping CAST_SPELL_KEY = new KeyMapping(KEY_CAST_SPELL, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY_XADIAN_MAGIC);
    public static final KeyMapping OPEN_GUI_KEY = new KeyMapping(KEY_OPEN_GUI, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_XADIAN_MAGIC);

}
