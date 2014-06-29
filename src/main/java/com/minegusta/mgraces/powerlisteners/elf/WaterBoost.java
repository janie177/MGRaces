package com.minegusta.mgraces.powerlisteners.elf;

import com.minegusta.mgraces.player.MGPlayer;
import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class WaterBoost
{
    private UUID uuid;
    private Player p;

    public WaterBoost(MGPlayer mgp)
    {
        this.uuid = mgp.getUUID();
        this.p = Bukkit.getPlayer(uuid);

        if(isInWater())
        {
            apply();
        }
    }

    private boolean isInWater()
    {
        Material mat = p.getLocation().getBlock().getType();
        return mat.equals(Material.WATER) || mat.equals(Material.STATIONARY_WATER);
    }

    private void apply()
    {
        new PermanentPotionEffect(uuid, PotionEffectType.REGENERATION, 0, 3);
        new PlayEffect(uuid, Effect.HEART, 1, 1, 1, 5);
    }
}
