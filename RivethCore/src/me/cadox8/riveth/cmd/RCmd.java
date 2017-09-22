package me.cadox8.riveth.cmd;

import lombok.Getter;
import me.cadox8.riveth.Riveth;
import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Cadiducho
 */

public abstract class RCmd {

    protected static transient Riveth plugin = Riveth.getInstance();
    @Getter private final transient String name;
    @Getter private final transient List<String> aliases;
    @Getter private final transient String perm;

    public RCmd(final String name, final String perm, final String... aliases) {
        this.name = name.toLowerCase();
        this.perm = "riveth." + perm;
        this.aliases = Arrays.asList(aliases);
    }

    public RCmd(final String name, final String perm) {
        this(name, perm, null);
    }

    public RCmd(final String name) {
        this(name, null, null);
    }

    public void run(ConsoleCommandSender sender, String label, String[] args) {
        run((CommandSender) sender, label, args);
    }

    public void run(RUser user, String label, String[] args) {
        run(user.getPlayer(), label, args);
    }

    public void run(CommandSender sender, String label, String[] args) {
        sender.sendMessage(Riveth.getMessage("NoPerms"));
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return new ArrayList<>();
    }
}
