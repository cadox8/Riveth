package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RUser;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class HatCMD extends RCmd {

    public HatCMD() {
        super("hat", "hat");
    }

    public void run(RUser u, String label, String... args) {
        PlayerInventory inv = u.getPlayer().getInventory();
        ItemStack newHat = inv.getItemInMainHand();
        ItemStack oldHat = inv.getHelmet();

        inv.setItemInMainHand(null);
        if (oldHat != null) {
            inv.setHelmet(null);
            inv.setItemInMainHand(oldHat);
        }
        inv.setHelmet(newHat);
        u.sendMessage("*Hat");
    }
}
