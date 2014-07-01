package com.minegusta.mgraces.health;

import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.race.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RaceHealth
{
    private static DefaultConf conf = new DefaultConf();

    //Setting health
    public static void setHealth(UUID uuid, Race r)
    {
        Player p = Bukkit.getPlayer(uuid);

        if(r instanceof Dwarf)
        {
            int x = conf.dwarfHealth();
            p.setHealthScale(x);
            p.setMaxHealth(x);
            return;
        }

        if(r instanceof Elf)
        {
            int x = conf.elfHealth();
            p.setHealthScale(x);
            p.setMaxHealth(x);
            return;
        }

        if(r instanceof EnderBorn)
        {
            int x = conf.enderBornHealth();
            p.setHealthScale(x);
            p.setMaxHealth(x);
            return;
        }

        if(r instanceof Demon)
        {
            int x = conf.demonHealth();
            p.setHealthScale(x);
            p.setMaxHealth(x);
            return;
        }

        if(r instanceof Aurora)
        {
            int x = conf.auroraHealth();
            p.setHealthScale(x);
            p.setMaxHealth(x);
            return;
        }

        if(r instanceof Human)
        {
            int x = conf.humanHealth();
            p.setHealthScale(x);
            p.setMaxHealth(x);
            return;
        }
    }
}
