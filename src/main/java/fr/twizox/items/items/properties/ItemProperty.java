package fr.twizox.items.items.properties;

import fr.twizox.items.serialization.Deserializable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Event;

public interface ItemProperty<E extends Event> extends Deserializable<ItemProperty<E>, ConfigurationSection> {

    void handle(E event);

    default boolean handleEvent(Event event, boolean itemHold) {
        if (!itemHold && handleOnlyWhenItemHold()) return false;
        // TODO: rewrite this using a registry
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
