package fr.twizox.superitem.items.properties;

import org.bukkit.event.Event;

public interface ItemProperty<E extends Event> {

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
