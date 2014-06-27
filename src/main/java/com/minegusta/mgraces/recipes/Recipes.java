package com.minegusta.mgraces.recipes;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Recipes
{
    public static void register()
    {
        crystalRecipe();
        elfRecipe();
        dwarfRecipe();
    }

    private static void elfRecipe()
    {
        ItemStack i = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemMeta meta = i.getItemMeta();
        List<String> lore = Lists.newArrayList();
        lore.add(ChatColor.GREEN + "Elf Stew");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.DARK_GREEN + "Odd Smelling Stew");
        i.setItemMeta(meta);

        Recipe elfRecipe = new ShapelessRecipe(i)
        {
            {
                addIngredient(3, Material.YELLOW_FLOWER);
                addIngredient(1, Material.MUSHROOM_SOUP);
                addIngredient(2, Material.POTATO_ITEM);
                addIngredient(2, Material.CARROT_ITEM);
                addIngredient(1, Material.LEAVES);
            }
        };

        Bukkit.getServer().addRecipe(elfRecipe);
    }

    private static void crystalRecipe()
    {
        ItemStack i = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemMeta meta = i.getItemMeta();
        List<String> lore = Lists.newArrayList();
        lore.add(ChatColor.GREEN + "Crystal Eye");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.DARK_GREEN + "Crystalized Eye of Ender");
        i.setItemMeta(meta);

        Recipe elfRecipe = new ShapelessRecipe(i)
        {
            {
                addIngredient(1, Material.EYE_OF_ENDER);
                addIngredient(4, Material.DIAMOND);
                addIngredient(4, Material.EMERALD);
            }
        };

        Bukkit.getServer().addRecipe(elfRecipe);
    }

    private static void dwarfRecipe()
    {
        ItemStack i = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemMeta meta = i.getItemMeta();
        List<String> lore = Lists.newArrayList();
        lore.add(ChatColor.GREEN + "Shiny Gem");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.DARK_GREEN + "The Arkenstone");
        i.setItemMeta(meta);

        Recipe elfRecipe = new ShapelessRecipe(i)
        {
            {
                addIngredient(1, Material.NETHER_STAR);
                addIngredient(4, Material.GOLD_BLOCK);
                addIngredient(4, Material.GOLD_INGOT);
            }
        };

        Bukkit.getServer().addRecipe(elfRecipe);
    }


}
