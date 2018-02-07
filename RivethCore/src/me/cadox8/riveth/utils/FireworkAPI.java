package me.cadox8.riveth.utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class FireworkAPI {

    private Firework fw;

    private final Location loc;
    private boolean trail;
    private Color color;
    private Color color2;
    private int power;
    private FireworkEffect.Type type;

    public FireworkAPI(Location l) {
        this.loc = l;
        normalFirework();
    }
    public FireworkAPI(Location l, Color color, FireworkEffect.Type type, Color color2, boolean trail, int power) {
        this.loc = l;
        this.color = color;
        this.type = type;
        this.color2 = color2;
        this.trail = trail;
        this.power = power;
        customFirework();
    }


    private void customFirework() {
        fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        FireworkEffect fe = FireworkEffect.builder().trail(trail).withColor(color).with(type).withFade(color2).build();
        fwm.addEffect(fe);
        fwm.setPower(power);

        fw.setFireworkMeta(fwm);
    }

    private void normalFirework() {
        fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        final Random r = new Random();
        FireworkMeta fwm = fw.getFireworkMeta();

        FireworkEffect fe = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(Color.PURPLE).with(FireworkEffect.Type.STAR).withFade(Color.AQUA).build();
        fwm.addEffect(fe);
        fwm.setPower(10);

        fw.setFireworkMeta(fwm);
    }

    public Firework getFirework() {
        return fw;
    }
}
