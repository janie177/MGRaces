package com.minegusta.mgraces.worldguard;


import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class WGManager
{
    public static boolean canPVP(Player p)
    {
        Location loc = p.getLocation();
        return WorldGuardPlugin.inst().getRegionManager(p.getWorld()).getApplicableRegions(loc).allows(DefaultFlag.PVP);
    }

    public static boolean canPVP(Entity e)
    {
        if(!(e instanceof LivingEntity)) return false;
        Location loc = e.getLocation();
        return WorldGuardPlugin.inst().getRegionManager(e.getWorld()).getApplicableRegions(loc).allows(DefaultFlag.PVP);
    }

    public static boolean canGetDamage(Player p)
    {
        Location loc = p.getLocation();
        return !(WorldGuardPlugin.inst().getRegionManager(p.getWorld()).getApplicableRegions(loc).allows(DefaultFlag.INVINCIBILITY));
    }
}
