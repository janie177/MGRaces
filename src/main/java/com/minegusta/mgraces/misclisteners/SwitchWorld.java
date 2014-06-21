package com.minegusta.mgraces.misclisteners;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.health.RaceHealth;
import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.util.WorldCheck;
import org.bukkit.World;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.UUID;

public class SwitchWorld
{
    World world;
    UUID uuid;

    public SwitchWorld(PlayerChangedWorldEvent e)
    {
        this.world = e.getPlayer().getWorld();
        this.uuid = e.getPlayer().getUniqueId();
        check();
    }

    private void check()
    {
        if(!WorldCheck.worldCheck(world))
        {
            RaceHealth.setHealth(uuid, new Human());
        }
        else
        {
            RaceHealth.setHealth(uuid, TempData.raceMap.get(uuid).getRace());
        }
    }
}
