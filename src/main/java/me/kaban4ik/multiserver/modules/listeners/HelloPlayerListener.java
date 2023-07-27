package me.kaban4ik.multiserver.modules.listeners;

import lombok.val;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HelloPlayerListener implements Listener {

    private String[] greetings = { // массив, каждый вход разные сообщения
            "Добро пожаловать на сервер UHC 4.0, %player%!",
            "Приветствуем вас, %player%, оцените новые обновления проекта :)",
            "Новые обновления встречают вас, %player%, погрузитесь в мир новых ощущений!"
    };

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        val p = e.getPlayer();
        val greeting = getRandomGreeting().replace("%player%", p.getName());
        p.sendMessage(greeting);
    }

    private String getRandomGreeting() {
        int random = (int) (Math.random() * greetings.length);
        return greetings[random];
    }
}
