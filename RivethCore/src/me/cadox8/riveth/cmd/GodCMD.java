package me.cadox8.riveth.cmd;

import me.cadox8.riveth.Riveth;
import me.cadox8.riveth.api.RServer;
import me.cadox8.riveth.api.RUser;

public class GodCMD extends RCmd {

    public GodCMD() {
        super("god", "god");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 1:
                RUser target = RServer.getUser(plugin.getServer().getPlayerExact(args[0]));

                if (target == null || !target.isOnline()) {
                    u.sendMessage("&6" + args[0] + " &cdoesn't exists or it's not online");
                    return;
                }
                target.setGod();
                u.sendMessage(Riveth.getMessage("God.Others"));
                break;

            default:
                u.setGod();
                break;
        }
    }
}
