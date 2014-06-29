package com.minegusta.mgraces.powerlisteners.enderborn;

import com.minegusta.mgraces.player.MGPlayer;
import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class InvisibleBoost
{
    private UUID uuid;

    public InvisibleBoost(MGPlayer mgp)
    {
        this.uuid = mgp.getUUID();

        if(isCrouching())apply();
    }

    private boolean isCrouching()
    {
        return Bukkit.getPlayer(uuid).isSneaking();
    }

    private void apply()
    {
        new PermanentPotionEffect(uuid, PotionEffectType.INVISIBILITY, 0, 2);
        new PlayEffect(uuid, Effect.PARTICLE_SMOKE, 1, 0, 1, 20);
    }
}