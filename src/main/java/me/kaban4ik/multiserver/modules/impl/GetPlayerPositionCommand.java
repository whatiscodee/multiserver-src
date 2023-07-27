package me.kaban4ik.multiserver.modules.impl;

import lombok.val;
import me.kaban4ik.multiserver.Multiserver;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetPlayerPositionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("getpos")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Multiserver.instance.getPrefix() + " §cКоманда выполняется только игроком :c");
                return true;
            }

            if (args.length != 1) {
                sender.sendMessage(Multiserver.instance.getPrefix() + " §fИспользуйте: /getpos <игрок>");
                return true;
            }

            val playerName = args[0];
            val targetPlayer = Bukkit.getPlayer(playerName);

            if (targetPlayer == null || !targetPlayer.isOnline()) {
                sender.sendMessage(Multiserver.instance.getPrefix() + " §cИгрок с ником " + playerName + " §fникогда не играл либо оффлайн");
                return true;
            }

            int x = (int) targetPlayer.getLocation().getX();
            int y = (int) targetPlayer.getLocation().getY();
            int z = (int) targetPlayer.getLocation().getZ();
            sender.sendMessage("Координаты игрока " + " " + targetPlayer.getName() + " X= " + x + " Y= " + y + " Z= " + z);

        }
        return true;
    }
}
