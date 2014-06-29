package com.minegusta.mgraces.powerlisteners.enderborn;

import com.minegusta.mgraces.Main;
import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.race.EnderBorn;
import com.minegusta.mgraces.util.MGMessage;
import com.minegusta.mgraces.util.PlayEffect;
import com.minegusta.mgraces.util.RandomInt;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BleedBoost
{
    private Entity damaged;
    private Entity damager;
    private DefaultConf conf = new DefaultConf();

    public BleedBoost(EntityDamageByEntityEvent e)
    {
        this.damaged = e.getEntity();
        this.damager = e.getDamager();

        if(isHuman() && !e.isCancelled() && victimIsLiving() && isEnderborn())
        {
            apply();
        }
    }

    private boolean isHuman()
    {
        return damager instanceof Player;
    }

    private boolean isEnderborn()
    {
        return TempData.raceMap.get(damager.getUniqueId()).getRace() instanceof EnderBorn;
    }

    private boolean victimIsLiving()
    {
        return damaged instanceof LivingEntity;
    }


    //Apply

    private void apply()
    {
        //Chance
        if(RandomInt.getRandom(100) <= conf.bleedChance())
        {
            if(damaged instanceof Player) MGMessage.message((Player)damaged, "You are now bleeding!");

            final LivingEntity le = (LivingEntity) damaged;


            for(int i = 0; i <= 20 * conf.bleedDuration(); i++)
            {
                final int k = i;
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                    @Override
                    public void run() {
                        if(k % 20 == 0)
                        {
                            new PlayEffect(le, Effect.CRIT, 1, 1, 1, 8);
                            le.damage(conf.bleedDamage());
                        }
                    }
                }, i);
            }

        }
    }
}

