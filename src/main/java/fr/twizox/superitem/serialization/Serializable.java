package fr.twizox.superitem.serialization;

public interface Serializable<T, S> {

    S serialize(T t);

}
