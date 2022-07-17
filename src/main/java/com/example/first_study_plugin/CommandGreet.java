package com.example.first_study_plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandGreet implements CommandExecutor {

  /**
   * 挨拶コマンド
   *
   * @param sender コマンドの送信者
   * @param command コマンドのクラス
   * @param label コマンド名
   * @param args コマンドの引数
   */
  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    // コマンド実行者に「Hi!」というメッセージを送る
    sender.sendMessage("Hi!");
    // コマンド成功判定
    return true;
  }
}
