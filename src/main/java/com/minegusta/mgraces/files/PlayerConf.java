package com.minegusta.mgraces.files;

import com.google.common.collect.Lists;
import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.race.Race;
import com.minegusta.mgraces.util.StringToRace;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.UUID;

public class PlayerConf
{
    private FileConfiguration conf()
    {
        return PlayerFile.playerFile;
    }


    //All methods for config values.

    public List<UUID> getAllUUID()
    {
        List<UUID> uuidList = Lists.newArrayList();
        for(String s : conf().getKeys(false))
        {
           uuidList.add(UUID.fromString(s));
        }

        return uuidList;
    }

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