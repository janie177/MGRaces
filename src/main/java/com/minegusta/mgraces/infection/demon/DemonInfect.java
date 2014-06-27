package com.minegusta.mgraces.infection.demon;

import com.minegusta.mgraces.Main;
import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.files.PlayerConf;
import com.minegusta.mgraces.infection.MakeRace;
import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.util.MGMessage;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class DemonInfect {
    private Player p;
    private DefaultConf conf = new DefaultConf();
    private String chant = conf.demonChant();
    private String message;
    private LivingEntity sheep;

    public DemonInfect(AsyncPlayerChatEvent e) {
        this.p = e.getPlayer();
        this.message = e.getMessage();

        if(isChant() && isHuman() && hasSheep() && isCircle())
        {
            makeDemon();
        }
    }

    private boolean isCircle() {
        Block center = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if (isObsidian(center)) {
            Block start1 = center.getRelative(BlockFace.EAST, 3);
            Block start2 = center.getRelative(BlockFace.SOUTH, 3);

            if (isLine(start1, BlockFace.WEST) && isLine(start2, BlockFace.NORTH)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLine(Block b, BlockFace face) {
        for (int i = 0; i < 7; i++) {
            if (!isObsidian(b.getRelative(face, i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isObsidian(Block b) {
        return b.getType().equals(Material.OBSIDIAN);
    }

    private boolean hasSheep() {
        for (Entity e : p.getNearbyEntities(3, 0, 3)) {
            if (e instanceof Sheep) {
                sheep = (LivingEntity) e;
                return true;
            }
        }
        return false;
    }

    private boolean isHuman() {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof Human;
    }

    private boolean isChant() {
        return chant.equalsIgnoreCase(message);
    }

    //make

    private void makeDemon() {
        p.getWorld().spigot().playEffect(p.getLocation(), Effect.FLAME, 1, 1, 3, 0, 3, 1, 150, 30);
        p.getWorld().strikeLightning(sheep.getLocation());
        fire();

        MakeRace.makeRace(p.getUniqueId(), "demon");

        MGMessage.message(p, "You feel the demon blood rush through your veins!");
    }

    private void fire()
    {
        for(int le = 0; le < 5; le++)
        {
            for(int le2 = 0; le2 < 5; le2++)
            {
                if(le2 + le > 3)
                {
                    if(p.getLocation().getBlock().getRelative(le, 0, le2).getType().equals(Material.AIR))
                    {
                        final int loc1 = le2;
                        final int loc2 = le;
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                            @Override
                            public void run() {
                                p.getLocation().getBlock().getRelative(loc1, 0, loc2).setType(Material.FIRE);
                            }
                        }, 0);
                    }
                }
            }
        }
    }
}
