package io.github.Nyg404.PGCORE.Handler;

import io.github.Nyg404.PGCORE.Utils.LoadReloadSaveMobs;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;
import ru.thisistails.tailslib.Tools.YAMLManager;

import javax.swing.text.html.parser.Entity;

public class PlayerkillMobs implements Listener {
    YAMLManager yamlManager = new YAMLManager();
    LoadReloadSaveMobs mobs = new LoadReloadSaveMobs();

    @EventHandler
    public void PlayerkillsMobs(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.getKiller() instanceof Player) {
            Player player = entity.getKiller();
            EntityType entityType = entity.getType();

            // Формируем путь для конфигурации
            String mobPath = "mobs." + entityType.getName().toUpperCase();
            player.sendMessage("Запрашиваемый путь в конфиге: " + mobPath);

            // Проверяем, существует ли путь
            if (mobs.mobconfig.contains(mobPath)) {
                double rewardHealth = mobs.mobconfig.getDouble(mobPath, 0);
                player.sendMessage("Вознаграждение для " + entityType.getName() + ": " + rewardHealth);

                if (rewardHealth > 0) {
                    double newHealth = Math.min(player.getHealth() + rewardHealth, player.getAttribute(Attribute.MAX_HEALTH).getBaseValue());
                    player.setHealth(newHealth);
                    player.sendTitle("Вы получили: " + rewardHealth, "Убитый моб: " + entityType.getName());
                } else {
                    player.sendMessage("Для этого моба нет вознаграждения.");
                }
            } else {
                player.sendMessage("Ошибка: ключ " + mobPath + " не найден в конфиге!");
                player.sendMessage("Ошибка: этот моб не имеет вознаграждения в конфиге.");
            }
        }
    }


}
