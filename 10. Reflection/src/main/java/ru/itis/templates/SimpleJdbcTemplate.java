package ru.itis.templates;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Anvar Khasanov
 * group 11-905
 */

public class SimpleJdbcTemplate {
    DataSource dataSource;

    public SimpleJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void update(String sql) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    // method for entities with all args constructor
    public <T> T queryForObject(String sql, Class<T> resultType) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                Field[] fields = resultType.getDeclaredFields();

                Class<?>[] params = new Class[fields.length];
                Object[] args = new Object[fields.length];

                resultSet.next();
                for (int i = 0; i < fields.length; i++) {
                    params[i] = fields[i].getType();
                    args[i] = (resultSet.getObject(fields[i].getName(), fields[i].getType()));
                }

                return resultType.getConstructor(params).newInstance(args);

            } catch (IllegalAccessException | InstantiationException
                    | InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
        } catch (SQLException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }
}
