package com.minegusta.mgraces;

import com.google.common.collect.Lists;
import com.minegusta.mgraces.commands.RaceCommand;
import com.minegusta.mgraces.data.LoadToData;
import com.minegusta.mgraces.files.DefaultConfigFile;
import com.minegusta.mgraces.files.PlayerFile;
import com.minegusta.mgraces.listener.RaceListener;
import com.minegusta.mgraces.recipes.Recipes;
import com.minegusta.mgraces.tasks.BoostTask;
import com.minegusta.mgraces.tasks.SaveTask;
import com.minegusta.mgraces.util.TotalRaces;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin
{
    public static Plugin PLUGIN;
    private static List<Integer> list = Lists.newArrayList();
    public static boolean WORLD_GUARD_ENABLED;

    @Override
    public void onEnable()
    {
        //PLUGIN
        PLUGIN = this;

        //Files
        PlayerFile.createPlayerFile();
        DefaultConfigFile.loadConfig();

        //Reload stuff
        LoadToData.loadAllToMap();

        //Tasks
        list.add(SaveTask.saveTask);
        list.add(BoostTask.boostTask);
        list.add(BoostTask.secondBoostTask);

        // Depends
        WORLD_GUARD_ENABLED = Bukkit.getPluginManager().isPluginEnabled("WorldGuard") && Bukkit.getPluginManager().getPlugin("WorldGuard") instanceof WorldGuardPlugin;


        //Recipes
        Recipes.register();

        //Listener
        Bukkit.getServer().getPluginManager().registerEvents(new RaceListener(), this);

        //Commands
        getCommand("race").setExecutor(new RaceCommand());

        //Count races from file
        TotalRaces.listAmounts();
    }

    @Override
    public void onDisable()
    {
        //Unregister all tasks.
        for(int i : list)
        {
            Bukkit.getServer().getScheduler().cancelTask(i);
        }

    }
}
