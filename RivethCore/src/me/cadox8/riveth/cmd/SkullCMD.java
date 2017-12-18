package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.utils.ItemUtils;

public class SkullCMD extends RCmd {

    public SkullCMD() {
        super("skull", "skull");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                u.getPlayer().getInventory().addItem(ItemUtils.createPlayerHead(args[0] + "'s skull", args[0], null));
                break;
            default:
                argsProblems(u);
                break;
        }
    }
}
