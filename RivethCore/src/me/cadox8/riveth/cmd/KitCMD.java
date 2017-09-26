package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.kits.Kit;
import me.cadox8.riveth.utils.Utils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class KitCMD extends RCmd {

    public KitCMD() {
        super("kit", "kit");
    }

    private String kits = "none";

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                Kit.kits.forEach(k -> {
                    if (kits.contains("none")) kits = "&c" + k.getName();
                    kits += "&6, &c" + k.getName();
                });
                u.sendMessage("&6Available kits: " + kits);
                break;
            case 1:
                String kit = args[0];

                Kit.kits.forEach(k-> {
                    if (k.getName().toLowerCase().equalsIgnoreCase(kit)) k.give(u);
                });
                break;

            default:
                break;
        }
    }
}
