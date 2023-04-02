package fr.twizox.superitem;

import com.google.inject.AbstractModule;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperItemsGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SuperItems.class).toInstance(JavaPlugin.getPlugin(SuperItems.class));
        bind(JavaPlugin.class).to(SuperItems.class);
        bind(PluginManager.class).toInstance(JavaPlugin.getPlugin(SuperItems.class).getServer().getPluginManager());
    }

}
