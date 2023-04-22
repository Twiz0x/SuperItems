package fr.twizox.superitem.serialization;

import org.bukkit.configuration.ConfigurationSection;

public interface Deserializer<T> {

    T deserialize(ConfigurationSection section);

}
