package fr.twizox.superitem.items.properties.block;

import fr.twizox.superitem.serialization.Deserializer;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class ExcavatorPropertyDeserializer implements Deserializer<ExcavatorProperty> {

    @Override
    public ExcavatorProperty deserialize(ConfigurationSection section) {
        int radius = section.getInt("radius", 1);
        int depth = section.getInt("depth", 0);
        List<Material> materials = section.getStringList("materials").stream()
                .map(Material::valueOf)
                .toList();
        return new ExcavatorProperty(radius, depth, materials);
    }

}
