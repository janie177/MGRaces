package com.minegusta.mgraces.files;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

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
        if (!conf().isSet("dwarfhealth")) return 20;
        return conf().getInt("dwarfhealth");
    }

    public int demonHealth()
    {
        if (!conf().isSet("demonhealth")) return 20;
        return conf().getInt("demonhealth");
    }

    public int enderBornHealth()
    {
        if (!conf().isSet("enderbornhealth")) return 20;
        return conf().getInt("enderbornhealth");
    }

    public int humanHealth()
    {
        if (!conf().isSet("humanhealth")) return 20;
        return conf().getInt("humanhealth");
    }

    public int elfHealth()
    {
        if (!conf().isSet("elfhealth")) return 20;
        return conf().getInt("elfhealth");
    }

    public int altarBlock()
    {
        if(!conf().isSet("altarblock"))return 133;
        return conf().getInt("altarblock");
    }

    public int altarSecondBlockAmount()
    {
        if(!conf().isSet("altarsecondblockamount"))return 10;
        return conf().getInt("altarsecondblockamount");
    }

    public int altarSecondBlock()
    {
        if(!conf().isSet("altarsecondblock"))return 155;
        return conf().getInt("altarsecondblock");
    }

    public int altarSecondBlockRadius()
    {
        if(!conf().isSet("altarsecondblockradius"))return 6;
        return conf().getInt("altarsecondblockradius");
    }

    public List<ItemStack> cureItems()
    {
        List<ItemStack> list = Lists.newArrayList();
        if(!conf().isSet("cureitems"))
        {
            return list;
        }
        List<String> items = conf().getStringList("cureitems");

        for(String s : items)
        {
            String[] split = s.split(",");
            ItemStack i = new ItemStack(Material.getMaterial(split[0]), Integer.getInteger(split[1]));
            list.add(i);
        }
        return list;
    }

    public String demonChant()
    {
        if(!conf().isSet("demonchant"))return "Wakka Wakka Wakka Wakka Hail PacMan!!";
        return conf().getString("demonchant");
    }
}
