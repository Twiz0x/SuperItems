package fr.twizox.superitem.items.properties.effect.impl;

import fr.twizox.superitem.items.properties.effect.AbstractEffectProperty;
import fr.twizox.superitem.serialization.Deserializer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;

public class EatEffectPropertyDeserializer implements Deserializer<EatEffectProperty> {

    @Override
    public EatEffectProperty deserialize(ConfigurationSection section) {
        int cooldown = section.getInt("cooldown", 0);
        PotionEffect potionEffect = AbstractEffectProperty.getPotionEffect(section);
        return new EatEffectProperty(potionEffect, cooldown);
    }

}
