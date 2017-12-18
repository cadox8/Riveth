package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RServer;
import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.utils.Utils;

public class FlyCMD extends RCmd {

    public FlyCMD() {
        super("fly", "fly");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                u.toggleFly();
                break;
            case 1:
                RUser target = RServer.getUser(plugin.getServer().getPlayerExact(args[0]));

                if (target == null || !target.isOnline()) {
                    notOnline(u, args[0]);
                    return;
                }

                target.toggleFly();
                u.sendMessage("*Fly.Others", Utils.boleanToText(target.getPlayer().getAllowFlight()), target.getName());
                break;

            default:
                argsProblems(u);
                break;
        }
    }
}
