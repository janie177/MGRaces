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
            p.setMaxHealth(conf.dwarfHealth());
        }

        if(r instanceof Elf)
        {
            p.setMaxHealth(conf.elfHealth());
        }

        if(r instanceof EnderBorn)
        {
            p.setMaxHealth(conf.enderBornHealth());
        }

        if(r instanceof Demon)
        {
            p.setMaxHealth(conf.demonHealth());
        }

        if(r instanceof Human)
        {
            p.setMaxHealth(conf.humanHealth());
        }
    }
}
