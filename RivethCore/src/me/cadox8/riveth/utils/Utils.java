package me.cadox8.riveth.utils;

import org.bukkit.ChatColor;

public class Utils {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
