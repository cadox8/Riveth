package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RUser;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import java.util.Set;

public class BreakCMD extends RCmd {

    public BreakCMD() {
        super("break", "break");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                Block b = null;
                for (int x = 0; x < 100; x++) {
                    b = u.getPlayer().getTargetBlock((Set<Material>) null,  x);

                    if (b.getType() != Material.AIR && b != null) break;
                    if (x >= 99) {
                        u.sendMessage("&cUnable to break that block");
                        return;
                    }
                }
                b.breakNaturally(new ItemStack(Material.AIR));
                u.sendMessage("&6Block broke");
                break;
            default:
                argsProblems(u);
                break;
        }
    }
}
