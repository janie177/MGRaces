package com.minegusta.mgraces.infection.aurora;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.infection.MakeRace;
import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.util.MGMessage;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class AuroraInfect
{
    private Player p;
    private EntityDamageEvent.DamageCause cause;
    private ItemStack crystal;

    
    //Constructor.
    
    public AuroraInfect(PlayerDeathEvent e)
    {
        this.p = e.getEntity();
        this.cause = e.getEntity().getLastDamageCause().getCause();
        this.crystal = p.getItemInHand();

        if(isDrown() && isHuman() && isCrystal() && isIceBiome())
        {
            apply();
        }
    }
    
    //Boolean methods.

    private boolean isCrystal()
    {
        return crystal != null && crystal.getType().equals(Material.MUSHROOM_SOUP) && crystal.hasItemMeta() && crystal.getItemMeta().hasLore() && crystal.getItemMeta().getLore().toString().contains("Ice Crystal");
    }

    private boolean isHuman()
    {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof Human;
    }

    private boolean isIceBiome()
    {
        return p.getLocation().getBlock().getTemperature() <= 0.15;
    }

    private boolean isDrown()
    {
        return cause.equals(EntityDamageEvent.DamageCause.DROWNING);
    }
    
    //Void methods.

    private void removeCrystal()
    {
        if(p.getItemInHand().getAmount() == 1)
        {
            p.setItemInHand(new ItemStack(Material.AIR));
            return;
        }
        p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
        p.updateInventory();
    }

    private void apply()
    {
        p.getWorld().spigot().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 1, 1, 3, 2, 3, 1,  15 , 25);
        p.getWorld().playSound(p.getLocation(), Sound.STEP_SNOW, 1, 1);

        MGMessage.message(p, "You feel all heat leave your body, and are now an Aurora!");
        MakeRace.makeRace(p.getUniqueId(), "aurora");
        removeCrystal();
    }

}
