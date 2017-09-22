package me.cadox8.riveth.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.cadox8.riveth.Riveth;
import me.cadox8.riveth.utils.RFileLoader;
import me.cadox8.riveth.utils.Utils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.util.UUID;

public class RUser {

    private Riveth plugin = Riveth.getInstance();

    @Getter private UUID uuid;
    @Getter @Setter private UserData userData;

    public RUser(OfflinePlayer player) {
        this(player.getUniqueId());
    }

    public RUser(UUID uuid) {
        this.uuid = uuid;
        setUserData(plugin.getMysql().loadUserData(uuid));
    }


    public Player getPlayer() {
        return plugin.getServer().getPlayer(uuid);
    }
    public OfflinePlayer getOfflinePlayer() {
        return plugin.getServer().getOfflinePlayer(uuid);
    }
    public String getName() {
        return getOfflinePlayer().getName();
    }


    public void sendMessage(String msg) {
        getPlayer().sendMessage(Riveth.getPrefix() + Utils.colorize(msg));
    }
    public void sendRawMessage(String msg) {
        getPlayer().sendMessage(Utils.colorize(msg));
    }
    public boolean hasPermission(String perm) {
        return getPlayer().hasPermission(perm);
    }
    public boolean isOnline() {
        return getOfflinePlayer().isOnline();
    }


    public void setGod() {
        getUserData().setGod(!getUserData().getGod());
        sendMessage(Riveth.getMessage("God.Yourself").replace("%god%", getUserData().getGod() ? "&2enabled" : "&cdisabled"));
    }

    @Data
    public static class UserData {
        Boolean god = false;
        Long lastConnect = 0L;
        String nickname = null;
        InetSocketAddress ip = null;
    }
}
