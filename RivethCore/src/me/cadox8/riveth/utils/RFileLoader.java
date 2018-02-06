package me.cadox8.riveth.utils;

import me.cadox8.riveth.Riveth;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;

public class RFileLoader {

    private static final Riveth plugin = Riveth.getInstance();

    public static File fConf;

    public static void load() {
        fConf = new File(plugin.getDataFolder(), "config.yml");
        if (!fConf.exists()) {
            try {
                plugin.getConfig().options().copyDefaults(true);
                plugin.saveConfig();
                Log.log(Log.Level.SUCCESS, "config.yml successfully generated");
            } catch (Exception e) {
                Log.log(Log.Level.ERROR, "Error while creating config.yml! Cause: " + e.toString());
            }
        }

        File lf = new File(plugin.getDataFolder(), "lang.yml");
        if (!lf.exists()) {
            try {
                getLang().options().copyDefaults(true);
                saveLang();
                Log.log(Log.Level.SUCCESS, "lang.yml successfully generated");
            } catch (Exception e) {
                Log.log(Log.Level.ERROR, "Error while creating lang.yml! Cause: " + e.toString());
            }
        }
    }

    private static FileConfiguration lang = null;
    private static File langFile = null;

    public static void reloadLang() {
        if (langFile == null) langFile = new File(plugin.getDataFolder(), "lang.yml");
        lang = YamlConfiguration.loadConfiguration(langFile);

        InputStream defStream = plugin.getResource("lang.yml");
        if (defStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(langFile);
            lang.setDefaults(defConfig);
        }
    }

    public static FileConfiguration getLang() {
        if (lang == null) reloadLang();
        return lang;
    }

    public static void saveLang() {
        if (lang == null || langFile == null) return;
        try {
            getLang().save(langFile);
        } catch (Exception ex) {
            Log.log(Log.Level.ERROR, langFile + " couldn't be created. Cause: " + ex.toString());
        }
    }
}
