package me.cadox8.riveth.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static boolean isInt(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String locationToString(Location loc) {
        return loc.getWorld().getName() + "%" + loc.getX() + "%" + loc.getY() + "%" + loc.getZ() + "%" + loc.getYaw() + "%" + loc.getPitch();
    }

    public static Location stringToLocation(String string) {
        if (string == null) return null;
        String[] s = string.split("%");
        Location loc = new Location(Bukkit.getWorld(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]), Double.parseDouble(s[3]), Float.parseFloat(s[4]), Float.parseFloat(s[5]));
        return loc;
    }

    public static ItemStack createHeadPlayer(String displayname, String username, List<String> lore) {
        ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta sm = (SkullMeta) playerHead.getItemMeta();
        sm.setOwner(username);
        ArrayList<String> colorLore = new ArrayList<>();
        if (lore != null) {
            lore.forEach(str -> colorLore.add(Utils.colorize(str)));
            sm.setLore(colorLore);
        }

        sm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_UNBREAKABLE);
        sm.setDisplayName(Utils.colorize(displayname));
        playerHead.setItemMeta(sm);
        return playerHead;
    }

    public static String buildString(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (i > 0) stringBuilder.append(" ");
            stringBuilder.append(args[i]);
        }
        return stringBuilder.toString();
    }
}
