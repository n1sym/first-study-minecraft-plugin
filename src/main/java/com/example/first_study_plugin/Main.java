package com.example.first_study_plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  private static Main instance = null;

  @Override
  public void onEnable() {
    instance = this;
    getLogger().info("プラグインを有効にしました。");
    this.getCommand("greet").setExecutor(new CommandGreet());
    this.getServer().getPluginManager().registerEvents(new SeichiAction(), this);
    this.getServer().getPluginManager().registerEvents(new Login(), this);
  }

  public static Main getInstance() {
    return instance;
}
}
