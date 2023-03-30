package fr.twizox.items.items.properties;

import fr.twizox.items.serialization.Deserializable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface ItemProperty<T extends Event> extends Deserializable<ItemProperty<T>, ConfigurationSection> {

    void handle(T event);

    default boolean handleWhenItemHoldOnly() {
        return false;
    }

    default boolean isApplicable(Event event) {
        Type type = getClass().getGenericInterfaces()[0];
        ParameterizedType p = (ParameterizedType) type;
        Type[] actualTypeArguments = p.getActualTypeArguments();
        return ((Class) actualTypeArguments[0]).isInstance(event);
    }


}
