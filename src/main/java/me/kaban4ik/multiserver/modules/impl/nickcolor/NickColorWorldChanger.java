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
    String worldName;

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        val player = event.getPlayer();
        val world = player.getWorld();

        // тут уже код определения цвета по миру
        if (world.getEnvironment() == World.Environment.NETHER) {
            color = ChatColor.RED;
            worldName = "[Nether]";
        } else if (world.getEnvironment() == World.Environment.THE_END) {
            color = ChatColor.BLUE;
            worldName = "[End]";
        } else { // в остальных мирах зеленый
            color = ChatColor.GREEN;
            worldName = "[World]";
        }

        // устанавливаем челу цвет к нику
        player.setDisplayName(color + worldName + " " + player.getName());

        // обновлялка цвета в табе
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            online.setPlayerListName(color + worldName + " " + online.getName());
        }
    }
}
