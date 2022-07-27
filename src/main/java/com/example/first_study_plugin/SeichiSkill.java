package com.example.first_study_plugin;

import org.bukkit.entity.Player;

public class SeichiSkill {
  public static int getSkillLevel(Player player) {
    int skill_level = player.getMetadata("skill_level").get(0).asInt();
    return skill_level;
  }

  public static void setSkillLevel(Player player, Integer level){
    UtilMetadata.setIntMetadata(player, "skill_level", level);
  }
}
