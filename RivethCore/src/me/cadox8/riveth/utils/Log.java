package me.cadox8.riveth.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.cadox8.riveth.Riveth;

public class Log {

    @AllArgsConstructor
    public enum Level {
        DEBUG("Debug", '3'),
        SUCCESS("Success", '2'),
        ERROR("Error", 'c');

        @Getter private String prefix;
        @Getter private char color;
    }

    public static void log(Level type, String msg) {
        String format = "&7[&" + type.getColor() + type.getPrefix() + "&7] &" + type.getColor() + msg;
        Riveth.getInstance().getServer().getConsoleSender().sendMessage(Utils.colorize(format));
    }

    public static void debugLog(String msg) {
        log(Level.DEBUG, msg);
    }
}
