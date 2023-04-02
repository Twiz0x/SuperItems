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
        if (propertySection == null)
            throw new IllegalArgumentException("Property section is null!");

        String propertyId = propertySection.getName();
        String basePropertyId = propertySection.getString("type");
        if (basePropertyId == null)
            throw new IllegalArgumentException("Base property type of section '" + propertyId + "' not found!");

        ItemProperty<?> baseProperty = getProperty(basePropertyId);
        if (baseProperty == null)
            throw new IllegalArgumentException("Base property '" + basePropertyId + "' not found!");

        this.register(propertyId, baseProperty.deserialize(propertySection));
    }

    public void registerProperties(ConfigurationSection properties) {
        if (properties == null)
            throw new IllegalArgumentException("Properties section is null!");
        properties.getKeys(false).stream()
                .map(properties::getConfigurationSection)
                .forEach(this::registerPropertySection);
    }

}
