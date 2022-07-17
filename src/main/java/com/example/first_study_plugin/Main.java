package com.example.first_study_plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  @Override
  public void onEnable() {
    getLogger().info("プラグインを有効にしました。");
    this.getCommand("greet").setExecutor(new CommandGreet());
    this.getServer().getPluginManager().registerEvents(new SeichiAction(), this);
  }
}
