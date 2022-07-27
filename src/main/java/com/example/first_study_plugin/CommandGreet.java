package com.example.first_study_plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

    if (args.length == 0) {
      return false;
    }
    
    Player player = null;
	  if (sender instanceof Player) {
	  	// プレイヤーが実行
	  	player = (Player) sender;
      if (args[0].equalsIgnoreCase("encha")) {
        Tools.addEnchantTool(player);
      }
      if (args[0].equalsIgnoreCase("init")) {
        Tools.getInitTools(player);
      }
      if (args[0].equalsIgnoreCase("gacha")){
        Skull.getSkull(player);
      }
      if (args[0].equalsIgnoreCase("skill")){
        if (args.length == 1) {
          Integer skill_level = SeichiSkill.getSkillLevel(player);
          sender.sendMessage("現在設定されているスキルレベルは " + skill_level.toString() + " です。" );
        } else {
          int seichi_level = player.getMetadata("seichi_level").get(0).asInt();
          if (args[1].equalsIgnoreCase("0")) {
            SeichiSkill.setSkillLevel(player, 0);
            Integer skill_level = SeichiSkill.getSkillLevel(player);
            sender.sendMessage("現在設定されているスキルレベルは " + skill_level.toString() + " です。" );
          }
          if (args[1].equalsIgnoreCase("1")) {
            if (seichi_level >= 5) {
              SeichiSkill.setSkillLevel(player, 1);
            } else {
              sender.sendMessage("整地レベルが足りません。スキルレベル1は整地レベル5が開放条件です。");
            }
            Integer skill_level = SeichiSkill.getSkillLevel(player);
            sender.sendMessage("現在設定されているスキルレベルは " + skill_level.toString() + " です。" );
          }
        }
      }
      
	  }
    // コマンド成功判定
    return true;
  }
}
