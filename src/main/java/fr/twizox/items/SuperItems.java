package fr.twizox.items;

import fr.twizox.items.items.properties.PropertyManager;
import fr.twizox.items.listeners.PlayerListeners;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperItems extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
        PropertyManager.INSTANCE.registerProperties(getConfig().getConfigurationSection("properties"));

    }

    @Override
    public void onDisable() {

    }


}