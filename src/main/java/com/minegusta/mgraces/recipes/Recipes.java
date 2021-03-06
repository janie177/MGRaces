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
        auroraRecipe();
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
        ItemStack i = new ItemStack(Material.EYE_OF_ENDER, 1);
        ItemMeta meta = i.getItemMeta();
        List<String> lore = Lists.newArrayList();
        lore.add(ChatColor.LIGHT_PURPLE + "Crystal Eye");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.DARK_PURPLE + "Crystalized Eye of Ender");
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
        ItemStack i = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = i.getItemMeta();
        List<String> lore = Lists.newArrayList();
        lore.add(ChatColor.YELLOW + "Shiny Gem");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.WHITE + "The Arkenstone");
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

    private static void auroraRecipe()
    {
        ItemStack i = new ItemStack(Material.DIAMOND, 1);
        ItemMeta meta = i.getItemMeta();
        List<String> lore = Lists.newArrayList();
        lore.add(ChatColor.AQUA + "Ice Crystal");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.DARK_AQUA + "Shiny Ice Crystal");
        i.setItemMeta(meta);

        Recipe elfRecipe = new ShapelessRecipe(i)
        {
            {
                addIngredient(4, Material.SNOW_BALL);
                addIngredient(1, Material.DIAMOND);
                addIngredient(4, Material.SNOW_BLOCK);
            }
        };
        Bukkit.getServer().addRecipe(elfRecipe);
    }



}
