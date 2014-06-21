package com.minegusta.mgraces.player;

import com.minegusta.mgraces.files.PlayerConf;
import org.bukkit.Bukkit;

import java.util.UUID;

public class CreateMGPlayer
{
    private UUID uuid;
    private String pName;
    private PlayerConf conf = new PlayerConf();

    public CreateMGPlayer(UUID uuid)
    {
       this.uuid = uuid;
       pName = Bukkit.getPlayer(uuid).getName();
    }

    public MGPlayer get()
    {
        return new MGPlayer(uuid, pName, conf.getRace(uuid.toString()));
    }
}
