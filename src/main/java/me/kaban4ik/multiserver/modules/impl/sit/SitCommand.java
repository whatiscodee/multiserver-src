package me.kaban4ik.multiserver.modules.impl.sit;

import lombok.val;
import me.kaban4ik.multiserver.modules.impl.sit.entity.SittingEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class SitCommand implements CommandExecutor {

    private static final ArrayList<Player> sittingPlayersMap = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {return true;}

        val p = (Player) sender;

        if (!p.isOnGround()) {return true;}
        if (sittingPlayersMap.contains(p)) {return true;}

        sittingPlayersMap.add(p);

        val loc = p.getLocation();
        val world = loc.getWorld();

        val sitting = new SittingEntity();

        sitting.spawn(world, loc);
        sitting.attachPlayer(p);

        return true;
    }

    @EventHandler
    public void onDismount(EntityDismountEvent event) {
        if (!(event.getEntity() instanceof Player)) {return;}

        val p = (Player) event.getEntity();

        if (!(event.getDismounted() instanceof ArmorStand)) {return;}

        if (sittingPlayersMap.contains(p)) {

            event.getDismounted().remove();

            sittingPlayersMap.remove(p);

            val loc = p.getLocation();
            loc.add(0,1,0);

            p.teleport(loc);
        }
    }
}
