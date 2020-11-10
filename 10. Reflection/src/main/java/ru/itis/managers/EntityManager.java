package ru.itis.managers;

import ru.itis.casters.*;
import ru.itis.templates.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Anvar Khasanov
 * group 11-905
 */

public class EntityManager {
    private List<Caster> casters = new ArrayList<>();
    SimpleJdbcTemplate jdbcTemplate;

    public EntityManager(DataSource dataSource) {
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);

        casters.add(new IntegerCaster());
        casters.add(new StringCaster());
        casters.add(new BooleanCaster());
        casters.add(new LongCaster());
    }

    // createTable("account", User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS \"").append(tableName).append("\" (  ");

        Arrays.stream(entityClass.getDeclaredFields()).forEach(field ->
            sqlBuilder.append(casters.stream()
                    .filter(caster -> caster.isCastable(field))
                    .map(caster -> caster.cast(field) + ", ")
                    .findAny().orElse(""))
        );

        deleteTwoLastChar(sqlBuilder);
        sqlBuilder.append(");");

        jdbcTemplate.update(sqlBuilder.toString());
    }

    public void save(String tableName, Object entity) {
        Class<?> classOfEntity = entity.getClass();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO \"").append(tableName).append("\" (  ");

        Arrays.stream(classOfEntity.getDeclaredFields()).forEach(field ->
            sqlBuilder.append(casters.stream()
                    .filter(caster -> caster.isCastable(field))
                    .map(caster -> "\"" + field.getName() + "\", ")
                    .findAny().orElse(""))
        );

        deleteTwoLastChar(sqlBuilder);
        sqlBuilder.append(") VALUES (  ");

        Arrays.stream(classOfEntity.getDeclaredFields()).forEach(field ->
                sqlBuilder.append(casters.stream()
                        .filter(caster -> caster.isCastable(field))
                        .map(caster -> caster.getValue(field, entity) + ", ")
                        .findAny().orElse(""))
        );

        deleteTwoLastChar(sqlBuilder);
        sqlBuilder.append(");");

        jdbcTemplate.update(sqlBuilder.toString());
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {

        String sql = "SELECT * FROM \"" + tableName +
                "\" WHERE \"id\" IN ('" + idValue.toString() + "');";

        return jdbcTemplate.queryForObject(sql, resultType);
    }

    private void deleteTwoLastChar(StringBuilder stringBuilder) {
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "");
    }
}
