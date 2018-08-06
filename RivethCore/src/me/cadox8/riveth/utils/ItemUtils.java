package me.cadox8.riveth.utils;

import me.cadox8.riveth.Riveth;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemUtils {

    private static Riveth plugin = Riveth.getInstance();

    @Deprecated
    public static ItemStack createPlayerHead(String username, List<String> lore) {
        return createPlayerHead(username, username, lore);
    }
    @Deprecated
    public static ItemStack createPlayerHead(String displayname, String username, List<String> lore) {
        return createPlayerHead(displayname, plugin.getServer().getOfflinePlayer(username).getUniqueId(), lore);
    }
    public static ItemStack createPlayerHead(String displayname, UUID userUUID, List<String> lore) {
        ItemStack playerHead = new ItemStack(Material.SKELETON_SKULL, 1, (short) 3);
        SkullMeta sm = (SkullMeta) playerHead.getItemMeta();
        sm.setOwningPlayer(plugin.getServer().getOfflinePlayer(userUUID));
        ArrayList<String> colorLore = new ArrayList<>();
        if (lore != null) {
            lore.forEach(str -> colorLore.add(Utils.colorize(str)));
            sm.setLore(colorLore);
        }

        sm.addItemFlags(ItemFlag.values());
        sm.setDisplayName(Utils.colorize(displayname));
        playerHead.setItemMeta(sm);
        return playerHead;
    }
}
