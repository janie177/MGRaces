package com.minegusta.mgraces.powerlisteners.enderborn;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.EnderBorn;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MeatBoost
{
    private int[] meat = {319, 349, 363, 365, 367};
    private Player p;
    private ItemStack s;

    public MeatBoost(PlayerItemConsumeEvent e)
    {
        this.p = e.getPlayer();
        this.s = e.getItem();

        if(isMeat() && isEnderBorn() && !e.isCancelled())
        {
            apply();
        }
    }

    private boolean isEnderBorn()
    {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof EnderBorn;
    }

    private boolean isMeat()
    {
        for(int i : meat)
        {
            if(i == s.getTypeId())return true;
        }
        return false;
    }

    private void apply()
    {
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * 20, 3));
    }
}
