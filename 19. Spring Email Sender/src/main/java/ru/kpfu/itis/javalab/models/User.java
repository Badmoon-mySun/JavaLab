package ru.kpfu.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private State state;
    private String confirmCode;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }
}
