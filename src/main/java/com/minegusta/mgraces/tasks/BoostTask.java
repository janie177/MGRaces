package com.minegusta.mgraces.tasks;

import com.minegusta.mgraces.Main;
import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.player.MGPlayer;
import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.powerlisteners.demon.IceWeakness;
import com.minegusta.mgraces.powerlisteners.demon.NetherBoost;
import com.minegusta.mgraces.powerlisteners.demon.WorldWeakness;
import com.minegusta.mgraces.powerlisteners.elf.WaterBoost;
import com.minegusta.mgraces.powerlisteners.enderborn.InvisibleBoost;
import com.minegusta.mgraces.powerlisteners.shared.WaterWeakness;
import com.minegusta.mgraces.race.*;
import com.minegusta.mgraces.util.WorldCheck;
import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffectType;

public class BoostTask
{
    public static int boostTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
        @Override
        public void run()
        {
            for(MGPlayer mGP : TempData.raceMap.values())
            {
                if(!WorldCheck.worldCheck(Bukkit.getPlayer(mGP.getUUID()).getWorld()))continue;
                if(mGP.getRace() instanceof Human)
                {
                    continue;
                }
                if(mGP.getRace() instanceof Elf)
                {
                    new PermanentPotionEffect(mGP.getUUID(), PotionEffectType.SPEED, 0, 4);
                    continue;
                }
                if(mGP.getRace() instanceof Dwarf)
                {
                    new PermanentPotionEffect(mGP.getUUID(), PotionEffectType.FAST_DIGGING, 0, 4);
                    continue;
                }
                if(mGP.getRace() instanceof EnderBorn)
                {
                    new PermanentPotionEffect(mGP.getUUID(), PotionEffectType.NIGHT_VISION, 0, 10);
                    new PermanentPotionEffect(mGP.getUUID(), PotionEffectType.JUMP, 0, 4);
                    continue;
                }
                if(mGP.getRace() instanceof Demon)
                {
                    new WorldWeakness(mGP);
                    new PermanentPotionEffect(mGP.getUUID(), PotionEffectType.FIRE_RESISTANCE, 1, 4);
                }
            }
        }
    }, 0 , 20 * 2);

    public static int secondBoostTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
        @Override
        public void run()
        {
            for(MGPlayer mGP : TempData.raceMap.values())
            {
                if(!WorldCheck.worldCheck(Bukkit.getPlayer(mGP.getUUID()).getWorld()))continue;
                if(mGP.getRace() instanceof Human)
                {
                    continue;
                }
                if(mGP.getRace() instanceof Elf)
                {
                    new WaterBoost(mGP);
                    continue;
                }
                if(mGP.getRace() instanceof Dwarf)
                {

                    continue;
                }
                if(mGP.getRace() instanceof EnderBorn)
                {
                    new WaterWeakness(mGP);
                    new InvisibleBoost(mGP);
                    continue;
                }
                if(mGP.getRace() instanceof Demon)
                {
                    new WaterWeakness(mGP);
                    new NetherBoost(mGP);
                    new IceWeakness(mGP);
                }
            }
        }
    }, 0 , 20);
}
