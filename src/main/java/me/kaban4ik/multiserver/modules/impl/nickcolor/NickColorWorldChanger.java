package me.kaban4ik.multiserver.modules.impl.nickcolor;

import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class NickColorWorldChanger implements Listener {
    ChatColor color;

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        val player = event.getPlayer();
        val world = player.getWorld();

        // тут уже код определения цвета по миру
        if (world.getEnvironment() == World.Environment.NETHER) {
            color = ChatColor.RED;
        } else if (world.getEnvironment() == World.Environment.THE_END) {
            color = ChatColor.BLUE;
        } else { // в остальных мирах зеленый
            color = ChatColor.GREEN;
        }

        // устанавливаем челу цвет к нику
        player.setDisplayName(color + player.getName());

        // обновлялка цвета в табе
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            online.setPlayerListName(color + online.getName());
        }
    }
}
