package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.tasks.TeleportDelay;

public class SpawnCMD extends RCmd {

    public SpawnCMD() {
        super("spawn", "spawn");
    }

    @Override
    public void run(RUser u, String label, String[] args) {
        switch (args.length) {
            case 0:
                if (!plugin.getConfig().getBoolean("Spawn.allowSpawn")) {
                    u.sendMessage("");
                    return;
                }
                int time = plugin.getConfig().getInt("Spawn.waitTime");

                if (plugin.getConfig().getInt("Spawn.waitTime") != -1) {
                    u.sendMessage("Spawn.Wait", time);
                    new TeleportDelay(u, plugin.getConfig().getString("Spawn.spawnLoc"), TeleportDelay.TeleportType.SPAWN).runTaskLater(plugin, time * 20);
                }

                return;

            case 1:

                return;

            default:
                argsProblems(u);
                return;
        }
    }
}
