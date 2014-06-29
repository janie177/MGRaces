package com.minegusta.mgraces.powerlisteners.elf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.Elf;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FruitBoost
{
    private Player p;
    private int[] fruit = {260, 282, 297, 322, 357, 360, 391, 393, 396, 400};
    private ItemStack s;

    public FruitBoost(PlayerItemConsumeEvent e)
    {
        this.p = e.getPlayer();
        this.s = e.getItem();

        if(isFruit() && isElf() && !e.isCancelled())
        {
            apply();
        }
    }

    private boolean isElf()
    {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof Elf;
    }

    private boolean isFruit()
    {
        for(int i : fruit)
        {
            if(i == s.getTypeId())return true;
        }
        return false;
    }

    private void apply()
    {
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15 * 20, 1));
    }

}
