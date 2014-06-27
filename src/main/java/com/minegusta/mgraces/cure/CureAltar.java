package com.minegusta.mgraces.cure;

import com.minegusta.mgraces.Main;
import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.files.DefaultConf;
import com.minegusta.mgraces.race.Human;
import com.minegusta.mgraces.util.MGMessage;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CureAltar
{
    private UUID uuid;
    private Material mat;
    private Location loc;
    private World world;
    private Action action;
    private Inventory inv;
    private Player p;
    private Block b;

    DefaultConf conf = new DefaultConf();


    public CureAltar(PlayerInteractEvent e)
    {
        this.uuid = e.getPlayer().getUniqueId();
        this.action = e.getAction();
        this.inv = e.getPlayer().getInventory();
        this.world = e.getPlayer().getWorld();
        this.p = e.getPlayer();
        this.loc = e.getPlayer().getLocation();

        if(!isAction())return;

        this.mat = e.getClickedBlock().getType();
        this.b = e.getClickedBlock();

        if(!isBlock() || !isRace() || !hasItems() || !hasBlocksNear())return;

        cure();
        takeItems();
        MGMessage.message(p, "You are now human again!");
        playEffect();
    }

    private boolean isRace()
    {
        return !(TempData.raceMap.get(uuid).getRace() instanceof Human);
    }

    private boolean isAction()
    {
        return action.equals(Action.RIGHT_CLICK_BLOCK);
    }

    private boolean isBlock()
    {
        return mat.equals(Material.getMaterial(conf.altarBlock()));
    }

    private boolean hasItems()
    {
        for(ItemStack s : conf.cureItems())
        {
            if(!inv.contains(s.getType(), s.getAmount()))
            {
                return false;
            }
        }
        return true;
    }

    private void takeItems()
    {
        for(ItemStack s : conf.cureItems())
        {
            p.getInventory().remove(s);
        }
        p.updateInventory();
    }

    private boolean hasBlocksNear()
    {

        int count = 0;
        int radius = conf.altarSecondBlockRadius();
        final Block block = b;
        for (int x = -(radius); x <= radius; x ++)
        {
            for (int y = -(radius); y <= radius; y ++)
            {
                for (int z = -(radius); z <= radius; z ++)
                {
                    if (block.getRelative(x,y,z).getType() == Material.QUARTZ_BLOCK)
                    {
                        count++;
                    }
                }
            }
        }
        if(count >= conf.altarSecondBlockAmount())return true;
        return false;
    }

    //void methods

    private void playEffect()
    {
        world.spigot().playEffect(loc,Effect.HEART,1,1,2,2,2,1,18,30);
        world.spigot().playEffect(loc,Effect.CLOUD,1,1,5,5,5,1,30,30);
        world.spigot().playEffect(loc,Effect.HAPPY_VILLAGER,1,1,2,2,2,1,10,30);
        world.spigot().playEffect(loc,Effect.ENDER_SIGNAL,1,1,2,2,2,1,2,30);

        for(int i = 0; i < 20 * 8; i++)
        {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable()
            {
                @Override
                public void run()
                {
                    p.getWorld().spigot().playEffect(p.getLocation(), Effect.POTION_SWIRL, 1, 1, 1, 1, 1, 1, 20, 25);
                }
            }, i);
        }
    }

    private void cure()
    {
        CureRace.makeHuman(uuid);
    }
}
