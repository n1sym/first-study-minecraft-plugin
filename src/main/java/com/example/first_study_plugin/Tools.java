package com.example.first_study_plugin;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class Tools {
  public static void getInitTools(Player player) {
    ItemStack pickaxe = initStonePickaxe();
    ItemStack shovel = initStoneShovel();
    ItemStack axe = initStoneAxe();
    ItemStack hoe = initDiamondHoe();
    ItemStack diamond_pickaxe = initDiamondPickaxe();
    ItemStack diamond_shovel = initDiamondShovel();

    boolean has_pickaxe = false;
    boolean has_shovel = false;
    boolean has_axe = false;
    boolean has_hoe = false;
    boolean has_diamond_pickaxe = false;
    boolean has_diamond_shovel = false;

    for (ItemStack item : player.getInventory().getContents()){
      if (item == null){
        continue;
      }
      ItemMeta meta = item.getItemMeta();
      if (item.getType() == Material.STONE_PICKAXE && meta.hasLore()) {
        has_pickaxe = true;
      }
      if (item.getType() == Material.STONE_SHOVEL && meta.hasLore()) {
        has_shovel = true;
      }
      if (item.getType() == Material.STONE_AXE && meta.hasLore()) {
        has_axe = true;
      }
      if (item.getType() == Material.DIAMOND_HOE && meta.hasLore()) {
        has_hoe = true;
      }
      if (item.getType() == Material.DIAMOND_PICKAXE) {
        has_diamond_pickaxe = true;
      }
      if (item.getType() == Material.DIAMOND_SHOVEL) {
        has_diamond_shovel = true;
      }
    }
    if (!has_pickaxe) {
      player.getInventory().addItem(pickaxe);
    }
    if (!has_shovel) {
      player.getInventory().addItem(shovel);
    }
    if (!has_axe) {
      player.getInventory().addItem(axe);
    }
    if (!has_hoe) {
      player.getInventory().addItem(hoe);
    }
    if (!has_diamond_pickaxe) {
      player.getInventory().addItem(diamond_pickaxe);
    }
    if (!has_diamond_shovel) {
      player.getInventory().addItem(diamond_shovel);
    }
  }

  public static ItemStack initStonePickaxe(){
    ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE);
    ItemMeta meta = pickaxe.getItemMeta();
    meta.setUnbreakable(true);
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("範囲破壊"));
      }
    };
    meta.lore(lores);
    pickaxe.setItemMeta(meta);
    pickaxe.addEnchantment(Enchantment.DIG_SPEED, 3);
    return pickaxe;
  }

  public static ItemStack initStoneShovel(){
    ItemStack shovel = new ItemStack(Material.STONE_SHOVEL);
    ItemMeta meta = shovel.getItemMeta();
    meta.setUnbreakable(true);
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("範囲破壊"));
      }
    };
    meta.lore(lores);
    shovel.setItemMeta(meta);
    shovel.addEnchantment(Enchantment.DIG_SPEED, 3);
    return shovel;
  }

  public static ItemStack initStoneAxe(){
    ItemStack axe = new ItemStack(Material.STONE_AXE);
    ItemMeta meta = axe.getItemMeta();
    meta.setUnbreakable(true);
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("木こり"));
      }
    };
    meta.lore(lores);
    axe.setItemMeta(meta);
    axe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
    return axe;
  }

  public static ItemStack initDiamondHoe(){
    ItemStack tool = new ItemStack(Material.DIAMOND_HOE);
    ItemMeta meta = tool.getItemMeta();
    meta.setUnbreakable(true);
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("液体凝固"));
      }
    };
    meta.lore(lores);
    tool.setItemMeta(meta);
    tool.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
    return tool;
  }

  public static ItemStack initDiamondPickaxe(){
    ItemStack tool = new ItemStack(Material.DIAMOND_PICKAXE);
    ItemMeta meta = tool.getItemMeta();
    meta.setUnbreakable(true);
    tool.setItemMeta(meta);
    tool.addEnchantment(Enchantment.DIG_SPEED, 5);
    return tool;
  }

  public static ItemStack initDiamondShovel(){
    ItemStack tool = new ItemStack(Material.DIAMOND_SHOVEL);
    ItemMeta meta = tool.getItemMeta();
    meta.setUnbreakable(true);
    tool.setItemMeta(meta);
    tool.addEnchantment(Enchantment.DIG_SPEED, 5);
    return tool;
  }

  public static void getScaffolding(Player player) {
    ItemStack scaf = new ItemStack(Material.SCAFFOLDING, 64);
    player.getInventory().addItem(scaf);
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
    pickaxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
    player.getInventory().addItem(pickaxe);
  }

  public static void addDamageTools(ItemStack tool, int count){
    ItemMeta meta = tool.getItemMeta();
    if (!meta.isUnbreakable()) {
      int now =((Damageable) meta).getDamage();
      ((Damageable) meta).setDamage(count + now);
    }
    tool.setItemMeta(meta);
  }


}
