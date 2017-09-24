package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RUser;

public class SuicideCMD extends RCmd {

    public SuicideCMD() {
        super("suicide", "suicide");
    }

    public void run(RUser u, String label, String... args) {
        switch (args.length) {
            case 0:
                u.suicide();
                break;
            default:
                argsProblems(u);
                break;
        }
    }
}
