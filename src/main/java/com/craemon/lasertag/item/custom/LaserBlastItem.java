package com.craemon.lasertag.item.custom;

import net.minecraft.item.Item;

public class LaserBlastItem extends Item {
    private final float damage;
    private final float speed;
    private final boolean explosive;
    public LaserBlastItem(Settings settings, float damage, float speed, boolean explosive)
    {
        super(settings);
        this.damage = damage;
        this.speed = speed;
        this.explosive = explosive;
    }

    public float getSpeed()
    {
        return this.speed;
    }
}
