package fr.twizox.superitem.initializer;

import com.google.inject.Inject;
import com.google.inject.Injector;
import fr.twizox.superitem.commands.ItemCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandInitializer implements Initializer {

    @Inject
    private JavaPlugin plugin;

    @Override
    public void init(Injector injector) {
        plugin.getCommand("item").setExecutor(injector.getInstance(ItemCommand.class));
    }

}
