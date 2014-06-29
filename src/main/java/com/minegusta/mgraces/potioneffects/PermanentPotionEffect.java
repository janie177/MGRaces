package com.minegusta.mgraces.potioneffects;

import com.minegusta.mgraces.util.WorldCheck;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class PermanentPotionEffect
{
    private PotionEffectType type;
    private UUID uuid;
    private int amplifier;
    private int seconds;

    public PermanentPotionEffect(UUID uuid, PotionEffectType type, int amplifier, int seconds)
    {
        this.uuid = uuid;
        this.type = type;
        this.amplifier = amplifier;
        this.seconds = seconds;

        apply();
    }

    private void apply() {
        Player p = Bukkit.getPlayer(uuid);
        if (!WorldCheck.worldCheck(p.getWorld())) return;

        for (PotionEffect pe : p.getActivePotionEffects()) {
            if (pe.getType().equals(type)) {
                p.removePotionEffect(type);
            }
        }
        p.addPotionEffect(new PotionEffect(type, 20 * seconds, amplifier, false));
    }
}
