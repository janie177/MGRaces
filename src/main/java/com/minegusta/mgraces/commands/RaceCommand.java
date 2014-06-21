package com.minegusta.mgraces.commands;

import com.google.common.collect.Lists;
import com.minegusta.mgraces.files.DefaultConf;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RaceCommand implements CommandExecutor
{
    private Player p;
    private DefaultConf conf = new DefaultConf();
    private String prefix = ChatColor.translateAlternateColorCodes('&', conf.getPrefix());


    //Lists

    List<String> help = Lists.newArrayList("");



    //Command


    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args)
    {
        if(!cmd.getName().equalsIgnoreCase("race"))return true;
        if(!(s instanceof Player))return true;

        this.p = (Player) s;

        //Length of args.
        if(args.length == 0)
        {

        }

        if(args.length > 0)
        {

        }
        return true;
    }


    private void sendMessage(String s)
    {
        p.sendMessage(ChatColor.GOLD + " - - - - - - - " + ChatColor.YELLOW + "Races Help" + ChatColor.GOLD + " - - - - - - - ");
        p.sendMessage(prefix + " " + ChatColor.YELLOW + s);
    }

    private void sendList(List<String> l)
    {
        p.sendMessage(ChatColor.GOLD + " - - - - - - - " + ChatColor.YELLOW + "Races Help" + ChatColor.GOLD + " - - - - - - - ");
        for(String s : l)
        {
            p.sendMessage(prefix + " " + ChatColor.YELLOW + s);
        }
    }
}
