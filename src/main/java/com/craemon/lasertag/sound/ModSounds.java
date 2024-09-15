package com.craemon.lasertag.sound;

import com.craemon.lasertag.LaserTag;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent LASERPROJECTILE_SHOOT = registerSoundEvent("laserprojectile_shoot");
    public static final SoundEvent LASERPROJECTILE_HIT = registerSoundEvent("laserprojectile_hit");

    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(LaserTag.MOD_ID, name),
                SoundEvent.of(Identifier.of(LaserTag.MOD_ID, name)));
    }

    public static void registerSounds() {
    }
}
