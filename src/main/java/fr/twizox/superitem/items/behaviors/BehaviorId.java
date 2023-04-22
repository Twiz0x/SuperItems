package fr.twizox.superitem.items.behaviors;

import java.util.Objects;

public record BehaviorId(String id) {

    public BehaviorId {
        Objects.requireNonNull(id, "Behavior id cannot be null!");
        id = id.toLowerCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BehaviorId)
            return id.equals(((BehaviorId) obj).id);
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }

}
