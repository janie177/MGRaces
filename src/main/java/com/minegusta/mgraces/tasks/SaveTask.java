package com.minegusta.mgraces.tasks;

import com.minegusta.mgraces.Main;
import com.minegusta.mgraces.files.PlayerFile;
import com.minegusta.mgraces.files.YamlUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SaveTask
{


    public static int saveTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
        @Override
        public void run()
        {
            YamlUtil.saveFile("/players/", "playerfile.yml", PlayerFile.playerFile);
        }
    }, 0 , 20 * 60);
}
