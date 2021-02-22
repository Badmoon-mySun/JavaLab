package ru.kpfu.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.javalab.dto.UserDto;
import ru.kpfu.itis.javalab.repositories.UserRepository;

import java.util.List;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUser() {
        return UserDto.from(userRepository.findAll());
    }
}
