package ru.itis.casters;

import java.lang.reflect.Field;

/**
 * @author Anvar Khasanov
 * group 11-905
 */

public interface Caster {
    boolean isCastable(Field field);

    String cast(Field field);

    default String getValue(Field field, Object obj) {
        String value;
        try {
            field.setAccessible(true);
            value = "'" + field.get(obj).toString() + "'";
        } catch (NullPointerException ex) {
            value = "NULL";
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
        return value;
    }
}
