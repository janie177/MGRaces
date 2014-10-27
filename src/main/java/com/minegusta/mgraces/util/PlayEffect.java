package com.minegusta.mgraces.util;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayEffect
{
    /**
     * Play an effect on a player.
     * @param uuid The UUID of the Entity.
     * @param effect The particle effect to play.
     * @param x The x offset.
     * @param y The y offset.
     * @param z The z offset.
     * @param amount The amount of particles.
     */
    public static void play(UUID uuid, Effect effect, int x, int y, int z, int amount)
    {
        Player p = Bukkit.getPlayer(uuid);
        p.getWorld().spigot().playEffect(p.getLocation(), effect, 1, 1, x, y, z, 1, amount, 15);
    }

    public static void play(LivingEntity e, Effect effect, int x, int y, int z, int amount)
    {
        e.getWorld().spigot().playEffect(e.getLocation(), effect, 1, 1, x, y, z, 1, amount, 15);
    }


}
