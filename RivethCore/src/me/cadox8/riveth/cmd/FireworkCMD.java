package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.utils.ColorUtils;
import me.cadox8.riveth.utils.FireworkAPI;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;

public class FireworkCMD extends RCmd {

    public FireworkCMD() {
        super("firework", "fw", "fw");
    }

    @Override
    public void run(RUser u, String label, String[] args) {
        switch (args.length) {
            case 0:
                new FireworkAPI(u.getLocation());
                break;

            case 5:
                // Schema: <type> <color> <color> <trail> <power>

                FireworkEffect.Type type = FireworkEffect.Type.valueOf(args[0]);
                Color color = ColorUtils.valueOf(args[1]).getColor();
                Color color2 = ColorUtils.valueOf(args[2]).getColor();
                boolean trail = Boolean.valueOf(args[3]);
                int power = Integer.parseInt(args[4]);

                new FireworkAPI(u.getLocation(), color, type, color2, trail, power);
                break;

            default:
                argsProblems(u);
                return;
        }
    }
}
