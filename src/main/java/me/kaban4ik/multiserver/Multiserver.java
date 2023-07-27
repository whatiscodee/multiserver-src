package me.kaban4ik.multiserver;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.val;
import me.kaban4ik.multiserver.modules.impl.DeathCoordinateModule;
import me.kaban4ik.multiserver.modules.impl.GetPlayerPositionCommand;
import me.kaban4ik.multiserver.modules.impl.nickcolor.NickColorWorldChanger;
import me.kaban4ik.multiserver.modules.impl.scoreboard.ScoreboardModule;
import me.kaban4ik.multiserver.modules.impl.sit.SitCommand;
import me.kaban4ik.multiserver.modules.listeners.HelloPlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public final class Multiserver extends JavaPlugin {

    @Getter
    public static Multiserver instance;

    final String prefix = "§7§l[§aMultiServer§7]";

    private ScoreboardModule scoreboardModule;

    @Override
    public void onEnable() {
        val instance = this;
        val scoreboardModule = new ScoreboardModule();
        Bukkit.getServer().getPluginManager().registerEvents((Listener) new DeathCoordinateModule(), (Plugin)getInstance());
        Bukkit.getServer().getPluginManager().registerEvents((Listener) new SitCommand(), (Plugin)getInstance());
        Bukkit.getServer().getPluginManager().registerEvents((Listener) new HelloPlayerListener(), (Plugin)getInstance());
        Bukkit.getServer().getPluginManager().registerEvents((Listener) new NickColorWorldChanger(), (Plugin)getInstance());

        getCommand("getpos").setExecutor(new GetPlayerPositionCommand());
        getCommand("sit").setExecutor(new SitCommand());
    }

    @Override
    public void onDisable() {
        scoreboardModule = null;
        instance = null;
    }

    public static Multiserver getInstance() {
        return instance;
    }

}
