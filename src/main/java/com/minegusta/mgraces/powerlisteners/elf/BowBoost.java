package com.minegusta.mgraces.powerlisteners.elf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.race.Elf;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class BowBoost
{
    private Entity damaged;
    private Entity damager;
    private EntityDamageByEntityEvent e;
    private DefaultConf conf = new DefaultConf();

    public BowBoost(EntityDamageByEntityEvent e)
    {
        this.e = e;
        this.damaged = e.getEntity();
        this.damager = e.getDamager();

        if(!e.isCancelled() && isPlayer() && victimIsLiving() && isElf() && isArrow())
        {
            apply();
        }
    }

    private boolean isPlayer()
    {
        return damager instanceof Player;
    }

    private boolean victimIsLiving()
    {
        return damaged instanceof LivingEntity;
    }

    private boolean isElf()
    {
        return TempData.raceMap.get(damager.getUniqueId()).getRace() instanceof Elf;
    }

    private boolean isArrow()
    {
        return e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE);
    }

    private void apply()
    {
        e.setDamage(e.getDamage() + conf.bowDamage());
    }
}
