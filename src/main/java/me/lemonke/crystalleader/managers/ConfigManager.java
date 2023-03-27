package me.lemonke.crystalleader.managers;

import me.lemonke.crystalleader.CrystalLeader;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConfigManager {

    private static final File dataFile = new File(CrystalLeader.getInstance().getDataFolder(), "/PlayerData.yml");
    private static YamlConfiguration dataConfig;

    public static ConfigManager INSTANCE;

    public HashMap<String, Long> allData = new HashMap<>();
    public List<Map.Entry<String, Long>> sortedData = new ArrayList<>();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void init() {
        INSTANCE = this;
        try {
            if (!dataFile.exists()) dataFile.createNewFile();
            dataConfig = YamlConfiguration.loadConfiguration(dataFile);
            updateAllPlayers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playerUpPoint(@NotNull Player player) {
        if (dataConfig.contains(player.getName())) {
            dataConfig.set(player.getName(), dataConfig.getLong(player.getName()) + 1);
        } else {
            dataConfig.set(player.getName(), 1L);
        }
        saveConfig();
        updateAllPlayers();
    }

    public long getPlayerPoint(@NotNull Player player) {
        return dataConfig.contains(player.getName()) ? dataConfig.getLong(player.getName()) : 0;
    }

    public void updateAllPlayers() {
        dataConfig.getKeys(false).forEach(it -> {
            if (allData.containsKey(it)) allData.replace(it, dataConfig.getLong(it));
            else allData.put(it, dataConfig.getLong(it));
        });
        sortedData = allData.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
    }

    private static void saveConfig() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
