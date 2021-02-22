package ru.kpfu.itis.javalab.services;

import ru.kpfu.itis.javalab.dto.UserDto;

import java.util.List;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
public interface UserService {
    List<UserDto> getAllUser();
}
