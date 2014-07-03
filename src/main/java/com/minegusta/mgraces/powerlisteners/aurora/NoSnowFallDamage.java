package com.minegusta.mgraces.powerlisteners.aurora;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.Aurora;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoSnowFallDamage
{
    private Entity entity;
    private EntityDamageEvent.DamageCause cause;
    private EntityDamageEvent e;

    public NoSnowFallDamage(EntityDamageEvent e)
    {
        this.entity = e.getEntity();
        this.e = e;
        this.cause = e.getCause();

        if(isHuman() && isFall() && isAurora() && isSnow())
        {
            cancel();
        }
    }

    private boolean isAurora()
    {
        return TempData.raceMap.get(entity.getUniqueId()).getRace() instanceof Aurora;
    }

    private boolean isHuman()
    {
        return entity instanceof Player;
    }

    private boolean isFall()
    {
        return cause.equals(EntityDamageEvent.DamageCause.FALL);
    }

    private boolean isSnow()
    {
        Block b = entity.getLocation().getBlock();
        return b.getType().equals(Material.SNOW) || b.getRelative(BlockFace.DOWN).getType().equals(Material.SNOW_BLOCK);
    }

    private void cancel()
    {
        e.setCancelled(true);
    }

}
