package com.minegusta.mgraces.infection.dwarf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.infection.MakeRace;
import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.util.MGMessage;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DwarfInfection
{

    private int[] axes = {258, 271, 275, 279, 286};
    private Entity killer;
    private Player p;
    private Entity killed;

    public DwarfInfection(EntityDeathEvent e)
    {
        this.killed = e.getEntity();
        this.killer = e.getEntity().getKiller();

        if(isSkeleton() && isPlayer() && isHuman() && isAxe() && isSpirit())
        {
            makeDwarf();
        }
    }

    private boolean isPlayer()
    {
        if(killer instanceof Player)
        {
            p = (Player) killer;
            return true;
        }
        return false;
    }

    private boolean isSkeleton()
    {
        return killed instanceof Skeleton;
    }

    private boolean isSpirit()
    {
        Skeleton skeleton = (Skeleton) killed;

        for(ItemStack i : skeleton.getEquipment().getArmorContents())
        {
            if(!i.getType().equals(Material.AIR) && i.hasItemMeta() && i.getItemMeta().hasDisplayName() && i.getItemMeta().getDisplayName().contains("DwarfBane"))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isAxe() {
        for (int i : axes) {
            if (p.getItemInHand().getTypeId() == i) return true;
        }
        return false;
    }

    private boolean isHuman()
    {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof Human;
    }


    //Void Methods

    private void removeArmour()
    {
        for(ItemStack i : ((Skeleton) killed).getEquipment().getArmorContents())
        {
            if(!i.equals(new ItemStack(Material.AIR)))
            {
                i.setType(Material.AIR);
            }
        }
        ((Skeleton) killed).getEquipment().setItemInHand(new ItemStack(Material.AIR));
    }

    private void makeDwarf()
    {
        p.getWorld().spigot().playEffect(p.getLocation(), Effect.VILLAGER_THUNDERCLOUD, 1, 1, 3, 2, 3, 1, 20, 25);
        p.getWorld().playSound(p.getLocation(), Sound.ANVIL_USE, 1, 1);

        removeArmour();
        MGMessage.message(p, "You are now one of the dwarves, MineCraftsman of the mountain halls.");
        MakeRace.makeRace(p.getUniqueId(), "dwarf");
    }
}
