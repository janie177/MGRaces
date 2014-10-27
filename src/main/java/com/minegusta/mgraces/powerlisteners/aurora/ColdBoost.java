package com.minegusta.mgraces.powerlisteners.aurora;

import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class ColdBoost
{

    public static void apply(Player p)
    {
        if(p.getLocation().getBlock().getTemperature() <= 0.15)
        {
            PlayEffect.play(p, Effect.SNOWBALL_BREAK, 1, 0, 1, 12);
            PermanentPotionEffect.apply(p, PotionEffectType.SPEED, 1, 10);
            PermanentPotionEffect.apply(p, PotionEffectType.REGENERATION, 0, 10);
            PermanentPotionEffect.apply(p, PotionEffectType.DAMAGE_RESISTANCE, 1, 10);
            PermanentPotionEffect.apply(p, PotionEffectType.INCREASE_DAMAGE, 0, 10);
        }
    }
}
