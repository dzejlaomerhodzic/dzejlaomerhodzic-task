package com.Task_back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void run(String... args) throws Exception {
        List<AppUser> users = new ArrayList<>();

        String[] emails = {
                "marina@wiline.com",
                "kip@wiline.com",
                "lorie@wiline.com",
                "jasmin@wiline.com",
                "emma@wiline.com",
                "elvia@wiline.com",
                "liliana@wiline.com",
                "florencio@wiline.com",
                "delores@wiline.com"
        };

        String[] phoneNumbers = {
                "202-555-0105",
                "202-555-0168",
                "202-555-0162",
                "202-555-0168",
                "202-555-0187",
                "202-555-0164",
                "202-555-0161",
                "202-555-0127",
                "202-555-0143"
        };

        for (int i = 0; i < emails.length; i++) {
            String email = emails[i];
            String phoneNumber = phoneNumbers[i];
            String[] nameParts = email.split("@")[0].split("\\.");
            String firstName = nameParts.length > 1 ? nameParts[0] : "Unknown";
            String lastName = nameParts.length > 1 ? nameParts[1] : "Unknown";

            users.add(new AppUser(null, firstName, lastName, email, phoneNumber));
        }

        appUserRepository.saveAll(users);
    }
}
