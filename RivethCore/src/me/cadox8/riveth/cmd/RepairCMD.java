package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RUser;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;

public class RepairCMD extends RCmd {

    public RepairCMD() {
        super("repair", "repair", "fix");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                ItemStack i = u.getInventory().getItemInMainHand();
                i.setDurability((short)0);
                break;
            case 1:
                if (args[0].equalsIgnoreCase("all")) repairAll(u);
                break;

            default:
                argsProblems(u);
                break;
        }
    }

    public void repairAll(RUser u) {
        for(ItemStack items : u.getInventory().getContents()) {
            if(items instanceof Repairable && items != null) items.setDurability((short)0);
        }

        for(ItemStack items : u.getInventory().getArmorContents()) {
            if(items instanceof Repairable && items != null) items.setDurability((short)0);
        }
    }
}
