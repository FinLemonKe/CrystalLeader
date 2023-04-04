package me.lemonke.crystalleader.extension;

import me.lemonke.crystalleader.managers.ConfigManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderHook extends PlaceholderExpansion {

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equalsIgnoreCase("player")) {
            return String.valueOf(ConfigManager.INSTANCE.getPlayerPoint(player));
        }
        if (params.toLowerCase().startsWith("top_")) {
            try {
                int data = Integer.parseInt(params.substring(4));
                if (ConfigManager.INSTANCE.allData.size() < data) return null;
                return ConfigManager.INSTANCE.sortedData.get(data - 1).getValue().toString();
            } catch (NumberFormatException ignored) {
                return null;
            }
        } else if (params.toLowerCase().startsWith("player_")) {
            try {
                int data = Integer.parseInt(params.substring(7));
                if (ConfigManager.INSTANCE.allData.size() < data) return null;
                return ConfigManager.INSTANCE.sortedData.get(data - 1).getKey();
            } catch (NumberFormatException ignored) {
                return null;
            }
        }
        return null;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "CrystalLeader";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Fin_LemonKee";
    }

    @Override
    public @NotNull String getVersion() {
        return "0.0.1";
    }
}
