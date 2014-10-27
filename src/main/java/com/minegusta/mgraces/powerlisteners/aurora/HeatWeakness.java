package com.minegusta.mgraces.powerlisteners.aurora;

import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class HeatWeakness
{
    public static void apply(Player p)
    {
        double x = p.getLocation().getBlock().getTemperature();

        if(x >= 2.0)
        {
            PlayEffect.play(p, Effect.WATERDRIP, 1, 1, 1, 15);
            PermanentPotionEffect.apply(p, PotionEffectType.WEAKNESS, 3, 10);
            PermanentPotionEffect.apply(p, PotionEffectType.SLOW, 0, 10);
        }
        else if(x >= 1.0)
        {
            PlayEffect.play(p, Effect.WATERDRIP, 1, 1, 1, 7);
            PermanentPotionEffect.apply(p, PotionEffectType.WEAKNESS, 2, 10);
        }
    }
}
