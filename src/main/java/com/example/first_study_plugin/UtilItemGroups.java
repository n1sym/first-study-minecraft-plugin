package com.example.first_study_plugin;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class UtilItemGroups {
  public static boolean isWaterMaterial(Block block) {
    boolean is_water = false;
    if (block.getType() == Material.WATER ||
        block.getType() == Material.SEAGRASS ||
        block.getType() == Material.TALL_SEAGRASS ||
        block.getType() == Material.POINTED_DRIPSTONE) {
      is_water = true;
    }
    return is_water;
  }
  public static boolean isLavaMaterial(Block block) {
    boolean is_lava = false;
    if (block.getType() == Material.LAVA) {
      is_lava = true;
    }
    return is_lava;
  }
}
