package com.example.first_study_plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class SeichiAction implements Listener {

  public void rangeBreak(Block block, int[] range) {
    int i_start = range[0];
    int i_end = range[1];
    int k_start = range[2];
    int k_end = range[3];

    for (int i = i_start; i <= i_end; i++) {
      for (int j = -1; j <= 1; j++) {
        for (int k = k_start; k <= k_end; k++) {
          Location location = block.getLocation();
          Block neighborhood_block = location.add(i, j, k).getBlock();
          if (neighborhood_block.getType() != Material.AIR) {
            neighborhood_block.breakNaturally();
          }
        }
      }
    }
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();
    String direction = Direction.getCardinalDirection(player);
    //Bukkit.getLogger().info(direction);

    Block block = event.getBlock();
    if (direction == "W") {
      int range[] = { -1, 1, 0, 2 };
      rangeBreak(block, range);
    } else if (direction == "E") {
      int range[] = { -1, 1, -2, 0 };
      rangeBreak(block, range);
    } else if (direction == "S") {
      int range[] = { 0, 2, -1, 1 };
      rangeBreak(block, range);
    } else if (direction == "N") {
      int range[] = { -2, 0, -1, 1 };
      rangeBreak(block, range);
    }
  }
}
