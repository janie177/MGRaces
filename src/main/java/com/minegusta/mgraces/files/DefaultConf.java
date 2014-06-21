package com.minegusta.mgraces.files;

import org.bukkit.ChatColor;
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

    public String getPrefix()
    {
        if(!conf().isSet("commandprefix"))return ChatColor.GOLD + "[MG] ";
        return conf().getString("commandprefix");
    }

    public int dwarfHealth() 
    {
        if (!conf().isSet("dwarfhealth")) return 10;
        return conf().getInt("dwarfhealh");
    }

    public int demonHealth()
    {
        if (!conf().isSet("demonhealth")) return 10;
        return conf().getInt("demonhealh");
    }

    public int enderBornHealth()
    {
        if (!conf().isSet("enderbornhealth")) return 10;
        return conf().getInt("enderbornhealh");
    }

    public int humanHealth()
    {
        if (!conf().isSet("humanhealth")) return 10;
        return conf().getInt("humanhealh");
    }

    public int elfHealth()
    {
        if (!conf().isSet("elfhealth")) return 10;
        return conf().getInt("elfhealh");
    }
}
