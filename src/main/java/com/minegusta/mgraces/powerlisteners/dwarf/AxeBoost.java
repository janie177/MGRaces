package com.minegusta.mgraces.powerlisteners.dwarf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.race.Dwarf;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AxeBoost
{
    private int[] axes = {258, 271, 275, 279, 286};
    private Entity damager;
    private DefaultConf conf = new DefaultConf();
    EntityDamageByEntityEvent e;
    private Entity damaged;

    public  AxeBoost(EntityDamageByEntityEvent e)
    {
        this.damaged = e.getEntity();
        this.damager = e.getDamager();
        this.e = e;

        if(!e.isCancelled() && damagerIsPlayer() && victimIsLiving() && damagerIsDwarf() && hasAxe())
        {
            apply();
        }
    }

    private boolean damagerIsPlayer()
    {
        return damager instanceof Player;
    }

    private boolean damagerIsDwarf()
    {
        return TempData.raceMap.get(damager.getUniqueId()).getRace() instanceof Dwarf;
    }

    private boolean victimIsLiving()
    {
        return damaged instanceof LivingEntity;
    }

    private boolean hasAxe()
    {
        if(((Player)damager).getItemInHand() == null)return false;
        for(int i : axes)
        {
            if(i == ((Player)damager).getItemInHand().getTypeId())return true;
        }
        return false;
    }

    private void apply()
    {
        e.setDamage(e.getDamage() + conf.axeDamage());
    }

}
