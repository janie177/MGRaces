package com.minegusta.mgraces.infection.enderborn;

import com.minegusta.mgraces.Main;
import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.infection.MakeRace;
import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.util.MGMessage;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EnderbornInfect
{
    private Player p;
    private Entity entity;

    public EnderbornInfect(PlayerInteractEntityEvent e)
    {
        this.p = e.getPlayer();
        this.entity = e.getRightClicked();

        if(hasCrystal() && isEnderMan() && isHuman())
        {
            e.setCancelled(true);
            removeCrystal();
            startMerge();
        }
    }

    private boolean isHuman()
    {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof Human;
    }

    private boolean isEnderMan()
    {
        return entity instanceof Enderman;
    }

    private boolean hasCrystal()
    {
        ItemStack s = p.getItemInHand();
        return s != null && s.getType().equals(Material.EYE_OF_ENDER) && s.getItemMeta().hasLore() && s.getItemMeta().getLore().toString().contains("Crystal Eye");
    }

    //Make enderborn.

    private void startMerge()
    {
        for (int i = 0; i < 20 * 10; i++) {
            final int k = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {

                @Override
                public void run()
                {
                    if(k % 20 == 0)
                    {
                        if(p.getLocation().distance(entity.getLocation()) > 4)
                        {
                            MGMessage.message(p, "You are too far from your target to complete the soul merge.");
                            return;
                        }
                        MGMessage.message(p, (10 - (k/20)) + " Seconds remaining till soul-transfusion is completed.");
                    }
                    enderbornEffect(entity, k);
                    enderbornEffect(p, k);
                    if(k == 199)
                    {
                        p.getWorld().createExplosion(entity.getLocation(), 0, false);
                        p.getWorld().strikeLightning(entity.getLocation());
                        entity.remove();
                        makeEnderBorn();
                    }
                }

            }, i);
        }
    }

    private void makeEnderBorn()
    {
        p.getWorld().spigot().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1, 1, 3, 2, 3, 1,  15 , 25);
        p.getWorld().playSound(p.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 1);

        MGMessage.message(p, "You merged souls with the enderman and are now Enderborn!");
        MakeRace.makeRace(p.getUniqueId(), "enderborn");
    }

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

    private void enderbornEffect(Entity e, int i)
    {
        e.getWorld().spigot().playEffect(e.getLocation(), Effect.PORTAL, 1, 1, i/80, i/80, i/80, 1, i / 2, 25);
    }
}
