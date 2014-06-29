package com.minegusta.mgraces.player;

import com.minegusta.mgraces.race.Race;

import java.util.UUID;

public class MGPlayer
{
    private UUID uuid;
    private String name;
    private Race race;

    public MGPlayer(UUID uuid, String name, Race race)
    {
        this.uuid = uuid;
        this.name = name;
        this.race = race;
    }

    public String getPlayerName()
    {
        return name;
    }

    public UUID getUUID()
    {
        return uuid;
    }

    public Race getRace()
    {
        return race;
    }
}
