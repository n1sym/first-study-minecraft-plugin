package com.example.first_study_plugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.entity.Player;
import java.util.UUID;

public class Logout implements Listener{
  @EventHandler
  public void onPlayerLogout(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    String name = player.getName();
    UUID uuid = player.getUniqueId();
    try {
      Integer id = sqlite.getId(uuid, name);
      Integer count = SeichiLevel.getSeichiCount(player);
      sqlite.setSeichiCount(id, count);
      int skill_level = SeichiSkill.getSkillLevel(player);
      sqlite.setSeichiSkillLevel(id, skill_level);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
