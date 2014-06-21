package com.minegusta.mgraces.files;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class DefaultConf
{
    private FileConfiguration conf()
    {
        return DefaultConfigFile.getConfig();
    }

    public List<String> getAllowedWorlds()
    {
        return ((List<String>) conf().getList("enabledworlds"));
    }
}
