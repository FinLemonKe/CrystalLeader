package me.lemonke.crystalleader.events;

import me.lemonke.crystalleader.CrystalLeader;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InventoryClear implements Listener {
    @EventHandler
    public void Clear(PlayerJoinEvent event) {
        FileConfiguration config = CrystalLeader.getInstance().getConfig();
        if (!config.getBoolean("InventoryClear.enable")) return;
        event.getPlayer().getInventory().clear();
    }
}
