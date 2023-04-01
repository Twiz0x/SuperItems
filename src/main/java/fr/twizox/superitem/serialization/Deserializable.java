package fr.twizox.superitem.serialization;

import fr.twizox.superitem.serialization.exception.DeserializationException;

public interface Deserializable<T, S> {

    T deserialize(S s) throws DeserializationException;

}
