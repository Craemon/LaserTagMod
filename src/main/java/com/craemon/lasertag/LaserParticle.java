package com.craemon.lasertag;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.Vec3d;

public class LaserParticle extends Particle {
    private int lifetime;
    private float red, green, blue;

    public LaserParticle(ClientWorld world, double x, double y, double z, float r, float g, float b, int duration) {
        super(world, x, y, z);
        this.lifetime = duration;
        this.red = r;
        this.green = g;
        this.blue = b;
        this.scale(0.1f); // Adjust scale as needed
        this.setColor(red, green, blue);
    }

    protected LaserParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    public void tick() {
        super.tick();
        lifetime--;
        if (lifetime <= 0) {
            this.markDead(); // Mark particle for removal
        }
        // Example movement logic
        this.setPos(this.x, this.y + 0.05, this.z); // Move upwards
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {

    }

    @Override
    public ParticleTextureSheet getType() {
        return null;
    }
}

