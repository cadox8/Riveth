package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RServer;
import me.cadox8.riveth.api.RUser;

public class GodCMD extends RCmd {

    public GodCMD() {
        super("god", "god");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                u.setGod(!u.getUserData().getGod());
                break;
            case 1:
                RUser target = RServer.getUser(plugin.getServer().getPlayerExact(args[0]));

                if (target == null || !target.isOnline()) {
                    notOnline(u, args[0]);
                    return;
                }
                target.setGod(!u.getUserData().getGod());
                u.sendMessage("*God.Others");
                break;
            case 2:
                if (!args[1].equalsIgnoreCase("on") || !args[1].equalsIgnoreCase("off")) {

                    return;
                }
                RUser target2 = RServer.getUser(plugin.getServer().getPlayerExact(args[0]));

                if (target2 == null || !target2.isOnline()) {
                    notOnline(u, args[0]);
                    return;
                }

                target2.setGod(args[1].equalsIgnoreCase("on"));
                u.sendMessage("*God.Others");
            default:
                argsProblems(u);
                break;
        }
    }
}
