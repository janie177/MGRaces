package com.minegusta.mgraces.powerlisteners.demon;

import com.minegusta.mgraces.player.MGPlayer;
import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class NetherBoost
{
    private UUID uuid;
    private Player p;

    public NetherBoost(MGPlayer mgp)
    {
        this.uuid = mgp.getUUID();
        this.p = Bukkit.getPlayer(uuid);

        if(isInNether())apply();
    }

    private boolean isInNether()
    {
        return p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).equals(Biome.HELL);
    }

    private void apply()
    {
        new PermanentPotionEffect(uuid, PotionEffectType.DAMAGE_RESISTANCE, 0, 10);
        new PermanentPotionEffect(uuid, PotionEffectType.JUMP, 2, 10);
        new PermanentPotionEffect(uuid, PotionEffectType.SPEED, 2, 10);
        new PermanentPotionEffect(uuid, PotionEffectType.INCREASE_DAMAGE, 0, 10);
        new PlayEffect(uuid, Effect.FLAME, 2, 0, 2, 6);
    }
}
