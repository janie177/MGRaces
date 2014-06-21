package com.minegusta.mgraces.misclisteners;

import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class LoadToMap
{
    private UUID uuid;

    public LoadToMap(PlayerJoinEvent e)
    {
        this.uuid = e.getPlayer().getUniqueId();
    }
}
