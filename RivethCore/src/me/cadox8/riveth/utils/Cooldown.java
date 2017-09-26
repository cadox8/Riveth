package me.cadox8.riveth.utils;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {

    @Getter private final int time;
    @Getter private final HashMap<UUID, Long> cooldowns;

    public Cooldown(int time) {
        this.time = time;
        this.cooldowns = new HashMap<>();
    }


    public int getTimeLeft(Player p) {
        return getTimeLeft(p.getUniqueId());
    }

    public int getTimeLeft(UUID uuid) {
        if (!isCoolingDown(uuid)) {
            return 0;
        }
        return (int) (((getCooldowns().get(uuid) - (System.currentTimeMillis() - (getTime() * 1000))) / 1000) + 1);
    }


    public void setOnCooldown(Player p) {
        setOnCooldown(p.getUniqueId());
    }

    public void setOnCooldown(UUID uuid) {
        getCooldowns().put(uuid, System.currentTimeMillis());
    }

    public boolean isCoolingDown(Player p) {
        return isCoolingDown(p.getUniqueId());
    }


    public boolean isCoolingDown(UUID uuid) {
        if (!getCooldowns().containsKey(uuid)) {
            return false;
        }
        return getCooldowns().get(uuid) >= (System.currentTimeMillis() - (getTime() * 1000));
    }
}
