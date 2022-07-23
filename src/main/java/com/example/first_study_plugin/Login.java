package com.example.first_study_plugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.UUID;

public class Login implements Listener {
  @EventHandler
  public void onPlayerLogin(PlayerLoginEvent event) {
    Player player = event.getPlayer();
    String name = player.getName();
    UUID uuid = player.getUniqueId();
    Bukkit.getLogger().info(name + "がログインしました");
    try {
      Integer id = sqlite.getId(uuid, name);
      Integer count = sqlite.getSeichiCount(id);
      SeichiLevel.setSeichiCount(player, count);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    Integer count = SeichiLevel.getSeichiCount(player);
    SeichiBar.createSeichiBar(player);
    SeichiCountManager.setSeichiCount(player, count);
    SeichiCountManager.addSeichiCount(player, 0);
  }
}
