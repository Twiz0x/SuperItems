package fr.twizox.superitem.items.properties;

import com.google.inject.Singleton;
import fr.twizox.superitem.items.properties.block.ExcavatorProperty;
import fr.twizox.superitem.items.properties.effect.impl.ClickEffectProperty;
import fr.twizox.superitem.items.properties.effect.impl.EatEffectProperty;
import fr.twizox.superitem.items.properties.effect.impl.HeldItemEffectProperty;
import fr.twizox.superitem.items.properties.farm.HarvestProperty;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Singleton
public class PropertyManager {

    private final Map<String, ItemProperty<?>> properties = new HashMap<>(Map.of(
            "harvest", new HarvestProperty(),
            "excavator", new ExcavatorProperty(),
            "click_effect", new ClickEffectProperty(),
            "held_effect", new HeldItemEffectProperty(),
            "eat_effect", new EatEffectProperty()
    ));

    public List<String> getPropertiesIds() {
        return List.copyOf(properties.keySet());
    }

    public ItemProperty<?> getProperty(String id) {
        return properties.get(id);
    }

    public boolean hasProperty(String id) {
        return properties.containsKey(id);
    }

    public void register(String id, ItemProperty<?> property) {
        if (hasProperty(id)) throw new IllegalArgumentException("Property '" + id + "' already exists!");

        properties.put(id, property);
    }

    public void registerPropertySection(ConfigurationSection propertySection) {
        Objects.requireNonNull(propertySection, "Property section not found!");

        String propertyId = propertySection.getName();
        String basePropertyId = propertySection.getString("type");
        Objects.requireNonNull(basePropertyId, "Base property type of section '" + propertyId + "' not found!");

        ItemProperty<?> baseProperty = getProperty(basePropertyId);
        Objects.requireNonNull(baseProperty, "Base property '" + basePropertyId + "' not found!");

        this.register(propertyId, baseProperty.deserialize(propertySection));
    }

    public void registerProperties(ConfigurationSection properties) {
        Objects.requireNonNull(properties, "Properties section not found!");

        properties.getKeys(false).stream()
                .map(properties::getConfigurationSection)
                .forEach(this::registerPropertySection);
    }

}
