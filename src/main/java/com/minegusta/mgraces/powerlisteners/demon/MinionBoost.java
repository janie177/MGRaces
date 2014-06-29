package com.minegusta.mgraces.powerlisteners.demon;

import com.minegusta.mgraces.Main;
import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.Demon;
import com.minegusta.mgraces.util.CoolDown;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MinionBoost
{
    private Entity damaged;
    private Entity damager;

    public MinionBoost(EntityDamageByEntityEvent e)
    {
        this.damaged = e.getEntity();
        this.damager = e.getDamager();

        if(!e.isCancelled() && isHuman() && enemyIsLiving() && isLowHealth() && isDemon())
        {
            apply();
        }
    }

    private boolean isHuman()
    {
        return damaged instanceof Player;
    }

    private boolean isDemon()
    {
        return TempData.raceMap.get(damaged.getUniqueId()).getRace() instanceof Demon;
    }

    private boolean isLowHealth()
    {
        return ((LivingEntity)damaged).getHealth() < 4;
    }

    private boolean enemyIsLiving()
    {
        return damager instanceof LivingEntity;
    }

    //Do

    private void apply()
    {
        if(!CoolDown.cooledDown(damaged.getUniqueId(), TempData.minionMap, 180))return;
        CoolDown.newCooldown(damaged.getUniqueId(), TempData.minionMap);
        final Location l = damaged.getLocation();
        for(int le = -5; le < 5; le++)
        {
            for(int le2 = -5; le2 < 5; le2++)
            {
                if(Math.abs(le2) + Math.abs(le) > 3 && Math.abs(le2) + Math.abs(le) < 5)
                {
                    final int loc1 = le2;
                    final int loc2 = le;
                    Entity e = l.getWorld().spawnEntity(l.getBlock().getRelative(loc1, 0, loc2).getLocation(), EntityType.PIG_ZOMBIE);
                    PigZombie m = (PigZombie) e;
                    m.setTarget((LivingEntity)damager);
                    for(int i = 0; i < 20 * 6; i++)
                    {
                        final int k = i;
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable()
                        {
                            @Override
                            public void run() {

                                l.getWorld().spigot().playEffect(l.getBlock().getRelative(loc1, 0, loc2).getLocation(), Effect.LAVADRIP, 1, 1, 0, k/30, 0, 1, 25, 30);

                            }
                        },i);
                    }
                }
            }
        }
    }

}
