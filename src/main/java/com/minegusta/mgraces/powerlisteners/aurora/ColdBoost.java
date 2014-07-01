package com.minegusta.mgraces.powerlisteners.aurora;

import com.minegusta.mgraces.player.MGPlayer;
import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class ColdBoost
{
    private UUID uuid;
    private Player p;

    public ColdBoost(MGPlayer mgp)
    {
        this.uuid = mgp.getUUID();
        this.p = Bukkit.getPlayer(uuid);

        if(isCold())apply();
    }

    private boolean isCold()
    {
        return p.getLocation().getBlock().getTemperature() <= 0.15;
    }

    private void apply()
    {
        new PlayEffect(uuid, Effect.SNOWBALL_BREAK, 1, 0, 1, 12);
        new PermanentPotionEffect(uuid, PotionEffectType.SPEED, 1, 10);
        new PermanentPotionEffect(uuid, PotionEffectType.REGENERATION, 0, 10);
        new PermanentPotionEffect(uuid, PotionEffectType.DAMAGE_RESISTANCE, 1, 10);
        new PermanentPotionEffect(uuid, PotionEffectType.INCREASE_DAMAGE, 0, 10);
    }
}
