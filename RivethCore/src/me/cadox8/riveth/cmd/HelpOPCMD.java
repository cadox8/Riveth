package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RServer;
import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.utils.Cooldown;
import me.cadox8.riveth.utils.Utils;


public class HelpOPCMD extends RCmd {

    public HelpOPCMD() {
        super("helpop", "", "hp");
    }

    private final Cooldown temp = new Cooldown(30);

    @Override
    public void run(RUser u, String label, String[] args) {
        switch (args.length) {
            case 0:
                u.sendMessage("*helpop.uso");
                break;

            default:
                if (temp.isCoolingDown(u.getPlayer())){
                    u.sendMessage("&cThis command is in cooldown", temp.getTimeLeft(u.getPlayer()));
                    return;
                }

                String message = Utils.buildString(args);

                RServer.users.forEach(user -> {
                    if (user.hasPermission("riveth.helpop")) user.sendRawMessage(" &7|| &cHelpOP &7|| &2" + u.getName() + "&r: " + message);
                });

                temp.setOnCooldown(u.getPlayer());
                u.sendMessage("&6Your message was sent");
                break;
        }
    }
}
