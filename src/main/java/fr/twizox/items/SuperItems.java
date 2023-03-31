package fr.twizox.items;

import fr.twizox.items.commands.ItemCommand;
import fr.twizox.items.items.behaviors.BehaviorManager;
import fr.twizox.items.items.properties.PropertyManager;
import fr.twizox.items.listeners.PlayerListeners;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperItems extends JavaPlugin {

    @Override
    public void onEnable() {

        saveResource("config.yml", true);

        PropertyManager.INSTANCE.registerProperties(getConfig().getConfigurationSection("properties"));
        BehaviorManager.INSTANCE.addBehaviors(getConfig().getConfigurationSection("behaviors"));

        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
        getCommand("item").setExecutor(new ItemCommand());

    }

    @Override
    public void onDisable() {

    }


}