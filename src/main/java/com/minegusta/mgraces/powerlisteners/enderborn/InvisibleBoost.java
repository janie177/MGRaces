package com.minegusta.mgraces.powerlisteners.enderborn;

import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class InvisibleBoost{

    public static void apply(Player p)
    {
        if(p.isSneaking())PermanentPotionEffect.apply(p, PotionEffectType.INVISIBILITY, 0, 3);
    }
}
