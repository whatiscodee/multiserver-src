package me.kaban4ik.multiserver.modules.impl;

import lombok.val;
import me.kaban4ik.multiserver.Multiserver;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathCoordinateModule implements Listener {

    @EventHandler
    public void onDeathPlayer(PlayerDeathEvent event) {
        val player = event.getEntity().getPlayer();
        val location = event.getEntity().getLocation();
        int y = (int) location.getY();
        int x = (int) location.getX();
        int z = (int) location.getZ();
        val worldName = location.getWorld().getName();
        player.sendMessage(Multiserver.instance.getPrefix() + " Вы умерли на координатах: X= " + x + " Y= " + y + " Z= " + z + " Мир:" + worldName);
    }
}
