package fr.twizox.items;

import fr.twizox.items.listeners.PlayerListeners;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperItems extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);

    }

    @Override
    public void onDisable() {
    }


}