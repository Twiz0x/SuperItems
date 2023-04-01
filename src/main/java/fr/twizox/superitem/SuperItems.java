package fr.twizox.superitem;

import fr.twizox.superitem.listeners.PlayerListeners;
import fr.twizox.superitem.commands.ItemCommand;
import fr.twizox.superitem.items.properties.PropertyManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperItems extends JavaPlugin {

    @Override
    public void onEnable() {

        saveResource("config.yml", true);

        PropertyManager.INSTANCE.registerProperties(getConfig().getConfigurationSection("properties"));
        fr.twizox.superitem.items.behaviors.BehaviorManager.INSTANCE.addBehaviors(getConfig().getConfigurationSection("behaviors"));

        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
        getCommand("item").setExecutor(new ItemCommand());

    }

    @Override
    public void onDisable() {

    }


}