package com.example.first_study_plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

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
          if (neighborhood_block.getType() != Material.AIR && !isOutsideTheWorld(location)) {
            breaked_count += 1;
            neighborhood_block.setType(Material.AIR);
          }
        }
      }
    }
    return breaked_count;
  }

  public boolean isOutsideTheWorld(Location location) {
    boolean bool = false;
    if (Math.abs(location.getX()) >= 1000 || Math.abs(location.getZ()) >= 1000) {
      bool = true;
    }
    return bool;
  }
  public int[] range_unit(String direction) {
    int range[] = new int[4];
    if (direction == "W") {
      range = new int[]{ -1, 1, 0, 2 };
    } else if (direction == "E") {
      range = new int[]{ -1, 1, -2, 0 };
    } else if (direction == "S") {
      range = new int[]{ 0, 2, -1, 1 };
    } else if (direction == "N") {
      range = new int[]{ -2, 0, -1, 1 };
    }
    return range;
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    event.setDropItems(false);

    Player player = event.getPlayer();

    // 持っているのはツール類か?
    ItemStack i = player.getInventory().getItemInMainHand();
    if ((i.getType() != Material.STONE_PICKAXE) && (i.getType() != Material.STONE_SHOVEL)) {
      SeichiCountManager.addSeichiCount(player, 1);
      return;
    }

    String direction = Direction.getCardinalDirection(player);
    int range[] = range_unit(direction); 
    Block block = event.getBlock();

    int breaked_count = rangeBreak(block, range);
    SeichiCountManager.addSeichiCount(player, breaked_count);
  }
}
