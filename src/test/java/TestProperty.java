import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface TestProperty<T> {


    default void isApplicable(Class clazz) {
        Type type = getClass().getGenericInterfaces()[0];
        ParameterizedType p = (ParameterizedType) type;
        Type[] actualTypeArguments = p.getActualTypeArguments();
        System.out.println(actualTypeArguments[0]);
    }

}
