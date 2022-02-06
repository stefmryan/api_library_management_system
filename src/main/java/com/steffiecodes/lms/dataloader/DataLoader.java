package com.steffiecodes.lms.dataloader;

import com.steffiecodes.lms.models.AppUser;
import com.steffiecodes.lms.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }

    public void loadUsers() {
        AppUser admin = new AppUser("admin@library.org", passwordEncoder.encode("Password123"), "ADMIN", "Ad", "Min");
        AppUser manager = new AppUser("manager@library.org", passwordEncoder.encode("Password123"), "MANAGER", "Mana", "Ger");
        AppUser employee = new AppUser("employee@library.org", passwordEncoder.encode("Password123"), "EMPLOYEE", "Em", "Ploy");

        List<AppUser> users = Arrays.asList(admin, manager, employee);
        appUserRepository.saveAll(users);
    }

}
