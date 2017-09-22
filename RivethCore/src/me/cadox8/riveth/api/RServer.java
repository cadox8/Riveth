package me.cadox8.riveth.api;

import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.UUID;

public class RServer {

    public static ArrayList<RUser> users = new ArrayList<>();


    public static RUser getUser(UUID id) {
        for (RUser pl : users) {
            if (pl.getUuid() == null) continue;
            if (pl.getUuid().equals(id)) return pl;
        }
        RUser us = new RUser(id);
        if (us.isOnline()) users.add(us);
        return us;
    }

    public static RUser getUser(OfflinePlayer p) {
        return getUser(p.getUniqueId());
    }
}
