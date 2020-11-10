package ru.itis.casters;

import java.lang.reflect.Field;

/**
 * @author Anvar Khasanov
 * group 11-905
 */

public class IntegerCaster implements Caster {
    @Override
    public boolean isCastable(Field field) {
        return field.getType().getSimpleName().equals("Integer");
    }

    @Override
    public String cast(Field field) {
        return "\"" + field.getName() + "\" INT";
    }
}
