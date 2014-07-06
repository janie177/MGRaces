package com.minegusta.mgraces.util;

import com.google.common.collect.Lists;
import com.minegusta.mgraces.files.PlayerFile;
import org.bukkit.ChatColor;

import java.util.List;

public class TotalRaces
{
    private static int auroraInt,enderbornInt,demonInt,elfInt,dwarfInt,humanInt = 0;

    public static void listAmounts()
    {
        for(String s : PlayerFile.playerFile.getKeys(false))
        {
            if(PlayerFile.playerFile.getString(s).equalsIgnoreCase("aurora"))auroraInt++;
            else if(PlayerFile.playerFile.getString(s).equalsIgnoreCase("dwarf"))dwarfInt++;
            else if(PlayerFile.playerFile.getString(s).equalsIgnoreCase("elf"))elfInt++;
            else if(PlayerFile.playerFile.getString(s).equalsIgnoreCase("demon"))demonInt++;
            else if(PlayerFile.playerFile.getString(s).equalsIgnoreCase("human"))humanInt++;
            else if(PlayerFile.playerFile.getString(s).equalsIgnoreCase("enderborn"))enderbornInt++;
        }
    }

    public static List<String> get()
    {
        return Lists.newArrayList("Aurora: " + ChatColor.AQUA + Integer.toString(auroraInt), "Aurora: " + ChatColor.AQUA + Integer.toString(auroraInt), "Elf: " + ChatColor.AQUA + Integer.toString(elfInt), "Dwarf: " + ChatColor.AQUA + Integer.toString(dwarfInt), "Demon: " + ChatColor.AQUA + Integer.toString(demonInt), "Human: " + ChatColor.AQUA + Integer.toString(humanInt));

    }
}
