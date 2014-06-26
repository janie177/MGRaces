package com.minegusta.mgraces.listener;

import com.minegusta.mgraces.infection.demon.DemonInfect;
import com.minegusta.mgraces.infection.elf.ElfInfect;
import com.minegusta.mgraces.infection.elf.ElfKills;
import com.minegusta.mgraces.infection.enderborn.EnderbornInfect;
import com.minegusta.mgraces.misclisteners.LoadToMap;
import com.minegusta.mgraces.misclisteners.RemoveFromMap;
import com.minegusta.mgraces.misclisteners.SwitchWorld;
import com.minegusta.mgraces.util.WorldCheck;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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

    }

    @EventHandler
    public void event(EntityDeathEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getEntity().getWorld()))return;

        new ElfKills(e);

    }

    @EventHandler
    public void event(PlayerItemConsumeEvent e)
    {
        //World Check
        if(!WorldCheck.worldCheck(e.getPlayer().getWorld()))return;

        new ElfInfect(e);

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
