package com.ionii.euroaviabucuresti.setup;

import com.ionii.euroaviabucuresti.model.User;
import com.ionii.euroaviabucuresti.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;


    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        if(userRepository.findByMailEuroavia("admin@euroavia-bucuresti.ro").isEmpty() && alreadySetup) {
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setPassword(passwordEncoder.encode("euroavia"));
        user.setMailEuroavia("admin@euroavia-bucuresti.ro");
        user.setMailPersonal("admin@gmail.com");
        user.setRole("ADMIN");
        user.setEnabled(true);
        user.setDepartment("IT");

        userRepository.save(user);

        alreadySetup = true;
        }

    }
}