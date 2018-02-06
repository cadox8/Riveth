package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RServer;
import me.cadox8.riveth.api.RUser;

public class InvSeeCMD extends RCmd {

    public InvSeeCMD() {
        super("invsee", "invsee");
    }

    @Override
    public void run(RUser u, String label, String[] args) {
        switch (args.length) {
            case 1:
                RUser target = RServer.getUser(plugin.getServer().getPlayerExact(args[0]));

                if (target == null || !target.isOnline()) {
                    notOnline(u, args[0]);
                    return;
                }

                u.getPlayer().openInventory(target.getInventory());
                break;

            default:
                argsProblems(u);
                break;
        }
    }
}
