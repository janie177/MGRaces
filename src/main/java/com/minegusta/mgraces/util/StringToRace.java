package com.minegusta.mgraces.util;

import com.minegusta.mgraces.race.*;
import org.bukkit.ChatColor;

public enum StringToRace
{
    human(new Human(), ChatColor.YELLOW + "[Human]"),
    elf(new Elf(), ChatColor.GREEN + "[Elf]"),
    enderborn(new EnderBorn(), ChatColor.DARK_PURPLE + "[EnderBorn]"),
    demon(new Demon(), ChatColor.RED + "[Demon]"),
    aurora(new Aurora(), ChatColor.AQUA + "[Aurora]"),
    dwarf(new Dwarf(), ChatColor.DARK_GRAY + "[Dwarf]");


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
