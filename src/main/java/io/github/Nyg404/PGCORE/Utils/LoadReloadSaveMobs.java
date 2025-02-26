package io.github.Nyg404.PGCORE.Utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LoadReloadSaveMobs {

    public FileConfiguration mobconfig;

    public LoadReloadSaveMobs() {
        // Используем правильный путь к папке плагина
        File pluginDataFolder = Bukkit.getPluginManager().getPlugin("RPGCORE").getDataFolder();
        File mobsFolder = new File(pluginDataFolder, "configuretions/mobs");

        // Проверка на существование папки и создание, если её нет
        if (!mobsFolder.exists()) {
            mobsFolder.mkdirs();  // Используем mkdirs(), чтобы создать все промежуточные папки, если их нет
        }

        // Файл конфигурации
        File mobsFile = new File(mobsFolder, "mobreward.yml");

        // Проверка существования файла и его создание, если нужно
        if (!mobsFile.exists()) {
            saveDefaultConfig(mobsFile);
        }

        // Загружаем конфигурацию
        mobconfig = YamlConfiguration.loadConfiguration(mobsFile);
    }

    // Метод для сохранения конфигурации в случае её отсутствия
    private void saveDefaultConfig(File mobsFile) {
        // Здесь мы берем ресурс из папки ресурсов плагина и сохраняем его в папку плагина
        Bukkit.getPluginManager().getPlugin("RPGCORE").saveResource("configuretions/mobs/mobreward.yml", false);
    }
}
