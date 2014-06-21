package com.minegusta.mgraces.misclisteners;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.player.CreateMGPlayer;
import com.minegusta.mgraces.player.MGPlayer;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class LoadToMap
{
    private UUID uuid;
    private MGPlayer mGP;

    public LoadToMap(PlayerJoinEvent e)
    {
        this.uuid = e.getPlayer().getUniqueId();
        this.mGP = new CreateMGPlayer(uuid).get();
        addToMap();
    }

    //Methods

    private void addToMap()
    {
        TempData.raceMap.put(uuid, mGP);
    }
}
