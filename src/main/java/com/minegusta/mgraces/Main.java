package com.minegusta.mgraces;

import com.google.common.collect.Lists;
import com.minegusta.mgraces.commands.RaceCommand;
import com.minegusta.mgraces.data.LoadToData;
import com.minegusta.mgraces.files.DefaultConfigFile;
import com.minegusta.mgraces.files.PlayerFile;
import com.minegusta.mgraces.listener.RaceListener;
import com.minegusta.mgraces.tasks.SaveTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin
{
    public static Plugin PLUGIN;
    private static List<Integer> list = Lists.newArrayList();

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

        //Listener
        Bukkit.getServer().getPluginManager().registerEvents(new RaceListener(), this);

        //Commands
        getCommand("race").setExecutor(new RaceCommand());
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
