package com.minegusta.mgraces.infection.elf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.infection.MakeRace;
import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.util.MGMessage;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class ElfInfect
{
    private Player p;
    private ItemStack stew;

    public ElfInfect(PlayerItemConsumeEvent e)
    {
        this.stew = e.getItem();
        this.p = e.getPlayer();

        if(isStew() && isHuman())
        {
            if(hasKills())
            {
                makeElf();
            }
            else
            {
                MGMessage.message(p, "You need another " + getKills() + " bow kills to become an elf.");
            }

        }
    }

    //Boolean methods

    private boolean isHuman()
    {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof Human;
    }

    private boolean hasKills()
    {
        return TempData.elfKills.containsKey(p.getUniqueId()) && TempData.elfKills.get(p.getUniqueId()) > 99;
    }

    private boolean isStew()
    {
        return stew != null && stew.getType().equals(Material.MUSHROOM_SOUP) && stew.hasItemMeta() && stew.getItemMeta().hasLore() && stew.getItemMeta().getLore().toString().contains("Elf Stew");
    }

    private int getKills()
    {
        if(!TempData.elfKills.containsKey(p.getUniqueId()))return 100;
        return 100 - TempData.elfKills.get(p.getUniqueId());
    }

    //Void methods.

    private void makeElf()
    {
        p.getWorld().spigot().playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 1, 1, 3, 2, 3, 1, 30, 25);
        p.getWorld().playSound(p.getLocation(), Sound.ARROW_HIT, 1, 1);
        MGMessage.message(p, "You are now an elf!");
        MakeRace.makeRace(p.getUniqueId(), "elf");
        TempData.elfKills.remove(p.getUniqueId());
    }
}
