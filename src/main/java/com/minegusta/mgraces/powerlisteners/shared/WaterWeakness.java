package com.minegusta.mgraces.powerlisteners.shared;

import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.player.MGPlayer;
import com.minegusta.mgraces.worldguard.WGManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WaterWeakness
{
    private UUID uuid;
    private Player p;
    private DefaultConf conf = new DefaultConf();

    public WaterWeakness(MGPlayer mgp)
    {
        this.uuid = mgp.getUUID();
        p = Bukkit.getPlayer(uuid);

        if((isInWater() && isNotCancelled()) || (isRain() && isNotCancelled() && !isShelter()))
        {
            apply();
        }
    }

    private boolean isNotCancelled()
    {
        return WGManager.canGetDamage(p);
    }

    private boolean isRain()
    {
        return p.getWorld().hasStorm() && p.getLocation().getBlock().getTemperature() <= 0.95;
    }

    private boolean isInWater()
    {
        return p.getLocation().getBlock().getType().equals(Material.WATER) || p.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER);
    }

    private boolean isShelter()
    {
        return p.getWorld().getHighestBlockYAt(p.getLocation().getBlockX(), p.getLocation().getBlockZ()) > p.getLocation().getBlockY() + 1;
    }

    //Apply boosts

    private void apply()
    {
        p.damage(conf.waterDamage());
    }
}
