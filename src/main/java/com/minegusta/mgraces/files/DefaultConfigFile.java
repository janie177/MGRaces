package com.minegusta.mgraces.files;

import com.minegusta.mgraces.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class DefaultConfigFile
{
    private static Plugin plugin = Main.PLUGIN;

    public static void loadConfig()
    {
        plugin.saveDefaultConfig();
    }

    public static FileConfiguration getConfig()
    {
        return plugin.getConfig();
    }

    public static void reloadConfig()
    {
        plugin.reloadConfig();
    }
}
