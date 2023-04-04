package me.lemonke.crystalleader;

import me.lemonke.crystalleader.events.*;
import me.lemonke.crystalleader.extension.PlaceholderHook;
import me.lemonke.crystalleader.events.AntiCheatListener;
import me.lemonke.crystalleader.events.DeathMessageListener;
import me.lemonke.crystalleader.managers.ConfigManager;
import me.lemonke.crystalleader.tasks.ArenaClear;
import me.lemonke.crystalleader.tasks.CrystalClear;
import me.lemonke.crystalleader.utils.Global;
import me.lemonke.crystalleader.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class CrystalLeader extends JavaPlugin {
    public static CrystalLeader INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        Long time = Global.calcTime(() -> {
            getLogger().info(Message.color("&aStart loading CrystalLeader..."));

            saveDefaultConfig();
            new ConfigManager().init();
            getLogger().info(Message.color("&aLoaded configuration"));

            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                new PlaceholderHook().register();
                getLogger().info(Message.color("&aHooked to PlaceholderAPI"));
            }
            Config.init();

            Bukkit.getServer().getPluginManager().registerEvents(new CrystalKiller(), this);
            Bukkit.getServer().getPluginManager().registerEvents(new FrameItems(), this);
            Bukkit.getServer().getPluginManager().registerEvents(new InventoryClear(), this);
            Bukkit.getServer().getPluginManager().registerEvents(new RespawnRegular(), this);
            Bukkit.getServer().getPluginManager().registerEvents(new AntiCheatListener(), this);
            Bukkit.getServer().getPluginManager().registerEvents(new DeathMessageListener(), this);

            Bukkit.getScheduler().runTaskTimer(this, new ArenaClear(), 0, 20 * 60 * 60);
            Bukkit.getScheduler().runTaskTimer(this, new CrystalClear(), 0, 20 * 60 * 60);

            getLogger().info(ChatColor.BLUE + """

                       ____                _        _   _                   _          \s
                      / ___|_ __ _   _ ___| |_ __ _| | | |    ___  __ _  __| | ___ _ __\s
                     | |   | '__| | | / __| __/ _` | | | |   / _ \\/ _` |/ _` |/ _ \\ '__|
                     | |___| |  | |_| \\__ \\ || (_| | | | |__|  __/ (_| | (_| |  __/ |  \s
                      \\____|_|   \\__, |___/\\__\\__,_|_| |_____\\___|\\__,_|\\__,_|\\___|_|  \s
                                 |___/                                                 \s
                    """);
        });
        getLogger().info(Message.color("Loaded CrystalLeader, it took time: " + time + "ms"));
    }

    @Override
    public void onDisable() {
        getLogger().info(Message.color("Disabled CrystalLeader, thanks for using OwO"));
    }

    public static CrystalLeader getInstance() {
        return getPlugin(CrystalLeader.class);
    }

    public static int configInt(String path) {
        return getInstance().getConfig().getInt(path);
    }

    public static String configString(String path) {
        return getInstance().getConfig().getString(path);
    }

    public static List<String> configStringList(String path) {
        return getInstance().getConfig().getStringList(path);
    }
}
