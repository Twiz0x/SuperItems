package fr.twizox.superitem;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import fr.twizox.superitem.initializer.CommandInitializer;
import fr.twizox.superitem.initializer.ListenerInitializer;
import fr.twizox.superitem.items.behaviors.BehaviorManager;
import fr.twizox.superitem.items.properties.PropertyManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperItems extends JavaPlugin {

    @Inject
    private PropertyManager propertyManager;

    @Inject
    private BehaviorManager behaviorManager;

    @Override
    public void onEnable() {

        saveResource("config.yml", true);

        final Injector injector = Guice.createInjector(new SuperItemsGuiceModule());
        injector.injectMembers(this);

        handleServices();

        injector.getInstance(ListenerInitializer.class).init(injector);
        injector.getInstance(CommandInitializer.class).init(injector);

    }

    private void handleServices() {
        getServer().getServicesManager().register(PropertyManager.class, propertyManager, this, ServicePriority.Highest);
        getServer().getServicesManager().register(BehaviorManager.class, behaviorManager, this, ServicePriority.Highest);

        propertyManager.registerProperties(getConfig().getConfigurationSection("properties"));
        behaviorManager.addBehaviors(getConfig().getConfigurationSection("behaviors"));
    }

    @Override
    public void onDisable() {

    }


}