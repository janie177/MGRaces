package com.minegusta.mgraces.powerlisteners.aurora;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.Aurora;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

public class WaterSpeedBoost
{
    private Player p;
    private Block b;

    public WaterSpeedBoost(PlayerToggleSneakEvent e)
    {
        this.p = e.getPlayer();
        this.b = e.getPlayer().getLocation().getBlock();

        if(isInWater() && isAurora())
        {
            apply();
        }
    }

    private boolean isInWater()
    {
        return b.getType().equals(Material.WATER) || b.getType().equals(Material.STATIONARY_WATER);
    }

    private boolean isAurora()
    {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof Aurora;
    }


    private void apply()
    {
        Vector victor = (p.getPassenger() != null && p.getLocation().getDirection().getY() > 0 ? p.getLocation().getDirection().clone().setY(0) : p.getLocation().getDirection()).normalize().multiply(1.3D);
        p.setVelocity(new Vector(victor.getX(), victor.getY(), victor.getZ()));   
    }
}
