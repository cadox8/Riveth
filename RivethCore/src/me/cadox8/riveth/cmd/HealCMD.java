package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RServer;
import me.cadox8.riveth.api.RUser;

public class HealCMD extends RCmd {

    public HealCMD() {
        super("heal", "heal", "regen");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                u.getPlayer().setFoodLevel(20);
                u.getPlayer().setHealth(u.getPlayer().getMaxHealth());
                u.sendMessage("*Health.Own");
                break;
            case 1:
                RUser target = RServer.getUser(plugin.getServer().getPlayerExact(args[0]));

                if (target == null || !target.isOnline()) {
                    notOnline(u, args[0]);
                    return;
                }

                if (target.getPlayer().isDead()) {
                    u.sendMessage("&cYou cannot heal someone who is dead!");
                    return;
                }

                target.getPlayer().setFoodLevel(20);
                target.getPlayer().setHealth(target.getPlayer().getMaxHealth());
                target.sendMessage("*Health.Own");
                u.sendMessage("*Health.Others", target.getName());
                break;

            default:
                argsProblems(u);
                break;
        }
    }
}
