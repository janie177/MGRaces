package com.minegusta.mgraces.files;

import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.race.Race;
import com.minegusta.mgraces.util.StringToRace;
import org.bukkit.configuration.file.FileConfiguration;

public class PlayerConf
{
    private FileConfiguration conf()
    {
        return PlayerFile.playerFile;
    }


    //All methods for config values.

    public Race getRace(String uuid)
    {
        if(!conf().isSet(uuid))return new Human();
        String race = conf().getString(uuid).toLowerCase();

        return StringToRace.valueOf(race).get();
    }

    public void setRace(String uuid, String race)
    {
        conf().set(uuid, race.toLowerCase());
    }
}
