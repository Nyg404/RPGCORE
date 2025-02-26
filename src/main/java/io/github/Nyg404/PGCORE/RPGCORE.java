package io.github.Nyg404.PGCORE;

import io.github.Nyg404.PGCORE.Handler.PlayerkillMobs;
import org.bukkit.plugin.java.JavaPlugin;

public final class RPGCORE extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerkillMobs(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
