package fr.twizox.items.serialization;

import fr.twizox.items.serialization.exception.DeserializationException;

public interface Deserializable<T, S> {

    T deserialize(S s) throws DeserializationException;

}
