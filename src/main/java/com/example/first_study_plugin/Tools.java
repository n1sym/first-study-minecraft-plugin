package com.example.first_study_plugin;

import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Tools {
  public static void getInitTools(Player player) {
    ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE);
    ItemStack shovel = new ItemStack(Material.STONE_SHOVEL);
    ItemStack axe = new ItemStack(Material.STONE_AXE);
    player.getInventory().addItem(pickaxe, shovel, axe);
  }
}
