package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RServer;
import me.cadox8.riveth.api.RUser;

public class FeedCMD extends RCmd {

    public FeedCMD() {
        super("feed", "feed", "eat");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                u.getPlayer().setFoodLevel(20);
                break;
            case 1:
                RUser target = RServer.getUser(plugin.getServer().getPlayerExact(args[0]));

                if (target == null || !target.isOnline()) {
                    notOnline(u, args[0]);
                    return;
                }
                target.getPlayer().setFoodLevel(20);
                break;

            default:
                argsProblems(u);
                break;
        }
    }
}
