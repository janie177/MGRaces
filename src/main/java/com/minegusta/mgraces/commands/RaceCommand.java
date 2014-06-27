package com.minegusta.mgraces.commands;

import com.google.common.collect.Lists;
import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.files.DefaultConfigFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RaceCommand implements CommandExecutor
{
    private Player p;
    private String race;
    private static DefaultConf conf = new DefaultConf();
    private String prefix = ChatColor.translateAlternateColorCodes('&', conf.getPrefix());
    //Command

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args)
    {
        if(!cmd.getName().equalsIgnoreCase("race"))return true;
        if(!(s instanceof Player))return true;

        this.p = (Player) s;

        race = TempData.raceMap.get(p.getUniqueId()).getRace().getName();
        List<String> show = Lists.newArrayList("You are a(n): " + ChatColor.DARK_PURPLE + race);


        //Length of args.
        if(args.length == 0)
        {
            sendList(help);
            return true;
        }

        if(args.length == 1)
        {
            if(args[0].equalsIgnoreCase("show"))
            {
                sendOwnInfo(show);
                return true;
            }

            if(args[0].equalsIgnoreCase("cure"))
            {
                sendList(cure);
                sendCureItems();
                return true;
            }

            if(args[0].equalsIgnoreCase("list"))
            {
                sendList(races);
                return true;
            }

            if(args[0].equalsIgnoreCase("recipes"))
            {
                sendRecipes(recipes);
                return true;
            }

            if(args[0].equalsIgnoreCase("reload") && p.isOp())
            {
                DefaultConfigFile.reloadConfig();
                sendMessage("Config file reloaded!");
                return true;
            }
            sendList(help);
            return true;
        }

        if(args.length > 1)
        {
            if(args[0].equalsIgnoreCase("Info"))
            {
                if(isRace(args[1]))
                {
                    sendInfo(args[1]);
                    return true;
                }
            }

            if(args[0].equalsIgnoreCase("Infect"))
            {
                if(isRace(args[1]) && !(args[1].equalsIgnoreCase("human")))
                {
                    sendList(raceToList.valueOf(args[1].toLowerCase()).getInfectInfo());
                    return true;
                }
            }
            sendList(help);
        }
        return true;
    }

    private void sendRecipes(String[][] s)
    {
        p.sendMessage(ChatColor.GOLD + " - - - - " + ChatColor.YELLOW + "Race Recipes Help" + ChatColor.GOLD + " - - - - ");
        p.sendMessage(prefix + " " + ChatColor.DARK_GRAY + "Note: " + ChatColor.GRAY + "Ingredients do not work when you stack the items.");

        for(int i = 0; i < s.length; i++)
        {
            p.sendMessage(prefix + " " + ChatColor.YELLOW + s[i][1] + ":");

            for(int i2 = 1; i2 < s[i].length; i2++)
            {
                p.sendMessage(prefix + " " + ChatColor.YELLOW + " - " + ChatColor.DARK_PURPLE + s[i][i2]);
            }
        }
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

    private void sendInfo(String race)
    {
        List<String> l = raceToList.valueOf(race.toLowerCase()).get();
        p.sendMessage(ChatColor.GOLD + " - - - - - - - " + ChatColor.YELLOW + race + " Info" + ChatColor.GOLD + " - - - - - - - ");
        for(String s : l)
        {
            p.sendMessage(prefix + ChatColor.YELLOW +  " - " + s);
        }
    }

    private void sendOwnInfo(List<String> show)
    {
        List<String> l = raceToList.valueOf(race.toLowerCase()).get();
        p.sendMessage(ChatColor.GOLD + " - - - - - - - " + ChatColor.YELLOW + race + " Info" + ChatColor.GOLD + " - - - - - - - ");
        for(String ss : show)
        {
            p.sendMessage(prefix + ChatColor.YELLOW + " - " + ss);
        }
        for(String s : l)
        {
            p.sendMessage(prefix + ChatColor.YELLOW +  " - " + s);
        }
    }

    private boolean isRace(String s)
    {
        return s.equalsIgnoreCase("Dwarf") || s.equalsIgnoreCase("Elf") || s.equalsIgnoreCase("human") || s.equalsIgnoreCase("enderborn") || s.equalsIgnoreCase("demon");
    }

    private void sendCureItems()
    {
        List<ItemStack> items = conf.cureItems();
        for(ItemStack i : items)
        {
            p.sendMessage(prefix + ChatColor.YELLOW + " - " + ChatColor.DARK_PURPLE + Integer.toString(i.getAmount()) + " " + i.getType().name());
        }
    }

    private enum raceToList
    {
        elf(elfInfo, elfInfect),
        dwarf(dwarfInfo, dwarfInfect),
        human(humanInfo),
        demon(demonInfo, demonInfect),
        enderborn(enderbornInfo, enderbornInfect);

        private List<String> info;
        private List<String> infect;
        private raceToList(List<String> info, List<String> infect)
        {
            this.info = info;
            this.infect = infect;
        }

        private raceToList(List<String> info)
        {
            this.info = info;
        }

        private List<String> get()
        {
            return info;
        }

        private List<String> getInfectInfo()
        {
            return infect;
        }
    }


    private List<String> help = Lists.newArrayList("/Race Help " + ChatColor.GRAY + "- Show this help menu.", "/Race List " + ChatColor.GRAY + "- Display a list of races.", "/Race Info <Race> " + ChatColor.GRAY + "- Info on the given race.", "/Race Show " + ChatColor.GRAY + "- Show info about your race.", "/Race Cure " + ChatColor.GRAY + "- Display cure info.", "/Race Infect <Race> " + ChatColor.GRAY + "- Show how to become a race.", "/Race Recipes " + ChatColor.GRAY + "- Show all recipes related to races.");
    private List<String> races = Lists.newArrayList("Elf", "Dwarf", "Enderborn", "Demon", "Human");

    private static List<String> elfInfo = Lists.newArrayList("Elves are great using bows.");
    private static List<String> dwarfInfo = Lists.newArrayList("Dwarves are great when using axes.");
    private static List<String> humanInfo = Lists.newArrayList("Humans suck.");
    private static List<String> demonInfo = Lists.newArrayList("Demons are fire resistant.");
    private static List<String> enderbornInfo = Lists.newArrayList("Enderborn are odd creatures.");

    private static List<String> elfInfect = Lists.newArrayList("To become an elf, follow these steps:", "- " + ChatColor.GRAY + "Get 100 mob kills with a bow.", "Craft an Elf Stew, with these ingredients:", ChatColor.DARK_PURPLE + "3 Yellow Flowers", ChatColor.DARK_PURPLE + "1 Mushroom Soup", ChatColor.DARK_PURPLE + "2 Potatoes", ChatColor.DARK_PURPLE + "2 Carrots", ChatColor.DARK_PURPLE + "1 Leaf", "Now eat the stew.");
    private static List<String> dwarfInfect = Lists.newArrayList("To become a dwarf, follow these steps:", "- " + ChatColor.GRAY + "", "- " + ChatColor.GRAY + "Craft a shiny Gem.", "- " + ChatColor.GRAY + "Use the gem on a skeleton.", "- " + ChatColor.GRAY + "Use an axe to kill the angry spirit.");
    private static List<String> demonInfect = Lists.newArrayList("To become a demon, perform these steps: ", "- " + ChatColor.GRAY + "Make a 7 by 7 obsidian cross.", "- " + ChatColor.GRAY + "Put a baby virgin sheep on it.", "Stand exactly in the center.", "Speak the follow lines:", "- " + ChatColor.DARK_PURPLE + conf.demonChant());
    private static List<String> enderbornInfect = Lists.newArrayList("To become an Enderborn, follow these steps:", "- " + ChatColor.GRAY + "Craft a Crystal Eye.", "- " + ChatColor.GRAY + "Right click an enderman.", "- " + ChatColor.GRAY + "Wait for the transfusion to complete." );

    private String[][] recipes = {{"Crystal Eye", "1 Eye of Ender", "4 Diamonds", "4 Emeralds"}, {"Elf Stew", "2 Carrots", "2 Potatoes", "1 Mushroom Soup", "1 Leaf", "3 Yellow Flowers"}, {"Shiny Gem", "4 Gold Bars", "4 Gold Blocks" , "1 Nether Star"}};
    private List<String> cure = Lists.newArrayList("If you want to become human again, follow these steps:", ChatColor.GRAY + "Build an altar out of:", " - " + ChatColor.DARK_PURPLE + "1 " + Material.getMaterial(conf.altarBlock()).name(), " - " + ChatColor.DARK_PURPLE + Integer.toString(conf.altarSecondBlockAmount()) + " " + Material.getMaterial(conf.altarSecondBlock()).name(), "You need the following items in your inventory:");


}
