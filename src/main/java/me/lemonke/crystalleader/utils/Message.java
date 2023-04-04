package me.lemonke.crystalleader.utils;

import org.bukkit.ChatColor;

public class Message {
    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&',msg);
    }
}
