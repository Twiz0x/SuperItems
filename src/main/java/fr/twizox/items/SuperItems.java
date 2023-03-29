package fr.twizox.items;

import fr.twizox.items.items.BehaviorManager;
import fr.twizox.items.items.ItemBehavior;
import fr.twizox.items.items.properties.farm.PlantingProperty;
import fr.twizox.items.listeners.PlayerListeners;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperItems extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
        BehaviorManager.INSTANCE.addBehavior(new ItemBehavior("farm", new PlantingProperty(1)));

    }

    @Override
    public void onDisable() {
    }


}