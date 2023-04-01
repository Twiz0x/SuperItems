package fr.twizox.superitem.items.properties;

import fr.twizox.superitem.serialization.Deserializable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Event;

public interface ItemProperty<E extends Event> extends Deserializable<ItemProperty<E>, ConfigurationSection> {

    void handle(E event);

    default boolean handleEvent(Event event, boolean itemHold) {
        if (!itemHold && handleOnlyWhenItemHold()) return false;
        try {
            handle((E) event);
        } catch (ClassCastException e) {
            return false;
        }
        return true;
    }

    default boolean handleOnlyWhenItemHold() {
        return true;
    }

}
