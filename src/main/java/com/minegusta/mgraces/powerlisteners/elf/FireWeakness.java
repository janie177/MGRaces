package com.minegusta.mgraces.powerlisteners.elf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.race.Elf;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class FireWeakness
{
    private Entity damaged;
    private EntityDamageEvent.DamageCause cause;
    private EntityDamageEvent e;
    private DefaultConf conf = new DefaultConf();

    public FireWeakness(EntityDamageEvent e)
    {
        this.damaged = e.getEntity();
        this.cause = e.getCause();
        this.e = e;

        if(!e.isCancelled() && isHuman() && isFire() && isElf())
        {
            apply();
        }
    }


    private boolean isFire()
    {
        return cause.equals(EntityDamageEvent.DamageCause.FIRE) || cause.equals(EntityDamageEvent.DamageCause.FIRE_TICK);
    }

    private boolean isHuman()
    {
        return damaged instanceof Player;
    }

    private boolean isElf()
    {
        return TempData.raceMap.get(damaged.getUniqueId()).getRace() instanceof Elf;
    }

    private void apply()
    {
        e.setDamage(e.getDamage() + conf.elfFireDamage());
    }
}
