package com.example.first_study_plugin;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class UtilMetadata {
  public static void setIntMetadata(Player player, String key, Integer num) {
    Plugin plugin = Main.getInstance();
    player.setMetadata(key, new FixedMetadataValue(plugin, num));
  }
}
