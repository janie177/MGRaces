package com.minegusta.mgraces.util;

import com.minegusta.mgraces.data.TempData;
import org.bukkit.entity.Player;

public class RaceManager
{
    public String getRace(Player p)
    {
        return StringToRace.valueOf(TempData.raceMap.get(p.getUniqueId()).getRace().getName().toLowerCase()).getTitle();
    }
}
