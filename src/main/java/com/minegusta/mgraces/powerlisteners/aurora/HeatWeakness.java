package com.minegusta.mgraces.powerlisteners.aurora;

import com.minegusta.mgraces.player.MGPlayer;
import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class HeatWeakness
{
    private UUID uuid;
    private Player p;

    public HeatWeakness(MGPlayer mgp)
    {
        this.uuid = mgp.getUUID();
        this.p = Bukkit.getPlayer(uuid);

        apply();

    }

    private void apply()
    {
        double x = p.getLocation().getBlock().getTemperature();

        if(x >= 2.0)
        {
            new PlayEffect(uuid, Effect.WATERDRIP, 1, 1, 1, 15);
            potionEffect(PotionEffectType.WEAKNESS, 3);
            potionEffect(PotionEffectType.SLOW, 0);
        }
        else if(x >= 1.0)
        {
            new PlayEffect(uuid, Effect.WATERDRIP, 1, 1, 1, 7);
            potionEffect(PotionEffectType.WEAKNESS, 2);
        }
    }

    private void potionEffect(PotionEffectType type, int amplifier)
    {
        new PermanentPotionEffect(uuid, type, amplifier, 10);
    }
}
