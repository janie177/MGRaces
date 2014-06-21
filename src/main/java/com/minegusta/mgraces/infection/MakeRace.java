package com.minegusta.mgraces.infection;

import com.minegusta.mgraces.files.PlayerConf;
import com.minegusta.mgraces.misclisteners.LoadToMap;

import java.util.UUID;

public class MakeRace
{
    private static PlayerConf conf = new PlayerConf();

    public static void makeRace(UUID uuid, String race)
    {
        conf.setRace(uuid.toString(), race);
        new LoadToMap(uuid);
    }
}
