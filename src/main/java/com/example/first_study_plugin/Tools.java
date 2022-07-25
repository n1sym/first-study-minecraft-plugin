package com.example.first_study_plugin;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class Tools {
  public static void getInitTools(Player player) {
    ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE);
    ItemStack shovel = new ItemStack(Material.STONE_SHOVEL);
    ItemStack axe = new ItemStack(Material.STONE_AXE);
    player.getInventory().addItem(pickaxe, shovel, axe);
  }

  public static void addEnchantTool(Player player) {
    ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE);
    ItemMeta meta = pickaxe.getItemMeta();
    meta.setUnbreakable(true);
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("hello"));
      }
    };
    meta.lore(lores);
    pickaxe.setItemMeta(meta);
    pickaxe.addEnchantment(Enchantment.DIG_SPEED, 1);
    player.getInventory().addItem(pickaxe);
  }
}
