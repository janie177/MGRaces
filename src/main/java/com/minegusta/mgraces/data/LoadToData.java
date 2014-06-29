package com.minegusta.mgraces.data;

import com.minegusta.mgraces.player.CreateMGPlayer;
import com.minegusta.mgraces.player.MGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LoadToData
{
    public static void loadAllToMap()
    {
        for(Player p : Bukkit.getOnlinePlayers())
        {
            UUID uuid = p.getUniqueId();
            MGPlayer player = new CreateMGPlayer(uuid).get();
            TempData.raceMap.put(uuid, player);
        }

    }
}
