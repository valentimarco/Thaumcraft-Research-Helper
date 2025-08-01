package de.promolitor.tchelper.commands;

import de.promolitor.tchelper.Config;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;

public class CommandSetScale extends CommandBase {
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	};

	@Override
	public String getCommandName() {
		return "tchelper";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/tchelper [scale/top] value";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 2) {
			throw new WrongUsageException("/tchelper [scale/top] value");
		}
		
		if (args[0].equals("scale")) {
			int scale = Integer.parseInt(args[1]);
			if (scale > 64 || scale < 1) {
				throw new WrongUsageException("/tchelper scale 1-64");
			}

			Config.setAspectScale(scale);
			sender.addChatMessage(new ChatComponentText("Setting Aspect Scale to " + scale));
			return;
		}

		if (args[0].equals("top")) {
			int scale = Integer.parseInt(args[1]);
			if (scale > 400 || scale < 1) {
				throw new WrongUsageException("/tchelper scale 1-400");
			}

			Config.setTopDistance(scale);
			sender.addChatMessage(new ChatComponentText("Setting Top distance to " + scale));
			return;
		}
	}

}
