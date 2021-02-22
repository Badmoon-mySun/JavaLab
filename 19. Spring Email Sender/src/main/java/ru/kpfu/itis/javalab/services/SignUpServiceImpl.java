package ru.kpfu.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.javalab.dto.UserForm;
import ru.kpfu.itis.javalab.models.User;
import ru.kpfu.itis.javalab.repositories.UserRepository;
import ru.kpfu.itis.javalab.utils.EmailUtil;
import ru.kpfu.itis.javalab.utils.MailsGenerator;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Value("${spring.server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void signUp(UserForm userForm) {
        User newUser = User.builder()
                .username(userForm.getUsername())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .state(User.State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .build();

        userRepository.save(newUser);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());

        emailUtil.sendMail(newUser.getEmail(), "Регистрация", from, confirmMail);
    }

    @Override
    public boolean confirm(String code) {
        Optional<User> userOptional = userRepository.findByConfirmCode(code);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setState(User.State.CONFIRMED);
            userRepository.update(user);
            return true;
        }

        return false;
    }
}
