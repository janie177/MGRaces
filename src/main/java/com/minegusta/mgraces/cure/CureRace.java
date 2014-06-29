package com.minegusta.mgraces.cure;

import com.minegusta.mgraces.files.PlayerConf;
import com.minegusta.mgraces.health.RaceHealth;
import com.minegusta.mgraces.misclisteners.LoadToMap;
import com.minegusta.mgraces.race.Human;

import java.util.UUID;

public class CureRace
{
    private static PlayerConf conf = new PlayerConf();

    public static void makeHuman(UUID uuid)
    {
        conf.setRace(uuid.toString(), "human");
        new LoadToMap(uuid);
        RaceHealth.setHealth(uuid, new Human());
    }
}
