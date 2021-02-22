package ru.kpfu.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.javalab.models.User;
import ru.kpfu.itis.javalab.models.User.State;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private State state;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .state(user.getState())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
