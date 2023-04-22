package fr.twizox.superitem.items.properties.effect.impl;

import fr.twizox.superitem.items.properties.effect.AbstractEffectProperty;
import fr.twizox.superitem.serialization.Deserializer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;

public class ClickEffectPropertyDeserializer implements Deserializer<ClickEffectProperty> {

    @Override
    public ClickEffectProperty deserialize(ConfigurationSection section) {
        boolean consumeItem = section.getBoolean("consume-item", false);
        int cooldown = section.getInt("cooldown", 0);
        PotionEffect potionEffect = AbstractEffectProperty.getPotionEffect(section);
        return new ClickEffectProperty(potionEffect, consumeItem, cooldown);
    }

}
