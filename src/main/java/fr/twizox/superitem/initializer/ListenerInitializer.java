package fr.twizox.superitem.initializer;

import fr.twizox.superitem.listeners.PlayerListeners;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerInitializer implements Initializer {

    @Override
    public void init(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new PlayerListeners(), plugin);
    }

}
