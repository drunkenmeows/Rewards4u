package me.drunkenmeows.rewards4u;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Rewards4u extends JavaPlugin {
	
	
	public HashMap<String, String> codes = new HashMap<String, String>();
	
	@Override
	public void onEnable() {
		
		List<String> rewards = this.getConfig().getStringList("Rewards");
		
		for(String r:rewards)
		{
			String code = this.getConfig().getString(r+".SecretCode");
			codes.put(code, r);
		}
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void processreward(String reward) {
		
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(command.getLabel().equals("redeem")) {
			if(args.length > 0) {
				if(codes.containsValue(args[0]))
				{
					String reward = codes.get(args[0]);
					
					if(this.getConfig().contains(reward))
					{
						long start = this.getConfig().getLong(reward+".Start");
						long end = this.getConfig().getLong(reward+".End");
						Date d = new Date();
						
						if(start > d.getTime() && end < d.getTime())
						{
							processreward(reward);
						}
					} else {
						sender.sendMessage("&cError: Reward &e[&f"+reward+"&e]&cundefined.");
					}
						
				
				} else {
					sender.sendMessage("Sorry, Key invalid.");
				}
				
			}
		}
		
		return true;
	}

}
