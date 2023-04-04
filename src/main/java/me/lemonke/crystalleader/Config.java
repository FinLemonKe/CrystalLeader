package me.lemonke.crystalleader;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static FileConfiguration CONFIG;

    // Anti cheat
    public static int MIN_CRYSTAL_AGE;

    public static void init() {

        // Config
        CONFIG = CrystalLeader.INSTANCE.getConfig();
        // Anti cheat
        MIN_CRYSTAL_AGE = CONFIG.getInt("min_crystal_age");
    }
}
