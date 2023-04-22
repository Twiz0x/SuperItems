package fr.twizox.superitem.items.properties;

import fr.twizox.superitem.items.properties.block.ExcavatorPropertyDeserializer;
import fr.twizox.superitem.items.properties.effect.impl.ClickEffectPropertyDeserializer;
import fr.twizox.superitem.items.properties.farm.HarvestProperty;
import fr.twizox.superitem.serialization.Deserializer;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;


public class PropertyManager {

    private final Map<String, ItemProperty<?>> properties = new HashMap<>(Map.of(
            "harvest", new HarvestProperty()
    ));
    private final Map<String, Deserializer<? extends ItemProperty<?>>> deserializerMap = new HashMap<>(Map.of(
            "excavator", new ExcavatorPropertyDeserializer(),
            "click_effect", new ClickEffectPropertyDeserializer(),
            "held_effect", new ClickEffectPropertyDeserializer(),
            "eat_effect", new ClickEffectPropertyDeserializer()
    ));

    public List<String> getPropertiesIds() {
        return List.copyOf(properties.keySet());
    }

    public Optional<ItemProperty<?>> getProperty(String id) {
        return Optional.ofNullable(properties.get(id.toLowerCase()));
    }

    public boolean hasProperty(String id) {
        return properties.containsKey(id.toLowerCase());
    }

    public void register(String id, ItemProperty<?> property) {
        id = id.toLowerCase();
        if (hasProperty(id)) throw new IllegalArgumentException("Property with name \"" + id + "\" already exists!");

        properties.put(id, property);
    }

    public void registerPropertySection(ConfigurationSection propertySection) {
        Objects.requireNonNull(propertySection, "Property section not found!");

        String propertyId = propertySection.getName();
        String type = propertySection.getString("type");
        Objects.requireNonNull(type, "Base property type of section \"" + propertyId + "\" not found!");

        Deserializer<? extends ItemProperty<?>> deserializer = deserializerMap.get(type);
        Objects.requireNonNull(deserializer, "Deserializer for type '" + type + "' not found!");

        ItemProperty<?> property = deserializer.deserialize(propertySection);
        this.register(propertyId, property);
    }

    public void registerProperties(ConfigurationSection properties) {
        Objects.requireNonNull(properties, "Properties section not found!");

        properties.getKeys(false).stream()
                .map(properties::getConfigurationSection)
                .forEach(this::registerPropertySection);
    }

}
