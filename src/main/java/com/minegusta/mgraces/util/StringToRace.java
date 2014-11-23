package com.minegusta.mgraces.util;

import com.minegusta.mgraces.race.*;
import org.bukkit.ChatColor;

public enum StringToRace
{
    human(new Human(), ChatColor.DARK_GRAY + "[Hu]"),
    elf(new Elf(), ChatColor.GREEN + "[El]"),
    enderborn(new EnderBorn(), ChatColor.DARK_PURPLE + "[EB]"),
    demon(new Demon(), ChatColor.RED + "[De]"),
    aurora(new Aurora(), ChatColor.AQUA + "[Au]"),
    dwarf(new Dwarf(), ChatColor.DARK_GRAY + "[Dw]");


    private Race race;
    private String title;

    private StringToRace(Race race, String title)
    {
        this.race = race;
        this.title = title;
    }

    public Race get()
    {
        return race;
    }

    public String getTitle()
    {
        return title;
    }

}
