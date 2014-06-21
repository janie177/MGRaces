package com.minegusta.mgraces.misclisteners;

import com.minegusta.mgraces.data.TempData;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class RemoveFromMap
{
    private UUID uuid;

    public RemoveFromMap(PlayerQuitEvent e)
    {
        this.uuid = e.getPlayer().getUniqueId();
        remove();
    }

    private void remove()
    {
        if(TempData.raceMap.containsKey(uuid))TempData.raceMap.remove(uuid);
    }
}
