package com.minegusta.mgraces.powerlisteners.demon;

import com.minegusta.mgraces.player.MGPlayer;
import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class WorldWeakness
{
    private UUID uuid;
    private Player p;

    public WorldWeakness(MGPlayer mgp)
    {
        this.uuid = mgp.getUUID();
        this.p = Bukkit.getPlayer(uuid);

        if(isWorld())apply();
    }

    private boolean isWorld()
    {
        return !p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).equals(Biome.HELL);
    }

    private void apply()
    {
        new PermanentPotionEffect(uuid, PotionEffectType.WEAKNESS, 1, 4);
    }
}

