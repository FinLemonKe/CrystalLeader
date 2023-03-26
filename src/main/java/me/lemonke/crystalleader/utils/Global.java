package me.lemonke.crystalleader.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;

import java.util.List;

public class Global {
    public static Long calcTime(Runnable runnable) {
        Timer timer = new Timer();
        runnable.run();
        return timer.passedMs();
    }

    public static void clearArena(World world, Location from, Location to, List<String> except) {
        int minX = Math.min(from.getBlockX(), to.getBlockX());
        int minY = Math.min(from.getBlockY(), to.getBlockY());
        int minZ = Math.min(from.getBlockZ(), to.getBlockZ());
        int maxX = Math.max(from.getBlockX(), to.getBlockX());
        int maxY = Math.max(from.getBlockY(), to.getBlockY());
        int maxZ = Math.max(from.getBlockZ(), to.getBlockZ());
        for (int x = minX; x < maxX + 1; x++)
            for (int y = minY; y < maxY + 1; y++)
                for (int z = minZ; z < maxZ + 1; z++) {
                    Material type = world.getBlockAt(x, y, z).getType();
                    if (type == Material.AIR || except.contains(type.name())) continue;
                    world.getBlockAt(x, y, z).setType(Material.AIR);
                }
    }

    public static void clearEndCrystals(World world, Location from, Location to) {
        int minX = Math.min(from.getBlockX(), to.getBlockX());
        int minY = Math.min(from.getBlockY(), to.getBlockY());
        int minZ = Math.min(from.getBlockZ(), to.getBlockZ());
        int maxX = Math.max(from.getBlockX(), to.getBlockX());
        int maxY = Math.max(from.getBlockY(), to.getBlockY());
        int maxZ = Math.max(from.getBlockZ(), to.getBlockZ());
        for (Entity entity : world.getEntities()) {
            if (!(entity instanceof EnderCrystal)) continue;
            Location loc = entity.getLocation();
            if (loc.getX() >= minX && loc.getY() >= minY && loc.getZ() >= minZ
                    && loc.getX() <= maxX && loc.getY() <= maxY && loc.getZ() <= maxZ) {
                entity.remove();
            }
        }
    }
}
