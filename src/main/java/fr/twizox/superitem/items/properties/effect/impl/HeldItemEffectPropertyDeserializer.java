package fr.twizox.superitem.items.properties.effect.impl;

import fr.twizox.superitem.items.properties.effect.AbstractEffectProperty;
import fr.twizox.superitem.serialization.Deserializer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;

public class HeldItemEffectPropertyDeserializer implements Deserializer<HeldItemEffectProperty> {

    @Override
    public HeldItemEffectProperty deserialize(ConfigurationSection section) {
        PotionEffect potionEffect = AbstractEffectProperty.getPotionEffect(section);
        return new HeldItemEffectProperty(potionEffect);
    }

}
