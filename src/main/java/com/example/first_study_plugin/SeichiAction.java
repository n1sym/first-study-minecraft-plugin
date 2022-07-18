package com.example.first_study_plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import org.bukkit.Bukkit;

public class SeichiAction implements Listener {

  public int rangeBreak(Block block, int[] range) {
    int i_start = range[0];
    int i_end = range[1];
    int k_start = range[2];
    int k_end = range[3];

    int breaked_count = 0;
    for (int i = i_start; i <= i_end; i++) {
      for (int j = -1; j <= 1; j++) {
        for (int k = k_start; k <= k_end; k++) {
          Location location = block.getLocation();
          Block neighborhood_block = location.add(i, j, k).getBlock();
          if (neighborhood_block.getType() != Material.AIR) {
            breaked_count += 1;
            neighborhood_block.breakNaturally();
          }
        }
      }
    }
    return breaked_count;
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();
    String direction = Direction.getCardinalDirection(player);

    int range[] = null; 
    Block block = event.getBlock();
    if (direction == "W") {
      int t_range[] = { -1, 1, 0, 2 };
      range = t_range;
    } else if (direction == "E") {
      int t_range[] = { -1, 1, -2, 0 };
      range = t_range;
    } else if (direction == "S") {
      int t_range[] = { 0, 2, -1, 1 };
      range = t_range;
    } else if (direction == "N") {
      int t_range[] = { -2, 0, -1, 1 };
      range = t_range;
    }

    int breaked_count = rangeBreak(block, range);

    Integer meta_count = SeichiLevel.getSeichiCount(player);
    SeichiLevel.setSeichiCount(player, meta_count + breaked_count);
    Bukkit.getLogger().info(" 整地カウント(meta): " + meta_count.toString());
   
  }
}
