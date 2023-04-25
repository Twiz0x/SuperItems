package fr.twizox.superitem.utils;

import java.util.Objects;

public abstract class LowerCaseId {

    private final String id;

    public LowerCaseId(String id) {
        Objects.requireNonNull(id, "Id cannot be null!");
        this.id = id.toLowerCase();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!this.getClass().isAssignableFrom(other.getClass())) return false;
        return id.equals(((LowerCaseId) other).id);
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
