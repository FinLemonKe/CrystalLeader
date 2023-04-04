package me.lemonke.crystalleader.tasks;

import me.lemonke.crystalleader.CrystalLeader;
import me.lemonke.crystalleader.utils.Global;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ArenaClear implements Runnable {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH");

    @Override
    public void run() {
        String format = dateFormat.format(new Date());
        if (format.equals("23") || format.equals("12")) {
            for (String arenaClear : Objects.requireNonNull(CrystalLeader.getInstance().getConfig().getConfigurationSection("ArenaClear")).getKeys(false)) {
                World world = Bukkit.getWorld(CrystalLeader.configString("ArenaClear." + arenaClear + ".world"));
                Location from = new Location(world, CrystalLeader.configInt("ArenaClear." + arenaClear + ".from.x"), CrystalLeader.configInt("ArenaClear." + arenaClear + ".from.y"), CrystalLeader.configInt("ArenaClear." + arenaClear + ".from.z"));
                Location to = new Location(world, CrystalLeader.configInt("ArenaClear." + arenaClear + ".to.x"), CrystalLeader.configInt("ArenaClear." + arenaClear + ".to.y"), CrystalLeader.configInt("ArenaClear." + arenaClear + ".to.z"));
                Global.clearArena(world, from, to, CrystalLeader.configStringList("ArenaClear." + arenaClear + ".ExceptBlocks"));
            }
        }
    }
}
