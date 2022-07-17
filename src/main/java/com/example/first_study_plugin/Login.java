package com.example.first_study_plugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class Login implements Listener {
  @EventHandler
  public void onPlayerLogin(PlayerLoginEvent event) {
    Player player = event.getPlayer();
    String player_name = player.getName();
    Bukkit.getLogger().info(player_name + "がログインしました");
    try {
      sqlite.exist(player_name);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
