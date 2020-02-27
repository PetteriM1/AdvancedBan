package me.leoko.advancedban.bukkit;

import lombok.RequiredArgsConstructor;
import me.leoko.advancedban.AdvancedBan;
import me.leoko.advancedban.AdvancedBanCommandSender;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class BukkitAdvancedBanCommandSender implements AdvancedBanCommandSender {
    private final CommandSender sender;

    @Override
    public String getName() {
        return sender.getName();
    }

    @Override
    public void sendMessage(String message) {
        sender.sendMessage(message);
    }

    @Override
    public boolean executeCommand(String command) {
        return sender.getServer().dispatchCommand(sender, command);
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
