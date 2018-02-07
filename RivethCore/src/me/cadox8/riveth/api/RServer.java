package me.cadox8.riveth.api;

import lombok.Getter;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.UUID;

public class RServer {

    // Just if necessary
    @Getter private static ArrayList<RUser> users = new ArrayList<>();
    @Getter private static ArrayList<RUser> afks = new ArrayList<>();

    // Users
    public static RUser getUser(OfflinePlayer p) {
        return getUser(p.getUniqueId());
    }
    public static RUser getUser(UUID id) {
        for (RUser pl : users) {
            if (pl.getUuid() == null) continue;
            if (pl.getUuid().equals(id)) return pl;
        }
        RUser us = new RUser(id);
        if (us.isOnline()) users.add(us);
        return us;
    }

    public static void removeUser(OfflinePlayer p) {
        removeUser(p.getUniqueId());
    }
    public static void removeUser(UUID id) {
        removeUser(getUser(id));
    }
    public static void removeUser(RUser u) {
        if (users.contains(u)) users.remove(u);
    }
    //

    //AFKs
    public static void addAFK(OfflinePlayer p) {
        addAFK(p.getUniqueId());
    }
    public static void addAFK(UUID id) {
        addAFK(getUser(id));
    }
    public static void addAFK(RUser u) {
        if (!afks.contains(u)) afks.remove(u);
    }

    public static void removeAFK(OfflinePlayer p) {
        removeAFK(p.getUniqueId());
    }
    public static void removeAFK(UUID id) {
        removeAFK(getUser(id));
    }
    public static void removeAFK(RUser u) {
        if (afks.contains(u)) afks.remove(u);
    }
    //


    public static void broadcast(String msg, Object... objects) {
        users.forEach(u -> u.sendMessage(msg, objects));
    }
}
