package me.kaban4ik.multiserver.modules.impl.sit.entity;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SittingEntity {

    private ArmorStand stand; // ну опять наверное летать будем :D

    public void spawn(World world, Location location) {

        this.stand = (ArmorStand) world.spawnEntity(
                location.add(0, -1.6, 0),
                EntityType.ARMOR_STAND);

        this.stand.setGravity(false);
        this.stand.setVisible(false);
        this.stand.setInvulnerable(false);
    }

    public void deSpawn() {
        this.stand.remove();
    }

    public ArmorStand getStand() {
        return this.stand;
    }

    public void attachPlayer(Player player) {

        if (this.stand != null) {

            this.stand.addPassenger(player); // ну да, точно летать будем нахуй
        }
    }
}
