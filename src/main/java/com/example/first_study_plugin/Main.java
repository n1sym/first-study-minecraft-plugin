package com.example.first_study_plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  @Override
  public void onEnable() {
    this.getCommand("greet").setExecutor(new CommandGreet());
  }
}
