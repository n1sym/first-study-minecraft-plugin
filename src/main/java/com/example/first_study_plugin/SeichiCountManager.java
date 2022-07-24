package com.example.first_study_plugin;

import org.bukkit.entity.Player;
import org.bukkit.metadata.*;
import org.bukkit.plugin.*;
import net.kyori.adventure.text.Component;

public class SeichiCountManager {
  public static void setSeichiCount(Player player, Integer count) {
    Plugin plugin = Main.getInstance();
    int res[] = getSeichiLevel(count);
    Integer seichi_level = res[0];
    int rounded_prev_count = res[1];
    int rounded_next_count = res[2];

    player.setMetadata("seichi_count", new FixedMetadataValue(plugin, count));
    player.setMetadata("seichi_level", new FixedMetadataValue(plugin, seichi_level));
    player.setMetadata("prev_count", new FixedMetadataValue(plugin, rounded_prev_count));
    player.setMetadata("next_count", new FixedMetadataValue(plugin, rounded_next_count));

    String str_level = seichi_level.toString();
    String display_name = player.getName();
    player.displayName(Component.text("Lv" + str_level + " " + display_name));
    player.playerListName(Component.text("Lv" + str_level + " " + display_name));
  }

  public static void addSeichiCount(Player player, Integer add_count) {
    int old_seichi_count = player.getMetadata("seichi_count").get(0).asInt();
    int seichi_count = old_seichi_count + add_count;
    Plugin plugin = Main.getInstance();
    player.setMetadata("seichi_count", new FixedMetadataValue(plugin, seichi_count));

    int next_count = player.getMetadata("next_count").get(0).asInt();
    if (next_count <= seichi_count) {
      setSeichiCount(player, seichi_count);
      next_count = player.getMetadata("next_count").get(0).asInt();
    }

    int seichi_level = player.getMetadata("seichi_level").get(0).asInt();
    int prev_count = player.getMetadata("prev_count").get(0).asInt();

    double base = (double)(next_count - prev_count);
    double progress = (double)((seichi_count - prev_count) / base);
    SeichiBar.setSeichiBar(seichi_level, seichi_count, next_count, player, progress);
  }

  private static int[] getSeichiLevel(Integer seichi_count) {
    double required_count = 1000.0;
    Integer player_seichi_level = 1;
    double factor = 1.15;
    double total = 1000.0;

    while (total <= seichi_count) {
      required_count *= factor;
      total += required_count;
      player_seichi_level += 1;
    }

    int res[] = new int[2];
    int round_prev_count = (int)(total-required_count);
    int rounded_next_count = (int) total;
    res = new int[] { player_seichi_level, round_prev_count,  rounded_next_count };
    return res;
  }

}
