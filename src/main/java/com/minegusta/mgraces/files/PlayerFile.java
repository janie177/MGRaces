package com.minegusta.mgraces.files;

import org.bukkit.configuration.file.FileConfiguration;

public class PlayerFile
{
    public static FileConfiguration playerFile;

    public static void createPlayerFile()
    {
         playerFile = YamlUtil.getConfiguration("/players/", "playerfile.yml");
    }
}
