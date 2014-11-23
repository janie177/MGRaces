package com.minegusta.mgraces.worldguard;


import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class WGManager
{
    public static boolean canPVP(Player p)
    {
        Location loc = p.getLocation();
        ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionManager(p.getWorld()).getApplicableRegions(loc);
        if(set.size() > 0)
        {
            for(ProtectedRegion r : set.getRegions())
            {
                if(r.getFlags().containsKey(DefaultFlag.PVP) && r.getFlag(DefaultFlag.PVP) == StateFlag.State.DENY)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean canPVP(Entity e)
    {
        if(!(e instanceof LivingEntity)) return false;
        Location loc = e.getLocation();
        ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionManager(e.getWorld()).getApplicableRegions(loc);
        if(set.size() > 0)
        {
            for(ProtectedRegion r : set.getRegions())
            {
                if(r.getFlags().containsKey(DefaultFlag.PVP) && r.getFlag(DefaultFlag.PVP) == StateFlag.State.DENY)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean canGetDamage(Player p)
    {
        Location loc = p.getLocation();
        ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionManager(p.getWorld()).getApplicableRegions(loc);
        if(set.size() > 0)
        {
            for(ProtectedRegion r : set.getRegions())
            {
                if(r.getFlags().containsKey(DefaultFlag.INVINCIBILITY) && r.getFlag(DefaultFlag.INVINCIBILITY) == StateFlag.State.ALLOW)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
