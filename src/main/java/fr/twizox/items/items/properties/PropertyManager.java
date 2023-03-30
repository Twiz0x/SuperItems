package fr.twizox.items.items.properties;

import fr.twizox.items.items.properties.block.ExcavatorProperty;
import fr.twizox.items.items.properties.farm.HarvestProperty;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Map;

public enum PropertyManager {

    INSTANCE;

    private final Map<String, ItemProperty<?>> properties = Map.of(
            "default_harvest", new HarvestProperty(),
            "default_excavator", new ExcavatorProperty(1, 0, List.of(Material.STONE))
    );

    public void register(String id, ItemProperty<?> property) {
        if (isExist(id)) throw new IllegalArgumentException("Property '" + id + "' already exist!");
        properties.put(id, property);
    }

    public void registerProperties(ConfigurationSection properties) {
        for (String propertyId : properties.getKeys(false)) {
            ConfigurationSection propertySection = properties.getConfigurationSection(propertyId);

            String type = propertySection.getString("type");
            if (type == null) continue; // default property not specified

            ItemProperty<?> property = PropertyManager.INSTANCE.get(type);
            if (property == null) continue; // default property not found

            this.register(propertyId, property.deserialize(propertySection));
        }
    }

    public ItemProperty<?> get(String id) {
        return properties.get(id);
    }

    public void remove(String id) {
        properties.remove(id);
    }

    public boolean isExist(String id) {
        return properties.containsKey(id);
    }

}
