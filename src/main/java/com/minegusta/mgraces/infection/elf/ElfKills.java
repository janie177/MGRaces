package com.minegusta.mgraces.infection.elf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.util.MGMessage;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;

public class ElfKills {

    private UUID uuid;
    private Entity killer;
    private Player p;

    public ElfKills(EntityDeathEvent e)
    {
        this.killer = e.getEntity().getKiller();

        if(isArrow() && isPlayer())
        {
            addKill();
        }
    }

    private boolean isPlayer()
    {
        Arrow arrow = (Arrow) killer;
        if(arrow.getShooter() instanceof Player && TempData.raceMap.get(((Player) arrow.getShooter()).getUniqueId()).getRace() instanceof Human)
        {
            uuid = ((Player) arrow.getShooter()).getUniqueId();
            p = (Player) arrow.getShooter();
            return true;
        }
        return false;
    }

    private boolean isArrow()
    {
        return killer instanceof Arrow;
    }

    private void addKill()
    {
        if (TempData.elfKills.containsKey(uuid))
        {
            int kills = TempData.elfKills.get(uuid);
            TempData.elfKills.put(uuid, kills + 1);
            if(kills == 99) MGMessage.message(p, "You have killed 100 enemies by bow!");
        }
        else
        {
            TempData.elfKills.put(uuid, 1);
        }
    }
}
