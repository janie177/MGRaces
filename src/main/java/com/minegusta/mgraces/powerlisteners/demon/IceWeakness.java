package com.minegusta.mgraces.powerlisteners.demon;

import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class IceWeakness
{
    /**
     * Show whether the player is in a cold biome.
     * @param p The player involved in the event.
     */
    public static void isCold(Player p)
    {
        if(p.getLocation().getBlock().getTemperature() <= 0.15)
        {
            PermanentPotionEffect.apply(p, PotionEffectType.SLOW, 0, 3);
            PlayEffect.play(p.getUniqueId(), Effect.SNOWBALL_BREAK, 1, 1, 1, 7);
        }
    }
}
