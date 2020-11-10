package ru.itis;

import org.postgresql.ds.PGSimpleDataSource;
import ru.itis.managers.EntityManager;
import ru.itis.models.User;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Anvar Khasanov
 * group 11-905
 */

public class EntityManagerTestDrive {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/db.properties"));
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));

        EntityManager entityManager = new EntityManager(dataSource);

        entityManager.createTable("user", User.class);

        User user = new User(1L, "Ivan", "Ivanov", true);
        entityManager.save("user", user);

        System.out.println(entityManager.findById("user", User.class, Long.class, 1L));
    }
}
