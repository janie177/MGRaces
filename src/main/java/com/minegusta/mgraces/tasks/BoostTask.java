package com.minegusta.mgraces.tasks;

import com.minegusta.mgraces.Main;
import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.player.MGPlayer;
import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.powerlisteners.aurora.ColdBoost;
import com.minegusta.mgraces.powerlisteners.aurora.HeatWeakness;
import com.minegusta.mgraces.powerlisteners.demon.IceWeakness;
import com.minegusta.mgraces.powerlisteners.demon.NetherBoost;
import com.minegusta.mgraces.powerlisteners.demon.WorldWeakness;
import com.minegusta.mgraces.powerlisteners.elf.WaterBoost;
import com.minegusta.mgraces.powerlisteners.enderborn.InvisibleBoost;
import com.minegusta.mgraces.powerlisteners.shared.WaterWeakness;
import com.minegusta.mgraces.race.*;
import com.minegusta.mgraces.util.WorldCheck;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class BoostTask
{
    public static int boostTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
        @Override
        public void run()
        {
            for(MGPlayer mGP : TempData.raceMap.values())
            {
                UUID uuid = mGP.getUUID();
                Player p = Bukkit.getPlayer(uuid);
                Race race = mGP.getRace();
                
                if(!WorldCheck.worldCheck(Bukkit.getPlayer(mGP.getUUID()).getWorld()))continue;
                if(race instanceof Human)
                {
                    continue;
                }
                if(race instanceof Elf)
                {
                    PermanentPotionEffect.apply(p, PotionEffectType.SPEED, 0, 10);
                    continue;
                }
                if(race instanceof Dwarf)
                {
                    PermanentPotionEffect.apply(p, PotionEffectType.FAST_DIGGING, 0, 10);
                    continue;
                }
                if(race instanceof EnderBorn)
                {
                    PermanentPotionEffect.apply(p, PotionEffectType.NIGHT_VISION, 0, 15);
                    PermanentPotionEffect.apply(p, PotionEffectType.JUMP, 1, 10);
                    continue;
                }
                if(race instanceof Aurora)
                {
                    continue;
                }
                if(race instanceof Demon)
                {
                    WorldWeakness.check(mGP.getUUID());
                    PermanentPotionEffect.apply(p, PotionEffectType.FIRE_RESISTANCE, 1, 10);
                }
            }
        }
    }, 0 , 20 * 3);

    public static int secondBoostTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
        @Override
        public void run()
        {
            for(MGPlayer mGP : TempData.raceMap.values())
            {
                UUID uuid = mGP.getUUID();
                Player p = Bukkit.getPlayer(uuid);
                Race race = mGP.getRace();
                
                if(!WorldCheck.worldCheck(Bukkit.getPlayer(mGP.getUUID()).getWorld()))continue;
                if(race instanceof Human)
                {
                    continue;
                }
                if(race instanceof Elf)
                {
                    WaterBoost.apply(p);
                    continue;
                }
                if(race instanceof Dwarf)
                {

                    continue;
                }
                if(race instanceof EnderBorn)
                {
                    new WaterWeakness(p);
                    InvisibleBoost.apply(p);
                    continue;
                }
                if(race instanceof Aurora)
                {
                    ColdBoost.apply(p);
                    HeatWeakness.apply(p);
                    continue;
                }
                if(race instanceof Demon)
                {
                    new WaterWeakness(p);
                    NetherBoost.isInNether(p);
                    IceWeakness.isCold(p);
                }
            }
        }
    }, 0 , 20);
}
