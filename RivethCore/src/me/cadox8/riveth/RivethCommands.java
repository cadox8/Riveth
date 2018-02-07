package me.cadox8.riveth;

import me.cadox8.riveth.api.RServer;
import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.cmd.*;
import me.cadox8.riveth.cmd.invs.AnvilCMD;
import me.cadox8.riveth.cmd.invs.EnchantTableCMD;
import me.cadox8.riveth.cmd.invs.FurnaceCMD;
import me.cadox8.riveth.cmd.invs.WorkbenchCMD;
import me.cadox8.riveth.utils.Log;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cadiducho
 */

public class RivethCommands implements TabCompleter {

    public static List<RCmd> cmds = new ArrayList<>();
    public static RivethCommands ucmds;
    private static Riveth plugin = Riveth.getInstance();
    private static String name = "riveth:";

    public static void load() {
        cmds.add(new BreakCMD());
        cmds.add(new FeedCMD());
        cmds.add(new FireworkCMD());
        cmds.add(new FlyCMD());
        cmds.add(new GamemodeCMD());
        cmds.add(new GodCMD());
        cmds.add(new HatCMD());
        cmds.add(new HealCMD());
        cmds.add(new HelpCMD());
        cmds.add(new HelpOPCMD());
        cmds.add(new InvSeeCMD());
        cmds.add(new KillCMD());
        cmds.add(new KitCMD());
        cmds.add(new RepairCMD());
        cmds.add(new SkullCMD());
        cmds.add(new SuicideCMD());

        cmds.add(new WorkbenchCMD());
        cmds.add(new AnvilCMD());
        cmds.add(new EnchantTableCMD());
        cmds.add(new FurnaceCMD());
        //
        ucmds = new RivethCommands();
        //
        cmds.forEach(RivethCommands::register);
    }

    public static void register(RCmd... cmdList) {
        for (RCmd cmd : cmdList) {
            register(cmd);
        }
    }

    public static void register(RCmd cmd) {
        CommandMap commandMap = getCommandMap();
        PluginCommand command = getCmd(cmd.getName());

        if (command.isRegistered()) command.unregister(commandMap);

        command.setAliases(cmd.getAliases());
        command.setTabCompleter(ucmds);

        if (commandMap == null) return;

        commandMap.register(plugin.getDescription().getName(), command);

        if (!cmds.contains(cmd)) cmds.add(cmd);

        if (plugin.getServer().getPluginCommand(name + cmd.getName()) == null) {
            Log.log(Log.Level.ERROR, "Error while loading command /" + cmd.getName());
        }
    }

    private static PluginCommand getCmd(String name) {
        PluginCommand command = null;
        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);

            command = c.newInstance(name, plugin);
        } catch (Exception e) {
            Log.debugLog("This command couldn't be obtained");
        }
        return command;
    }

    public static void onCmd(final CommandSender sender, Command cmd, String label, final String[] args) {
        if (label.startsWith(name)) label = label.replaceFirst(name, "");

        for (RCmd cmdr : cmds) {
            if (label.equals(cmdr.getName()) || cmdr.getAliases().contains(label)) {
                if (sender instanceof ConsoleCommandSender) {
                    ConsoleCommandSender cs = (ConsoleCommandSender) sender;
                    cmdr.run(cs, label, args);
                    break;
                }
                if (sender instanceof Player) {
                    RUser p = RServer.getUser((Player) sender);
                    if (p.hasPermission(cmdr.getPerm())) {
                        cmdr.run(p, label, args);
                        return;
                    }

                    p.sendMessage(Riveth.getPrefix() + "&cYou don't have permissions to do that");
                    return;
                }
                cmdr.run(sender, label, args);
            }
        }
    }

    private static CommandMap getCommandMap() {
        CommandMap commandMap = null;
        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field f = SimplePluginManager.class.getDeclaredField("commandMap");
                f.setAccessible(true);
                commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return commandMap;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> rtrn = null;
        if (label.startsWith(name)) {
            label = label.replaceFirst(name, "");
        }

        for (RCmd cmdr : cmds) {
            if (cmdr.getName().equals(label) || cmdr.getAliases().contains(label)) {
                try {
                    if ((sender instanceof Player) && (!RServer.getUser((Player) sender).hasPermission(cmdr.getPerm()))) {
                        return new ArrayList<>();
                    }
                    rtrn = cmdr.onTabComplete(sender, cmd, label, args, args[args.length - 1], args.length - 1);
                } catch (Exception ex) {
                    Log.log(Log.Level.ERROR, "Error while autofilling " + label);
                }
                break;
            }
        }

        if (rtrn == null) {
            rtrn = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                rtrn.add(p.getName());
            }
        }

        ArrayList<String> rtrn2 = new ArrayList<>();
        rtrn2.addAll(rtrn);
        rtrn = rtrn2;
        if (!(args[args.length - 1].isEmpty() || args[args.length - 1] == null)) {
            List<String> remv = new ArrayList<>();
            for (String s : rtrn) {
                if (!StringUtils.startsWithIgnoreCase(s, args[args.length - 1])) {
                    remv.add(s);
                }
            }
            rtrn.removeAll(remv);
        }
        return rtrn;
    }
}
