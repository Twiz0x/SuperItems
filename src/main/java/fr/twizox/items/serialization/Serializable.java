package fr.twizox.items.serialization;

public interface Serializable<T, S> {

    S serialize(T t);

}
