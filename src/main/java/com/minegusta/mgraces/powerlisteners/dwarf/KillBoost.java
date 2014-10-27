package com.minegusta.mgraces.powerlisteners.dwarf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.Dwarf;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class KillBoost
{
    private Entity killer;
    private int[] axes = {258, 271, 275, 279, 286};
    private Entity killed;

    public KillBoost(EntityDeathEvent e)
    {
        this.killed = e.getEntity();
        this.killer = e.getEntity().getKiller();

        if(killerIsPlayer() && victimIsLiving() && killerIsDwarf() && hasAxe())
        {
            apply();
        }
    }

    private boolean killerIsPlayer()
    {
        return killer instanceof Player;
    }

    private boolean killerIsDwarf()
    {
        return TempData.raceMap.get(killer.getUniqueId()).getRace() instanceof Dwarf;
    }

    private boolean victimIsLiving()
    {
        return killed instanceof LivingEntity;
    }

    private boolean hasAxe()
    {
        if(((Player)killer).getItemInHand() == null) return false;
        for(int i : axes)
        {
            if(i == ((Player)killer).getItemInHand().getTypeId())return true;
        }
        return false;
    }

    private void apply()
    {
        ((Player)killer).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 4, 1));
        PlayEffect.play((Player)killer, Effect.VILLAGER_THUNDERCLOUD, 1, 1, 1, 10);
    }
}
