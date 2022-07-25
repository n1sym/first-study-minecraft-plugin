package com.example.first_study_plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.*;

import net.kyori.adventure.text.Component;

public class SeichiAction implements Listener {

  public int rangeBreak(Block block, int[] range, Player player) {
    int i_start = range[0];
    int i_end = range[1];
    int j_start = range[2];
    int j_end = range[3];
    int k_start = range[4];
    int k_end = range[5];

    // 起点ブロックが自分の足元と同じ高さだった場合、y範囲の起点を0とする
    if (player.getLocation().getY() == block.getLocation().getY()) {
      j_start = 0;
    }

    if (isDenyGravityValue(block, range)) {
      player.sendMessage(Component.text("範囲破壊は露天掘りのみ許可しています"));
      return 1;
    }

    int breaked_count = 0;
    for (int i = i_start; i <= i_end; i++) {
      for (int j = j_start; j <= j_end; j++) {
        for (int k = k_start; k <= k_end; k++) {
          Location location = block.getLocation();
          Block neighborhood_block = location.add(i, j, k).getBlock();
          if (i == 0 && j == 0 && k == 0) {
            continue;
          }
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

  public boolean isDenyGravityValue(Block block, int[] range) {
    boolean bool = false;
    int i_start = range[0];
    int i_end = range[1];
    int j_end = range[3];
    int k_start = range[4];
    int k_end = range[5];
    int air_count = 0;
    int block_count = 0;

    for (int i = i_start; i <= i_end; i++) {
      for (int j = j_end + 1; j <= j_end + 5; j++) {
        for (int k = k_start; k <= k_end; k++) {
          Location location = block.getLocation();
          Block neighborhood_block = location.add(i, j, k).getBlock();
          if (neighborhood_block.getType() == Material.AIR) {
            air_count += 1;
          } else {
            block_count += 1;
          }
        }
      }
    }

    double gravity_rate = Double.valueOf(block_count) / Double.valueOf(air_count + block_count);
    if (gravity_rate > 0.5) {
      bool = true;
    }
    return bool;
  }

  public int[] range_unit(String direction) {
    int range[] = new int[4];
    if (direction == "W") {
      range = new int[] { -1, 1, -1, 1, 0, 2 };
    } else if (direction == "E") {
      range = new int[] { -1, 1, -1, 1, -2, 0 };
    } else if (direction == "S") {
      range = new int[] { 0, 2, -1, 1, -1, 1 };
    } else if (direction == "N") {
      range = new int[] { -2, 0, -1, 1, -1, 1 };
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

    int breaked_count = rangeBreak(block, range, player);
    SeichiCountManager.addSeichiCount(player, breaked_count);

    // 耐久度を減らす
    Tools.addDamageTools(i, breaked_count);
  }
}
