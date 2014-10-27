package com.minegusta.mgraces.potioneffects;

import com.minegusta.mgraces.util.WorldCheck;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PermanentPotionEffect
{

    /**
     *
     * @param p The player involved.
     * @param type The type of the potion.
     * @param amplifier The amplifier (0 = 1).
     * @param seconds The duration in seconds.
     */
    public static void apply(Player p, PotionEffectType type, int amplifier, int seconds)
    {

        if (!WorldCheck.worldCheck(p.getWorld())) return;

        for (PotionEffect pe : p.getActivePotionEffects()) {
            if (pe.getType().equals(type) && pe.getAmplifier() <= amplifier) {
                p.removePotionEffect(type);
            }
        }
        p.addPotionEffect(new PotionEffect(type, 20 * seconds, amplifier, false));
    }
}
