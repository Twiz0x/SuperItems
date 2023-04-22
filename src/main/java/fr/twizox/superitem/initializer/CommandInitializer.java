package fr.twizox.superitem.initializer;

import fr.twizox.superitem.commands.ItemCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandInitializer implements Initializer {

    @Override
    public void init(JavaPlugin plugin) {
        plugin.getCommand("item").setExecutor(new ItemCommand());
    }

}
