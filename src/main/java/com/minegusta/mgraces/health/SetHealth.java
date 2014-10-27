package com.minegusta.mgraces.health;

import com.minegusta.mgraces.data.TempData;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SetHealth
{
    /**
     * Set the health of a player on joining.
     * @param p The player.
     */
    public static void set(Player p)
    {
        UUID uuid = p.getUniqueId();
        RaceHealth.setHealth(uuid, TempData.raceMap.get(uuid).getRace());
    }


}
