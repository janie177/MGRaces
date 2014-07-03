package com.minegusta.mgraces.util;

import com.minegusta.mgraces.race.*;

public enum StringToRace
{
    human(new Human()),
    elf(new Elf()),
    enderborn(new EnderBorn()),
    demon(new Demon()),
    aurora(new Aurora()),
    dwarf(new Dwarf());


    private Race race;

    private StringToRace(Race race)
    {
        this.race = race;
    }

    public Race get()
    {
        return race;
    }

}
