package com.minegusta.mgraces.powerlisteners.elf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.Elf;
import com.minegusta.mgraces.util.PlayEffect;
import org.bukkit.Effect;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityTameEvent;

public class TameBoost
{
    private AnimalTamer p;
    private LivingEntity e;

    public TameBoost(EntityTameEvent e)
    {
        this.p = e.getOwner();
        this.e = e.getEntity();

        if(!e.isCancelled() && isPlayer() && isElf())
        {
            apply();
        }
    }

    private boolean isPlayer()
    {
        return p instanceof Player;
    }

    private boolean isElf()
    {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof Elf;
    }

    private void apply()
    {
        boolean effect = true;
        switch (e.getType())
        {
            case WOLF:
            {
                Wolf wolf = (Wolf) e;
                wolf.setOwner(p);
            }
                break;
            case OCELOT:
            {
                Ocelot ocelot = (Ocelot) e;
                ocelot.setOwner(p);
            }
            break;
            case HORSE:
            {
                Horse horse = (Horse) e;
                horse.setOwner(p);
            }
            break;
            default:
            {
                effect = false;
            }
            break;
        }

        if(effect)
        {
            new PlayEffect(e, Effect.POTION_SWIRL, 2, 2, 2, 20);
            new PlayEffect(e, Effect.HAPPY_VILLAGER, 2, 2, 2, 10);
        }
    }
}
