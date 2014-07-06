package com.minegusta.mgraces.commands;

import com.google.common.collect.Lists;
import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.files.DefaultConfigFile;
import com.minegusta.mgraces.files.PlayerConf;
import com.minegusta.mgraces.files.PlayerFile;
import com.minegusta.mgraces.util.TotalRaces;
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
            if(args[0].equalsIgnoreCase("amount"))
            {
                sendAmount();
                return true;
            }

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
                conf = new DefaultConf();
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
            p.sendMessage(prefix + " " + ChatColor.YELLOW + s[i][0] + ":");

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

    private void sendAmount()
    {
        p.sendMessage(ChatColor.GOLD + " - - - - - " + ChatColor.YELLOW + race + "Total Amount of Races" + ChatColor.GOLD + " - - - - - ");
        for(String s : TotalRaces.get())
        {
            p.sendMessage(prefix + ChatColor.YELLOW + " - " + s);
        }
    }

    private boolean isRace(String s)
    {
        return s.equalsIgnoreCase("Dwarf") || s.equalsIgnoreCase("Elf") || s.equalsIgnoreCase("human") || s.equalsIgnoreCase("enderborn") || s.equalsIgnoreCase("demon") || s.equalsIgnoreCase("aurora");
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
        aurora(auroraInfo, auroraInfect),
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


    private List<String> help = Lists.newArrayList("/Race Help " + ChatColor.GRAY + "- Show this help menu.", "/Race Amount " + ChatColor.GRAY + "- Show how many people there are per race.", "/Race List " + ChatColor.GRAY + "- Display a list of races.", "/Race Info <Race> " + ChatColor.GRAY + "- Info on the given race.", "/Race Show " + ChatColor.GRAY + "- Show info about your race.", "/Race Cure " + ChatColor.GRAY + "- Display cure info.", "/Race Infect <Race> " + ChatColor.GRAY + "- Show how to become a race.", "/Race Recipes " + ChatColor.GRAY + "- Show all recipes related to races.");
    private List<String> races = Lists.newArrayList("Elf", "Dwarf", "Enderborn", "Demon", "Human", "Aurora");

    private static List<String> elfInfo = Lists.newArrayList("Elves always have a speed boost.", "Elves have " + ChatColor.RED + Integer.toString(conf.elfHealth()/2) + ChatColor.YELLOW + " hearts health.", "Elves get 15 seconds of speed II when eating vegetarian food.", "When in water, elves regenerate health.", "Elves can instantly tame any animal.", "Elves take additional damage from fire.", "They are skilled archers and deal additional bow damage.");
    private static List<String> dwarfInfo = Lists.newArrayList("Dwarves always have a mining boost.", "Dwarves have " + ChatColor.RED + Integer.toString(conf.dwarfHealth()/2) + ChatColor.YELLOW + " hearts health.", "When slaying a foe by axe, Dwarves get a Strength II boost.", "Dwarves are weak to arrows.", "When using an axe, Dwarves deal additional damage.", "When right clicking with an axe, Dwarves knock back all enemies.");
    private static List<String> humanInfo = Lists.newArrayList("Humans have " + ChatColor.RED + Integer.toString(conf.humanHealth()/2) + ChatColor.YELLOW + " hearts health.", "They have no special powers, nor weaknesses.");
    private static List<String> demonInfo = Lists.newArrayList("Demons are immune to fire and lava.", "Demons have " + ChatColor.RED + Integer.toString(conf.demonHealth()/2) + ChatColor.YELLOW + " hearts health.", "When demons have low health, they summon minions to aid them.", "Demons are very strong in the Nether.", "In the Overworld, Demons are very weak.", "In ice biomes, Demons are even weaker.", "Water is also a weakness, and will harm Demons.");
    private static List<String> enderbornInfo = Lists.newArrayList("Enderborn always have night vision and a jump boost.", "Enderborn have " + ChatColor.RED + Integer.toString(conf.enderBornHealth()/2) + ChatColor.YELLOW + " hearts health.", "When Enderborn eat raw meat, they gain a short speed IV boost.", "Enderborn vanish in shadow while sneaking.", "Enderborn have a chance to make opponents bleed.", "Enderborn will be hurt by touching water or rain.");
    private static List<String> auroraInfo = Lists.newArrayList("Aurora have " + ChatColor.RED + Integer.toString(conf.auroraHealth()/2) + ChatColor.YELLOW + " hearts health.", "When in an ice biome, aurora get boosts.", "However in the heat, they are weakened.", "Aurora take no fall damage on snow.", "While in water, aurora shoot forward when pressing the crouch key.");

    private static List<String> auroraInfect = Lists.newArrayList("To become Aurora, follow these steps:", "- " + ChatColor.GRAY + "Craft an Ice Crystal.", "- " + ChatColor.GRAY + "Find water in an ice biome.", "- " + ChatColor.GRAY + "Drown with the crystal in your hand.");
    private static List<String> elfInfect = Lists.newArrayList("To become an Elf, follow these steps:", "- " + ChatColor.GRAY + "Get 100 mob kills with a bow.", "Craft an Elf Stew, with these ingredients:", ChatColor.DARK_PURPLE + "3 Yellow Flowers", ChatColor.DARK_PURPLE + "1 Mushroom Soup", ChatColor.DARK_PURPLE + "2 Potatoes", ChatColor.DARK_PURPLE + "2 Carrots", ChatColor.DARK_PURPLE + "1 Leaf", "Now eat the stew.");
    private static List<String> dwarfInfect = Lists.newArrayList("To become a Dwarf, follow these steps:", "- " + ChatColor.GRAY + "", "- " + ChatColor.GRAY + "Craft a shiny Gem.", "- " + ChatColor.GRAY + "Use the gem on a skeleton.", "- " + ChatColor.GRAY + "Use an axe to kill the angry spirit.");
    private static List<String> demonInfect = Lists.newArrayList("To become a Demon, perform these steps: ", "- " + ChatColor.GRAY + "Make a 7 by 7 obsidian cross.", "- " + ChatColor.GRAY + "Put a baby virgin sheep on it.", "Stand exactly in the center.", "Speak the follow lines:", "- " + ChatColor.DARK_PURPLE + conf.demonChant());
    private static List<String> enderbornInfect = Lists.newArrayList("To become an Enderborn, follow these steps:", "- " + ChatColor.GRAY + "Craft a Crystal Eye.", "- " + ChatColor.GRAY + "Right click an enderman.", "- " + ChatColor.GRAY + "Wait for the transfusion to complete." );

    private String[][] recipes = {{"Crystal Eye", "1 Eye of Ender", "4 Diamonds", "4 Emeralds"}, {"Elf Stew", "2 Carrots", "2 Potatoes", "1 Mushroom Soup", "1 Leaf", "3 Dandellions"}, {"Shiny Gem", "4 Gold Bars", "4 Gold Blocks" , "1 Nether Star"}, {"Ice Crystal", "1 Diamond", "4 Snowballs", "4 Snow Blocks"}};
    private List<String> cure = Lists.newArrayList("If you want to become human again, follow these steps:", ChatColor.GRAY + "Build an altar out of:", " - " + ChatColor.DARK_PURPLE + "1 " + Material.getMaterial(conf.altarBlock()).name(), " - " + ChatColor.DARK_PURPLE + Integer.toString(conf.altarSecondBlockAmount()) + " " + Material.getMaterial(conf.altarSecondBlock()).name(), "You need the following items in your inventory:");
}
