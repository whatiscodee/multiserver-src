package me.kaban4ik.multiserver.modules.impl.test;

import lombok.val;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BloodKillEffect implements Listener {

    private final int animationTicks = 40;

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        val victim = event.getEntity();
        val location = victim.getLocation();
        playBloodEffect(location);
    }

    private void playBloodEffect(Location location) {
        new BukkitRunnable() {

            int ticksPassed = 0;
            @Override
            public void run() {
                if (ticksPassed >= animationTicks) {
                    this.cancel();
                } else {
                    spawnBloodParticles(location);
                    ticksPassed++;
                }
            }
        }.runTaskTimer((Plugin) this, 0, 1);
    }

    private void spawnBloodParticles(Location location) {
        location.getWorld().spawnParticle(Particle.BLOCK_CRACK, location, 50, 0.3, 0.3, 0.3, 0, 55);
        location.getWorld().spawnParticle(Particle.DRIP_LAVA, location, 10, 0.1, 0.1, 0.1, 0);
    }
}
