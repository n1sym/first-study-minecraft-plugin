package com.example.first_study_plugin;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class UtilItemGroups {
  public static boolean isWaterMaterial(Block block) {
    boolean is_water = false;
    if (block.getType() == Material.WATER ||
        block.getType() == Material.SEAGRASS ||
        block.getType() == Material.TALL_SEAGRASS) {
      is_water = true;
    }
    return is_water;
  }
}
