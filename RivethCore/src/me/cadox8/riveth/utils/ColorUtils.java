package me.cadox8.riveth.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Color;

@AllArgsConstructor
public enum ColorUtils {

    WHITE(Color.WHITE, ChatColor.WHITE),
    SILVER(Color.SILVER, ChatColor.GRAY),
    GRAY(Color.GRAY, ChatColor.DARK_GRAY),
    BLACK(Color.BLACK, ChatColor.BLACK),
    RED(Color.RED, ChatColor.RED),
    DARK_RED(Color.MAROON, ChatColor.DARK_RED),
    YELLOW(Color.YELLOW, ChatColor.YELLOW),
    OLIVE(Color.OLIVE, ChatColor.DARK_GREEN),
    LIME(Color.LIME, ChatColor.GREEN),
    GREEN(Color.GREEN, ChatColor.GREEN),
    AQUA(Color.AQUA, ChatColor.AQUA),
    TEAL(Color.TEAL, ChatColor.BLUE),
    BLUE(Color.BLUE, ChatColor.DARK_BLUE),
    NAVY(Color.NAVY, ChatColor.GREEN),
    FUCHSIA(Color.FUCHSIA, ChatColor.LIGHT_PURPLE),
    PURPLE(Color.PURPLE, ChatColor.DARK_PURPLE),
    ORANGE(Color.ORANGE, ChatColor.GOLD);

    @Getter private Color color;
    @Getter private ChatColor chatColor;
}
