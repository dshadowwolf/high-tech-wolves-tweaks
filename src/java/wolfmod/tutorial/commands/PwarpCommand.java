package wolfmod.tutorial.commands;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.entity.player.EntityPlayer;

import wolfmod.tutorial.tutorial;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class PwarpCommand extends CommandBase {

	public PwarpCommand() {
		
	}
	
	@Override
	@Nonnull
	public String getCommandName() {
		return "pwarp";
	}

	@Override
	@Nonnull
	public String getCommandUsage(ICommandSender sender) {
		return "pwarp [command] <name>\npwarp <name>\ncommand: set/delete";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub

	}

}