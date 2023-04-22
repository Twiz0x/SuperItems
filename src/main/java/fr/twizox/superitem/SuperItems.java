package fr.twizox.superitem;

import fr.twizox.superitem.items.behaviors.BehaviorManager;
import fr.twizox.superitem.items.properties.PropertyManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperItems extends JavaPlugin {

    @Override
    public void onEnable() {
        saveResource("config.yml", true);

        registerServices();
    }

    private void registerServices() {
        PropertyManager propertyManager = new PropertyManager();
        BehaviorManager behaviorManager = new BehaviorManager(propertyManager);
        handleService(propertyManager);
        handleService(behaviorManager);
    }

    private <T> void handleService(T service) {
        getServer().getServicesManager().register((Class<T>) service.getClass(), service, this, ServicePriority.Highest);
    }

    @Override
    public void onDisable() {
        getServer().getServicesManager().unregisterAll(this);
    }


}