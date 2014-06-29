package com.minegusta.mgraces.infection.elf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.util.MGMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;

public class ElfKills {

    private UUID uuid;
    private Entity killer;
    private EntityDamageEvent.DamageCause cause;
    private Player p;

    public ElfKills(EntityDeathEvent e)
    {
        this.killer = e.getEntity().getKiller();
        this.cause = e.getEntity().getLastDamageCause().getCause();

        if(isArrow() && isPlayer())
        {
            addKill();
        }
    }

    private boolean isPlayer()
    {
        if(killer instanceof Player && TempData.raceMap.get((killer).getUniqueId()).getRace() instanceof Human)
        {
            uuid = killer.getUniqueId();
            p = (Player) killer;
            return true;
        }
        return false;
    }

    private boolean isArrow()
    {
        return cause.equals(EntityDamageEvent.DamageCause.PROJECTILE);
    }

    private void addKill()
    {
        if (TempData.elfKills.containsKey(uuid))
        {
            int kills = TempData.elfKills.get(uuid);
            TempData.elfKills.put(uuid, kills + 1);
            if(kills % 5 == 0) MGMessage.message(p, "You have killed " + ChatColor.AQUA + Integer.toString(kills) + ChatColor.YELLOW + " enemies by bow!");
        }
        else
        {
            TempData.elfKills.put(uuid, 1);
        }
    }
}
