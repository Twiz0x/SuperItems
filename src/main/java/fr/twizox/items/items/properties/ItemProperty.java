package fr.twizox.items.items.properties;

import org.bukkit.event.Event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface ItemProperty<T extends Event> {

    void handle(T event);

    default boolean handleItemInInventory() {
        return false;
    }

    default boolean isApplicable(Event event) {
        Type type = getClass().getGenericInterfaces()[0];
        ParameterizedType p = (ParameterizedType) type;
        Type[] actualTypeArguments = p.getActualTypeArguments();
        return ((Class) actualTypeArguments[0]).isInstance(event);
    }

}
