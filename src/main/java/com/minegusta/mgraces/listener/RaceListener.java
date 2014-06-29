package com.minegusta.mgraces.listener;

import com.minegusta.mgraces.cure.CureAltar;
import com.minegusta.mgraces.health.SetHealth;
import com.minegusta.mgraces.infection.demon.DemonInfect;
import com.minegusta.mgraces.infection.dwarf.DwarfInfect;
import com.minegusta.mgraces.infection.dwarf.DwarfInfection;
import com.minegusta.mgraces.infection.elf.ElfInfect;
import com.minegusta.mgraces.infection.elf.ElfKills;
import com.minegusta.mgraces.infection.enderborn.EnderbornInfect;
import com.minegusta.mgraces.misclisteners.LoadToMap;
import com.minegusta.mgraces.misclisteners.RemoveFromMap;
import com.minegusta.mgraces.misclisteners.SwitchWorld;
import com.minegusta.mgraces.powerlisteners.demon.FallDamageBoost;
import com.minegusta.mgraces.powerlisteners.demon.MinionBoost;
import com.minegusta.mgraces.powerlisteners.dwarf.ArrowWeakness;
import com.minegusta.mgraces.powerlisteners.dwarf.AxeBoost;
import com.minegusta.mgraces.powerlisteners.dwarf.BattleCry;
import com.minegusta.mgraces.powerlisteners.dwarf.KillBoost;
import com.minegusta.mgraces.powerlisteners.elf.BowBoost;
import com.minegusta.mgraces.powerlisteners.elf.FireWeakness;
import com.minegusta.mgraces.powerlisteners.elf.FruitBoost;
import com.minegusta.mgraces.powerlisteners.elf.TameBoost;
import com.minegusta.mgraces.powerlisteners.enderborn.BleedBoost;
import com.minegusta.mgraces.powerlisteners.enderborn.MeatBoost;
import com.minegusta.mgraces.util.WorldCheck;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.player.*;

public class RaceListener implements Listener
{
    //World Check

    //Events
    @EventHandler
    public void event(PlayerJoinEvent e)
    {
        //Add them to the map, no matter what world!
        new LoadToMap(e);

        //World Check
        if(!WorldCheck.worldCheck(e.getPlayer().getWorld()))return;
        new SetHealth(e);

    }

    @EventHandler
    public void event(PlayerQuitEvent e)
    {
        //Remove them no matter what world.
        new RemoveFromMap(e);

        //World Check
        if(!WorldCheck.worldCheck(e.getPlayer().getWorld()))return;

    }

    @EventHandler
    public void event(PlayerInteractEntityEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getPlayer().getWorld()))return;
        new EnderbornInfect(e);
        new DwarfInfect(e);
        new BattleCry(e);
    }

    @EventHandler
    public void event(EntityDamageByEntityEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getEntity().getWorld()))return;
        new MinionBoost(e);
        new BleedBoost(e);
        new ArrowWeakness(e);
        new AxeBoost(e);
        new BowBoost(e);
    }

    @EventHandler
    public void event(EntityDamageEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getEntity().getWorld()))return;
        new FallDamageBoost(e);
        new FireWeakness(e);
    }

    @EventHandler
    public void event(EntityDeathEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getEntity().getWorld()))return;

        new ElfKills(e);
        new KillBoost(e);
        new DwarfInfection(e);

    }

    @EventHandler
    public void event(EntityTameEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getEntity().getWorld()))return;

        new TameBoost(e);

    }

    @EventHandler
    public void event(PlayerItemConsumeEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getPlayer().getWorld()))return;
        new FruitBoost(e);
        new ElfInfect(e);
        new MeatBoost(e);
    }

    @EventHandler
    public void event(BlockBreakEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getPlayer().getWorld()))return;
    }

    @EventHandler
    public void event(PlayerInteractEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getPlayer().getWorld()))return;
        new BattleCry(e);
        new CureAltar(e);
    }

    @EventHandler
    public void event(PlayerMoveEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getPlayer().getWorld()))return;
    }

    @EventHandler
    public void event(AsyncPlayerChatEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getPlayer().getWorld()))return;

        new DemonInfect(e);
    }

    @EventHandler
    public void event(PlayerToggleSneakEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getPlayer().getWorld()))return;
    }

    @EventHandler
    public void event(PlayerChangedWorldEvent e)
    {
        new SwitchWorld(e);
    }
}
