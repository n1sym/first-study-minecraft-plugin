package com.example.first_study_plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;

public class CustomMenu {
  public static void openCustomMenu(Player player) {
    Inventory inventory = Bukkit.createInventory(new SeichiMenu(), InventoryType.CHEST, Component.text("My custom メニュー!"));
    addToolButton(inventory);
    addScafButton(inventory);
    player.openInventory(inventory);
  }

  public static void addScafButton(Inventory inventory){
    ItemStack scaf = new ItemStack(Material.SCAFFOLDING);
    ItemMeta meta = scaf.getItemMeta();
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("足場ブロックを取得"));
      }
    };
    meta.lore(lores);
    scaf.setItemMeta(meta);
    inventory.addItem(scaf);
  }

  public static void addToolButton(Inventory inventory){
    ItemStack scaf = new ItemStack(Material.STONE_PICKAXE);
    ItemMeta meta = scaf.getItemMeta();
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("整地ツールセットを取得"));
      }
    };
    meta.lore(lores);
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    scaf.setItemMeta(meta);
    inventory.addItem(scaf);
  }
}
