package com.minegusta.mgraces.util;

import com.minegusta.mgraces.files.DefaultConf;
import org.bukkit.World;

public class WorldCheck
{
    private static DefaultConf conf = new DefaultConf();

    public static boolean worldCheck(World w)
    {
        if(conf.getAllowedWorlds().contains(w.getName().toLowerCase()))
        {
            return true;
        }
        return false;
    }
}
