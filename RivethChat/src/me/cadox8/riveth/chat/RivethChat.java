package me.cadox8.riveth.chat;

import lombok.Getter;
import me.cadox8.riveth.Riveth;
import me.cadox8.riveth.utils.Log;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class RivethChat extends JavaPlugin {

    @Getter private static RivethChat instance;
    @Getter private static Riveth API;

    public void onEnable() {
        instance = this;

        if (getServer().getPluginManager().getPlugin("RivethCore") == null) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "RivethCore not found, disabling...");
            getServer().getPluginManager().disablePlugin(this);
        }
        API = Riveth.getInstance();

        Log.log(Log.Level.SUCCESS, "RivethChat loaded. Version: " + getDescription().getVersion());
    }
}
