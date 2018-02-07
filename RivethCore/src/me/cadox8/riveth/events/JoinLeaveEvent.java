package me.cadox8.riveth.events;

import me.cadox8.riveth.Riveth;
import me.cadox8.riveth.api.RServer;
import me.cadox8.riveth.api.RUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveEvent implements Listener {

    private final Riveth plugin;

    public JoinLeaveEvent(Riveth instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        RUser u = RServer.getUser(e.getPlayer().getUniqueId());

        plugin.getMysql().setupTable(u);
        u.save();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        RUser u = RServer.getUser(e.getPlayer().getUniqueId());

        RServer.broadcast(plugin.getConfig().getString("JL.join"));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        RUser u = RServer.getUser(e.getPlayer().getUniqueId());

        RServer.broadcast(plugin.getConfig().getString("JL.leave"));

        RServer.removeUser(u);
        RServer.removeAFK(u);
    }
}
