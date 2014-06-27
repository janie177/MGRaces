package com.minegusta.mgraces.util;

import com.minegusta.mgraces.files.DefaultConf;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MGMessage
{
    private static DefaultConf conf = new DefaultConf();
    private static String prefix = ChatColor.translateAlternateColorCodes('&', conf.getPrefix());

    public static void message(Player p, String text)
    {
        p.sendMessage(prefix + ChatColor.YELLOW + " " + text);
    }

}
