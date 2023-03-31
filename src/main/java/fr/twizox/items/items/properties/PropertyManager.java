package fr.twizox.items.items.properties;

import fr.twizox.items.items.properties.block.ExcavatorProperty;
import fr.twizox.items.items.properties.effect.impl.ClickEffectProperty;
import fr.twizox.items.items.properties.farm.HarvestProperty;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PropertyManager {

    INSTANCE;

    private final Map<String, ItemProperty<?>> properties = new HashMap<>(Map.of(
            "default_harvest", new HarvestProperty(),
            "default_excavator", new ExcavatorProperty(1, 0, List.of(Material.STONE)),
            "click_effect", new ClickEffectProperty(new PotionEffect(PotionEffectType.SPEED, 200, 1))
    ));

    public void register(String id, ItemProperty<?> property) {
        if (isExist(id)) throw new IllegalArgumentException("Property '" + id + "' already exist!");
        properties.put(id, property);
    }

    public void register(ConfigurationSection propertySection) {
        String propertyId = propertySection.getName();
        String basePropertyId = propertySection.getString("type");
        if (basePropertyId == null) return;

        ItemProperty<?> baseProperty = PropertyManager.INSTANCE.getProperty(basePropertyId);
        if (baseProperty == null) return;

        this.register(propertyId, baseProperty.deserialize(propertySection));
    }

    public void registerProperties(ConfigurationSection properties) {
        properties.getKeys(false).forEach(propertyId -> register(properties.getConfigurationSection(propertyId)));
    }

    public List<String> getPropertiesIds() {
        return List.copyOf(properties.keySet());
    }

    public ItemProperty<?> getProperty(String id) {
        return properties.get(id);
    }

    public boolean isExist(String id) {
        return properties.containsKey(id);
    }

}
