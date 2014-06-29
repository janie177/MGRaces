package com.minegusta.mgraces.tasks;

import com.minegusta.mgraces.Main;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.Player;

public class ParticleTask
{
    public static int particleTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable()
        {
            @Override
            public void run()
            {
                for (Player p : Bukkit.getOnlinePlayers())
                {
                    ((CraftLivingEntity)p).getHandle().getDataWatcher().watch(7, Integer.valueOf(0));
                }
            }
        }, 0, 30 * 4);
    }
}
