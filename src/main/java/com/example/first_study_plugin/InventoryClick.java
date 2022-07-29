package com.example.first_study_plugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {
  @EventHandler
  public void onClick(InventoryClickEvent event) {
    if (event.getClickedInventory() == null){
      return;
    }
    if (!(event.getClickedInventory().getHolder() instanceof SeichiMenu)) {
      return;
    }
    event.setCancelled(true);
    ItemStack item = event.getCurrentItem();
    Player player = (Player) event.getWhoClicked();
    if (item != null) {
      if (item.getType() == Material.SCAFFOLDING) {
        Tools.getScaffolding(player);
      }
      if (item.getType() == Material.STONE_PICKAXE) {
        Tools.getInitTools(player);
      }
    }
  }
}
