package com.minegusta.mgraces.powerlisteners.demon;

import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Effect;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class NetherBoost
{
    public static void isInNether(Player p)
    {
        if(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).equals(Biome.HELL))
        {
            PermanentPotionEffect.apply(p, PotionEffectType.DAMAGE_RESISTANCE, 0, 10);
            PermanentPotionEffect.apply(p, PotionEffectType.JUMP, 2, 10);
            PermanentPotionEffect.apply(p, PotionEffectType.SPEED, 2, 10);
            PermanentPotionEffect.apply(p, PotionEffectType.INCREASE_DAMAGE, 0, 10);
            PlayEffect.play(p, Effect.FLAME, 2, 0, 2, 6);
        }
    }
}
