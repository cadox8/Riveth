package me.cadox8.riveth;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.riveth.utils.Log;
import me.cadox8.riveth.utils.MySQL;
import me.cadox8.riveth.utils.RFileLoader;
import me.cadox8.riveth.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Riveth extends JavaPlugin {

    @Getter private static Riveth instance;

    @Getter private static String prefix = Utils.colorize(" &7|| &cRiveth &7|| ");
    @Getter @Setter private static boolean debug;

    @Getter private MySQL mysql = null;

    @Override
    public void onEnable() {
        instance = this;
        setDebug(false);

        registerClasses();
    }

    private void registerClasses() {
        RFileLoader.load();

        loadMySQL();

        RivethCommands.load();
    }

    private void registerEvents() {

    }

    private void loadMySQL() {
        String host = getConfig().getString("MySQL.host");
        String port = getConfig().getString("MySQL.port");
        String user = getConfig().getString("MySQL.user");
        String pass = getConfig().getString("MySQL.pass");
        String db = getConfig().getString("MySQL.db");
        try {
            Log.debugLog("Loading MySQL");
            mysql = new MySQL(host, port, user, pass, db);
            mysql.openConnection();
        } catch (SQLException | ClassNotFoundException exc) {
            Log.log(Log.Level.ERROR,"Error while connecting with MySQL");
            Log.debugLog("Cause: " + exc.toString());
            Log.log(Log.Level.ERROR,"Riveth disabled");
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}
