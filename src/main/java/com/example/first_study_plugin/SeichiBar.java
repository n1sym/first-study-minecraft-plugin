package com.example.first_study_plugin;

import org.bukkit.boss.*;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.metadata.*;
import org.bukkit.plugin.*;

public class SeichiBar {
  public static void createSeichiBar(Player player){
    BossBar bar = Bukkit.getServer().createBossBar("init", BarColor.GREEN, BarStyle.SOLID);
    bar.addPlayer(player);
    Plugin plugin = Main.getInstance();
    MetadataValue metadata = new FixedMetadataValue(plugin, bar);
    player.setMetadata("bar", metadata);
  }

  public static void setSeichiBar(Integer seichi_count, Player player){
    BossBar boss_bar = (BossBar)player.getMetadata("bar").get(0).value();
    boss_bar.setTitle("seichi count : " + seichi_count.toString());
    double base = 1000.0;
    boss_bar.setProgress(seichi_count / base);
  }
}
