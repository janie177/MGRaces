package com.minegusta.mgraces.data;

import com.minegusta.mgraces.files.PlayerConf;
import com.minegusta.mgraces.player.CreateMGPlayer;
import com.minegusta.mgraces.player.MGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class LoadToData
{
    private static PlayerConf conf = new PlayerConf();

    public static void loadAllToMap()
    {

        List<UUID> list = conf.getAllUUID();
        for(UUID uuid : list)
        {
            if(Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).isOnline())continue;
            MGPlayer p = new CreateMGPlayer(uuid).get();
            TempData.raceMap.put(uuid, p);
        }

    }
}
