package fr.twizox.superitem.initializer;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class ListenerInitializer implements Initializer {

    private final PluginManager pluginManager;
    private final JavaPlugin plugin;

    @Inject
    public ListenerInitializer(PluginManager pluginManager, JavaPlugin plugin) {
        this.pluginManager = pluginManager;
        this.plugin = plugin;
    }

    @Override
    public void init(Injector injector) {
        findAllClassesUsingClassLoader(plugin.getClass().getPackage().getName() + ".listeners")
                .filter(Listener.class::isAssignableFrom)
                .map(clazz -> (Class<? extends Listener>) clazz)
                .map(injector::getInstance)
                .forEach(listener -> pluginManager.registerEvents(listener, plugin));
    }

    private Stream<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = getClass()
                .getClassLoader()
                .getResourceAsStream(packageName.replace('.', '/'));
        if (stream == null) return Stream.empty();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines().filter(line -> line.endsWith(".class")).map(line -> getClass(line, packageName));
    }

    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException ignored) {
            return null;
        }
    }
}
