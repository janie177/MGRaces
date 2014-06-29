package com.minegusta.mgraces.util;

import com.minegusta.mgraces.files.PlayerConf;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayEffect
{
    public PlayEffect(UUID uuid, Effect effect, int x, int y, int z, int amount)
    {
        Player p = Bukkit.getPlayer(uuid);
        p.getWorld().spigot().playEffect(p.getLocation(), effect, 1, 1, x, y, z, 1, amount, 15);
    }

    public PlayEffect(LivingEntity e, Effect effect, int x, int y, int z, int amount)
    {
        e.getWorld().spigot().playEffect(e.getLocation(), effect, 1, 1, x, y, z, 1, amount, 15);
    }


}
