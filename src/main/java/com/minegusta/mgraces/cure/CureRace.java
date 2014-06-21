package com.minegusta.mgraces.cure;

import com.minegusta.mgraces.files.PlayerConf;
import com.minegusta.mgraces.misclisteners.LoadToMap;

import java.util.UUID;

public class CureRace
{
    private static PlayerConf conf = new PlayerConf();

    public static void makeHuman(UUID uuid)
    {
        conf.setRace(uuid.toString(), "human");
        new LoadToMap(uuid);
    }
}
