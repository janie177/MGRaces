package com.minegusta.mgraces.infection.dwarf;

import com.minegusta.mgraces.data.TempData;
import com.minegusta.mgraces.race.Human;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DwarfInfect
{
    private Player p;
    private Entity e;
    private ItemStack i;

    public DwarfInfect(PlayerInteractEntityEvent e)
    {
        this.p = e.getPlayer();
        this.i = e.getPlayer().getItemInHand();
        this.e = e.getRightClicked();

        if(isSkeleton() && isHuman() && isGem())
        {
            makeSpirit();
            removeGem();
        }
    }

    //Checks

    private boolean isSkeleton()
    {
        return e instanceof Skeleton;
    }

    private boolean isHuman()
    {
        return TempData.raceMap.get(p.getUniqueId()).getRace() instanceof Human;
    }

    private boolean isGem()
    {
        return i != null && i.hasItemMeta() && i.getItemMeta().hasLore() && i.getItemMeta().getLore().toString().contains("Shiny Gem");
    }


    //Make skeleton boss

    private void removeGem()
    {
        if(p.getItemInHand().getAmount() == 1)
        {
            p.setItemInHand(new ItemStack(Material.AIR));
            return;
        }
        p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
        p.updateInventory();
    }

    private void makeSpirit()
    {
        Skeleton skeleton = (Skeleton) e;
        skeleton.setSkeletonType(Skeleton.SkeletonType.WITHER);
        skeleton.setCustomNameVisible(true);
        skeleton.setCustomName(ChatColor.RED + "Angry Dwarf Spirit");
        skeleton.setTarget(p);
        skeleton.setHealth(skeleton.getMaxHealth());

        ItemStack[] items = {new ItemStack(Material.DIAMOND_HELMET, 1)
        {
            {
                addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                ItemMeta meta = getItemMeta();
                meta.setDisplayName("DwarfBane");
                setItemMeta(meta);
            }
        }, new ItemStack(Material.DIAMOND_CHESTPLATE, 1)
        {
            {
                ItemMeta meta = getItemMeta();
                meta.setDisplayName("DwarfBane");
                setItemMeta(meta);
                addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                addEnchantment(Enchantment.THORNS, 1);
            }
        }, new ItemStack(Material.DIAMOND_LEGGINGS, 1)
        {
            {
                ItemMeta meta = getItemMeta();
                meta.setDisplayName("DwarfBane");
                setItemMeta(meta);
                addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            }
        }, new ItemStack(Material.DIAMOND_BOOTS, 1)
        {
            {
                ItemMeta meta = getItemMeta();
                meta.setDisplayName("DwarfBane");
                setItemMeta(meta);
                addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                addEnchantment(Enchantment.THORNS, 1);
            }
        }};

        skeleton.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD, 1)
        {
            {
                ItemMeta meta = getItemMeta();
                meta.setDisplayName("DwarfBane");
                setItemMeta(meta);
                addEnchantment(Enchantment.DAMAGE_ALL, 2);
                addEnchantment(Enchantment.KNOCKBACK, 1);
            }
        });

        skeleton.getEquipment().setArmorContents(items);
        skeleton.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 1200, 1));
        skeleton.setCanPickupItems(false);
        skeleton.setRemoveWhenFarAway(true);

        skeleton.getEquipment().setHelmetDropChance(0);
        skeleton.getEquipment().setChestplateDropChance(0);
        skeleton.getEquipment().setLeggingsDropChance(0);
        skeleton.getEquipment().setBootsDropChance(0);
        skeleton.getEquipment().setItemInHandDropChance(0);

        p.sendMessage(ChatColor.RED + "[Elf Fiend] " + ChatColor.YELLOW + "Slay me with an axe to prove you are worthy!");
    }



}

