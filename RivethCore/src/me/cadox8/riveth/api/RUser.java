package me.cadox8.riveth.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.cadox8.riveth.Riveth;
import me.cadox8.riveth.utils.RFileLoader;
import me.cadox8.riveth.utils.Utils;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.PlayerInventory;

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

    public void save() {
        plugin.getMysql().saveUser(this);
        RServer.users.remove(this);
        plugin.getMysql().loadUserData(uuid);
        RServer.users.add(this);
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
    public boolean hasPermission(String perm) {
        return getPlayer().hasPermission(perm);
    }
    public boolean isOnline() {
        return getOfflinePlayer().isOnline();
    }
    public PlayerInventory getInventory() {
        return getPlayer().getInventory();
    }

    //

    public void teleport(Location l) {
        getPlayer().teleport(l);
    }

    public void sendRawMessage(String msg) {
        getPlayer().sendMessage(Utils.colorize(msg));
    }

    public void setGod(boolean god) {
        getUserData().setGod(god);
        sendMessage("*God.Yourself", getUserData().getGod() ? "&2enabled" : "&cdisabled");
        save();
    }

    public void suicide() {
        EntityDamageEvent e = new EntityDamageEvent(getPlayer(), EntityDamageEvent.DamageCause.SUICIDE, Short.MAX_VALUE);
        plugin.getServer().getPluginManager().callEvent(e);
        getPlayer().damage(Short.MAX_VALUE);
        if (getPlayer().getHealth() > 0) getPlayer().setHealth(0);

        sendMessage("*Suicide.Own");
        RServer.broadcast("*Suicide.BC", getName());
    }

    public void toggleFly() {
        getPlayer().setAllowFlight(!getPlayer().getAllowFlight());
        sendMessage("*Fly.Own", Utils.boleanToText(getPlayer().getAllowFlight()));
    }



    //
    public void sendMessage(String msg, Object... objects) {
        String finalMsg = msg;

        if (msg.startsWith("*")) {
            finalMsg = RFileLoader.getLang().getString(msg.substring(1));

            if (objects != null) {
                String[] split = finalMsg.split(" ");
                String[] ph = {};
                int id = 0;
                for (String s : split) {
                    if (s.startsWith("%") && s.endsWith("%")) ph[id] = s;
                    id++;
                }
                id--;
                for (Object o : objects) {
                    finalMsg.replace(ph[id], o.toString());
                    id--;
                    if (id == 0) break;
                }
            }
        }
        getPlayer().sendMessage(Riveth.getPrefix() + Utils.colorize(finalMsg));
    }
    //

    @Data
    public static class UserData {
        Boolean god = false;
        Long lastConnect = 0L;
        String nickname = null;
        InetSocketAddress ip = null;

        double money = 0;
    }
}
