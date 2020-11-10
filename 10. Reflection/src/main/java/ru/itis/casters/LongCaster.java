package ru.itis.casters;

import java.lang.reflect.Field;

/**
 * @author Anvar Khasanov
 * group 11-905
 */

public class LongCaster implements Caster {
    @Override
    public boolean isCastable(Field field) {
        return field.getType().getSimpleName().equals("Long");
    }

    @Override
    public String cast(Field field) {
        return "\"" + field.getName() + "\" BIGINT";
    }
}
