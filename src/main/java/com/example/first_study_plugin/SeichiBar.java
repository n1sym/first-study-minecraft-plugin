package com.example.first_study_plugin;

import org.bukkit.boss.*;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class SeichiBar {
  public static void createBossBar(Player player){
    BossBar seichiBar = Bukkit.getServer().createBossBar("seichi level", BarColor.GREEN, BarStyle.SOLID);
    seichiBar.setProgress(0);
    seichiBar.addPlayer(player);
  }
}
