package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;

// BEGIN
public class Validator {

    public static <T> List<String> validate(T c) {
        List<String> result = new ArrayList<>();
        Predicate<Field> p = f -> getObjectField(f, c) == null && isAnnotation(f, NotNull.class);
        Map<Class<? extends Annotation>, Predicate<Field>> predicates = Map.of(NotNull.class, p);
        Map<Field, List<Class<? extends Annotation>>> validation = checkAnnotations(c, predicates);
        for (Map.Entry<Field, List<Class<? extends Annotation>>> el : validation.entrySet()) {
            result.add(el.getKey().getName());
        }
        return result;
    }

    public static <T> Map<String, List<String>> advancedValidate(T c) {
        Map<String, List<String>> result = new HashMap<>();
        Predicate<Field> p1 = f -> getObjectField(f, c) == null && isAnnotation(f, NotNull.class);
        Predicate<Field> p2 = f -> getObjectField(f, c) == null
                ? isAnnotation(f, MinLength.class) : isAnnotation(f, MinLength.class)
                && f.getAnnotation(MinLength.class).minLength() > String.valueOf(getObjectField(f, c)).length();
        Map<Class<? extends Annotation>, Predicate<Field>> predicates = Map.of(NotNull.class, p1, MinLength.class, p2);
        Map<Field, List<Class<? extends Annotation>>> validation = checkAnnotations(c, predicates);
        for (Map.Entry<Field, List<Class<? extends Annotation>>> el : validation.entrySet()) {
            List<String> notValidate = new ArrayList<>();
            if (el.getValue().contains(NotNull.class)) {
                notValidate.add("can not be null");
            }
            if (el.getValue().contains(MinLength.class)) {
                notValidate.add("length less than " + el.getKey().getAnnotation(MinLength.class).minLength());
            }
            result.put(el.getKey().getName(), notValidate);
        }
        return result;
    }

    private static <T> Object getObjectField(Field field, T c) {
        Object o = null;
        try {
            o = field.get(c);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    private static boolean isAnnotation(Field f, Class<? extends Annotation> a) {
        return f.getAnnotation(a) != null;
    }

    private static <T> Map<Field, List<Class<? extends Annotation>>> checkAnnotations(T c
            , Map<Class<? extends Annotation>, Predicate<Field>> predicateMap) {
        Map<Field, List<Class<? extends Annotation>>> result = new LinkedHashMap<>();
        for (Field f : c.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            List<Class<? extends Annotation>> annotations = new ArrayList<>();
            for (Map.Entry<Class<? extends Annotation>, Predicate<Field>> predicateEntry : predicateMap.entrySet()) {
                if (predicateEntry.getValue().test(f)) {
                    annotations.add(predicateEntry.getKey());
                }
            }
            if (annotations.size() > 0) {
                result.put(f, annotations);
            }
        }
        return result;
    }
}
// END
