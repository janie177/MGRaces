package com.minegusta.mgraces.powerlisteners.demon;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.Demon;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamageBoost
{
    private Entity entity;
    private EntityDamageEvent.DamageCause cause;
    private EntityDamageEvent e;

    public FallDamageBoost(EntityDamageEvent e)
    {
        this.entity = e.getEntity();
        this.e = e;
        this.cause = e.getCause();

        if(isHuman() && isFall() && isDemon() && isNether())
        {
            cancel();
        }
    }

    private boolean isDemon()
    {
        return TempData.raceMap.get(entity.getUniqueId()).getRace() instanceof Demon;
    }

    private boolean isHuman()
    {
        return entity instanceof Player;
    }

    private boolean isFall()
    {
        return cause.equals(EntityDamageEvent.DamageCause.FALL);
    }

    private boolean isNether()
    {
        return entity.getWorld().getBiome(entity.getLocation().getBlockX(), entity.getLocation().getBlockZ()).equals(Biome.HELL);
    }

    private void cancel()
    {
        e.setCancelled(true);
    }

}
