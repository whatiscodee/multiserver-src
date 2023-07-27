package me.kaban4ik.multiserver.modules.impl.scoreboard;

import com.comphenix.protocol.utility.MinecraftProtocolVersion;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.ProtocolVersion;

public class ScoreboardModule {

    private ScoreboardManager scoreboardManager;
    private Scoreboard scoreboard;
    protected Objective objective;

    public ScoreboardModule() {
        scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        objective = scoreboard.registerNewObjective("server_info", "dummy", "§A§LKABAN4IK BOARD");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        new BukkitRunnable() {

            @Override
            public void run() {
                updateScoreboard();
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("multiserver"), 0, 20);
    }

    private void updateScoreboard() {
        val serverName = "UHC 4.0";

        for (val player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
        objective.setDisplayName(serverName);
    }
}
