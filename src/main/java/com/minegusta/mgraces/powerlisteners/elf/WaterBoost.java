package com.minegusta.mgraces.powerlisteners.elf;

import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class WaterBoost
{
    public static void apply(Player p)
    {
        Material mat = p.getLocation().getBlock().getType();
        if(mat.equals(Material.WATER) || mat.equals(Material.STATIONARY_WATER))
        {
            PermanentPotionEffect.apply(p, PotionEffectType.REGENERATION, 0, 3);
            PlayEffect.play(p, Effect.HEART, 1, 1, 1, 5);
        }
    }
}
