package me.lemonke.crystalleader.events;

import me.lemonke.crystalleader.CrystalLeader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnRegular implements Listener {

    @EventHandler
    public void onSpawn(PlayerRespawnEvent event) {
        if (!CrystalLeader.getInstance().getConfig().getBoolean("RespawnRegular.enable")) return;
        event.setRespawnLocation(new Location(Bukkit.getWorld(CrystalLeader.configString("RespawnRegular.world")), CrystalLeader.configInt("RespawnRegular.x"), CrystalLeader.configInt("RespawnRegular.y"), CrystalLeader.configInt("RespawnRegular.z")));
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        if (!CrystalLeader.getInstance().getConfig().getBoolean("RespawnRegular.enable")) return;
        event.getPlayer().teleport(new Location(Bukkit.getWorld(CrystalLeader.configString("RespawnRegular.world")), CrystalLeader.configInt("RespawnRegular.x"), CrystalLeader.configInt("RespawnRegular.y"), CrystalLeader.configInt("RespawnRegular.z")));
    }
}