package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RServer;
import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.utils.Utils;
import org.bukkit.GameMode;

public class GamemodeCMD extends RCmd {

    public GamemodeCMD() {
        super("gamemode", "gamemode", "gm");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 1:
                changeGamemode(u, null, args[0]);
                break;
            case 2:
                changeGamemode(u, RServer.getUser(plugin.getServer().getPlayerExact(args[1])), args[0]);
                break;

            default:
                argsProblems(u);
                break;
        }
    }

    private void changeGamemode(RUser u, RUser target, String gameMode) {
        GameMode gm = Utils.isInt(gameMode) ? parseNumber(Integer.parseInt(gameMode)) : parseString(gameMode);

        if (gm == null) {
            u.sendMessage("&cThis is not a valid GameMode");
            return;
        }

        if (target != null) {
            if (!target.isOnline()) {
                notOnline(u, target.getName());
                return;
            }

            target.getPlayer().setGameMode(gm);
            target.sendMessage("*Gamemode");
            return;
        }

        u.getPlayer().setGameMode(gm);
        u.sendMessage("*Gamemode");
    }

    private GameMode parseNumber(int number) {
        switch (number) {
            case 0:
                return GameMode.SURVIVAL;
            case 1:
                return GameMode.CREATIVE;
            case 2:
                return GameMode.ADVENTURE;
            case 3:
                return GameMode.SPECTATOR;

            default:
                return null;
        }
    }

    private GameMode parseString(String gamemode) {
        switch (gamemode) {
            case "c":
            case "creative":
                return GameMode.CREATIVE;
            case "a":
            case "adventure":
                return GameMode.ADVENTURE;
            case "s":
            case "survival":
                return GameMode.SURVIVAL;
            case "spectator":
                return GameMode.SPECTATOR;

            default:
                return null;
        }
    }
}
