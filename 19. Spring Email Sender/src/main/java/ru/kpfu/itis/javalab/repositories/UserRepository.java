package ru.kpfu.itis.javalab.repositories;

import ru.kpfu.itis.javalab.models.User;

import java.util.Optional;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
public interface UserRepository extends CrudRepository<User> {
    Optional<User> findByConfirmCode(String code);
}
