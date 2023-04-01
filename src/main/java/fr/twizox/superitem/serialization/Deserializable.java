package fr.twizox.superitem.serialization;

public interface Deserializable<T, S> {

    T deserialize(S s);

}
