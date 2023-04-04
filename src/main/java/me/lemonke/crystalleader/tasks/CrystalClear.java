package me.lemonke.crystalleader.tasks;

import me.lemonke.crystalleader.CrystalLeader;
import me.lemonke.crystalleader.utils.Global;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CrystalClear implements Runnable {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
    @Override
    public void run() {
        String format = dateFormat.format(new Date());
        if (format.equals("23") || format.equals("12")) {
            for (String crystalClear : Objects.requireNonNull(CrystalLeader.getInstance().getConfig().getConfigurationSection("ArenaClear")).getKeys(false)) {
                World world = Bukkit.getWorld(CrystalLeader.configString("ArenaClear." + crystalClear + ".world"));
                Location from = new Location(world, CrystalLeader.configInt("ArenaClear." + crystalClear + ".from.x"), CrystalLeader.configInt("ArenaClear." + crystalClear + ".from.y"), CrystalLeader.configInt("ArenaClear." + crystalClear + ".from.z"));
                Location to = new Location(world, CrystalLeader.configInt("ArenaClear." + crystalClear + ".to.x"), CrystalLeader.configInt("ArenaClear." + crystalClear + ".to.y"), CrystalLeader.configInt("ArenaClear." + crystalClear + ".to.z"));
                Global.clearEndCrystals(world, from, to);
            }
        }
    }
}
