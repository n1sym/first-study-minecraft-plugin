package com.example.first_study_plugin;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.metadata.*;
import org.bukkit.plugin.*;

public class SeichiLevel {
  public static void setSeichiCount(Player player, Integer count) {
    Plugin plugin = Main.getInstance();
    MetadataValue metadata = new FixedMetadataValue(plugin, count);
    player.setMetadata("seichi_count", metadata);
  }

  public static Integer getSeichiCount(Player player) {
    Plugin plugin = Main.getInstance();
    List<MetadataValue> values = player.getMetadata("seichi_count");
    MetadataValue value = null;
    for (MetadataValue v : values) {
    	// 名前を比較して同じプラグインか確認
    	if (v.getOwningPlugin().getName().equals(plugin.getName())) {
    		// 同じなら値をセットしてループ抜ける
    		value = v;
    	}
    }
    if (value == null) {
    	return 0;
    }
    return value.asInt();
  }

}
