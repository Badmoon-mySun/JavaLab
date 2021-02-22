package ru.kpfu.itis.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.javalab.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Repository
public class UserRepositoryJdbcTemplateImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from user";

    //language=SQL
    private static final String SQL_SAVE_USER =
            "insert into test_user(username, email, password, state, confirm_code) values (?, ?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_BY_CONFIRM_CODE = "select * from test_user where confirm_code = ?";

    //language=SQL
    private static final String SQL_UPDATE_USER =
            "update test_user set username = ?, email = ?, password = ?, state = ?, confirm_code = ? where id = ?";

    private User mapToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .state(User.State.valueOf(resultSet.getString("state")))
                .confirmCode(resultSet.getString("confirm_code"))
                .build();
    }

    @Autowired
    public UserRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, this::mapToUser);
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SQL_SAVE_USER, entity.getUsername(), entity.getEmail(),
                entity.getPassword(), entity.getState().toString(), entity.getConfirmCode());
    }

    @Override
    public Optional<User> findByConfirmCode(String code) {
        return jdbcTemplate.query(SQL_BY_CONFIRM_CODE, this::mapToUser, code).stream().findAny();
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(SQL_UPDATE_USER, entity.getUsername(), entity.getEmail(),
                entity.getPassword(), entity.getState().toString(), entity.getConfirmCode(), entity.getId());

    }

    @Override
    public List<User> findAll(int page, int size) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User entity) {

    }
}
