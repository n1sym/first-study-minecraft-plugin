package com.example.first_study_plugin;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Skull {
  public static void getSkull(Player player) {
    ItemStack item = new ItemStack(Material.PLAYER_HEAD);
    SkullMeta meta = (SkullMeta)item.getItemMeta();
    OfflinePlayer op = Bukkit.getServer().getOfflinePlayer("Mr_nish");
    meta.setOwningPlayer(op);
    item.setItemMeta(meta);
    player.getInventory().addItem(item);
  }
}
