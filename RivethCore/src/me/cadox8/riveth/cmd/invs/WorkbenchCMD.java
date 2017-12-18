package me.cadox8.riveth.cmd.invs;

import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.cmd.RCmd;
import org.bukkit.event.inventory.InventoryType;

public class WorkbenchCMD extends RCmd {

    public WorkbenchCMD() {
        super("workbench", "workbench", "wb", "wbench");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                u.getPlayer().openInventory(plugin.getServer().createInventory(null, InventoryType.WORKBENCH));
                break;

            default:
                argsProblems(u);
                break;
        }
    }
}
