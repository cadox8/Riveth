package me.cadox8.riveth.cmd;

import me.cadox8.riveth.api.RUser;

public class HelpCMD extends RCmd {

    public HelpCMD() {
        super("help");
    }

    public void run(RUser user, String label, String... args) {
        switch (args.length) {
            case 0:

                break;
        }
    }
}
