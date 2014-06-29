package com.minegusta.mgraces.powerlisteners.demon;

import com.minegusta.mgraces.player.MGPlayer;
import com.minegusta.mgraces.potioneffects.PermanentPotionEffect;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class IceWeakness
{
    private UUID uuid;
    private Player p;

    public IceWeakness(MGPlayer mgp)
    {
        this.uuid = mgp.getUUID();
        this.p = Bukkit.getPlayer(uuid);

        if(isCold())apply();
    }

    private boolean isCold()
    {
        return p.getLocation().getBlock().getTemperature() <= 0.15;
    }

    private void apply()
    {
        new PermanentPotionEffect(uuid, PotionEffectType.SLOW, 0, 3);
        new PlayEffect(uuid, Effect.SNOWBALL_BREAK, 1, 1, 1, 7);
    }
}
