package me.cadox8.riveth.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.utils.Utils;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportDelay extends BukkitRunnable {

    private RUser u;
    private Location l;
    private TeleportType ty;

    public TeleportDelay(RUser u, String l, TeleportType ty) {
        this(u, Utils.stringToLocation(l), ty);
    }
    public TeleportDelay(RUser u, Location l, TeleportType ty) {
        this.u = u;
        this.l = l;
        this.ty = ty;
    }

    public void run() {
        u.teleport(l);

        switch (ty) {
            case SPAWN:
                u.sendMessage(ty.getMsg());
                return;
        }
    }


    @AllArgsConstructor
    public enum TeleportType {
        SPAWN("Spawn.Own");

        @Getter private String msg;
    }
}
