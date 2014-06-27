package com.minegusta.mgraces.health;

import com.minegusta.mgraces.data.TempData;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class SetHealth
{
    public SetHealth(PlayerJoinEvent e)
    {
        UUID uuid = e.getPlayer().getUniqueId();
        RaceHealth.setHealth(uuid, TempData.raceMap.get(uuid).getRace());
    }


}
