package com.minegusta.mgraces.powerlisteners.dwarf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.race.Dwarf;
import com.minegusta.mgraces.worldguard.WGManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class ArrowWeakness
{
    private EntityDamageEvent.DamageCause cause;
    private Entity damaged;
    private DefaultConf conf = new DefaultConf();

    public ArrowWeakness(EntityDamageByEntityEvent e)
    {
        this.damaged = e.getEntity();
        this.cause = e.getCause();

        if(!e.isCancelled() && isArrow() && isPlayer() && canPVP() && isDwarf())
        {
            apply();
        }
    }

    private boolean canPVP()
    {
        return WGManager.canPVP(damaged);
    }


    private boolean isArrow()
    {
        return cause.equals(EntityDamageEvent.DamageCause.PROJECTILE);
    }

    private boolean isPlayer()
    {
        return damaged instanceof Player;
    }

    private boolean isDwarf()
    {
        return TempData.raceMap.get(damaged.getUniqueId()).getRace() instanceof Dwarf;
    }

    private void apply()
    {
        ((LivingEntity)damaged).damage(conf.arrowDamage());
    }
}
