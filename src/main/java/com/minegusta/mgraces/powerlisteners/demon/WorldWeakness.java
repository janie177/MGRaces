package com.minegusta.mgraces.powerlisteners.demon;

import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class WorldWeakness
{

    public static void check(UUID uuid)
    {
        Player p = Bukkit.getPlayer(uuid);
        if(!p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).equals(Biome.HELL))PermanentPotionEffect.apply(p, PotionEffectType.WEAKNESS, 1, 10);
    }
}

