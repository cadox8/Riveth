package me.cadox8.riveth.cmd.invs;

import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.cmd.RCmd;
import org.bukkit.event.inventory.InventoryType;

public class FurnaceCMD extends RCmd {

    public FurnaceCMD() {
        super("furnace", "furnace");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                u.getPlayer().openInventory(plugin.getServer().createInventory(null, InventoryType.FURNACE));
                break;

            default:
                argsProblems(u);
                break;
        }
    }
}
