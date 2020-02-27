package me.leoko.advancedban.command;

import me.leoko.advancedban.AdvancedBanCommandSender;
import me.leoko.advancedban.punishment.Punishment;
import me.leoko.advancedban.utils.CommandUtils;

import java.util.Optional;
import java.util.OptionalInt;

public class UnPunishCommand extends AbstractCommand {

    public UnPunishCommand() {
        super("unpunish", "ab.all", "UnPunish");
    }

    @Override
    public boolean onCommand(AdvancedBanCommandSender sender, String[] args) {
        if (args.length == 1 && args[0].matches("[0-9]+")) {
            OptionalInt id = CommandUtils.parseInt(args[0]);
            if (!id.isPresent()) {
                return false;
            }
            Optional<Punishment> punishment = sender.getAdvancedBan().getPunishmentManager().getPunishment(id.getAsInt());

            if (punishment.isPresent()) {
                sender.getAdvancedBan().getPunishmentManager().deletePunishment(punishment.get());
                sender.sendCustomMessage("UnPunish.Done", true, "ID", args[0]);
            } else {
                sender.sendCustomMessage("UnPunish.NotFound", true, "ID", args[0]);
            }
            return true;
        }
        return false;
    }
}
