package com.minegusta.mgraces.powerlisteners.dwarf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.race.Dwarf;
import com.minegusta.mgraces.util.CoolDown;
import com.minegusta.mgraces.util.MGMessage;
import com.minegusta.mgraces.util.PlayEffect;
import com.minegusta.mgraces.worldguard.WGManager;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BattleCry
{
    private Player p;
    private ItemStack s;
    private int[] axes = {258, 271, 275, 279, 286};
    private DefaultConf conf = new DefaultConf();

    public BattleCry(PlayerInteractEvent e)
    {
        this.p = e.getPlayer();
        this.s = p.getItemInHand();

        if(!e.isCancelled() && (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)|| e.getAction().equals(Action.RIGHT_CLICK_AIR)))
        {
            run();
        }
    }

    public BattleCry(PlayerInteractEntityEvent e)
    {
        this.p = e.getPlayer();
        this.s = p.getItemInHand();

        if(!e.isCancelled())
        {
            run();
        }
    }

    private boolean canPVP()
    {
        return WGManager.canPVP(p);
    }

    private void run()
    {
        if(hasAxe() && canPVP() && isDwarf())
        {
            apply();
        }
    }

    private boolean hasAxe()
    {
        if(s == null)return false;
        for(int i : axes)
        {
            if(i == s.getTypeId())return true;
        }
        return false;
    }

    private boolean isDwarf()
    {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof Dwarf;
    }

    private void apply()
    {
        if(!CoolDown.cooledDown(p.getUniqueId(), TempData.battleCryMap, conf.battleCryCooldown()))
        {
            MGMessage.message(p, "You have to wait another " + CoolDown.getRemainingTime(p.getUniqueId(), TempData.battleCryMap) + " seconds to use battlecry.");
            return;
        }
        CoolDown.newCooldown(p.getUniqueId(), TempData.battleCryMap);
        PlayEffect.play(p, Effect.VILLAGER_THUNDERCLOUD, 2, 2, 2, 20);
        p.sendMessage(ChatColor.RED + "You knock back your enemies!");
        for(Entity e : p.getNearbyEntities(3.0, 3.0, 3.0))
        {
            if(!(e instanceof LivingEntity)) return;
            LivingEntity le = (LivingEntity) e;
            if(le instanceof Player) MGMessage.message((Player) le, "You were knocked back by an angry dwarf!");
            le.getWorld().spigot().playEffect(le.getLocation(), Effect.CRIT, 0, 0, 1, 1, 1, 1, 15, 20);
            le.getWorld().playSound(le.getLocation(), Sound.ANVIL_USE, 1, 1);
            le.setVelocity(le.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(conf.battleCryStrength()));
        }
    }
}
