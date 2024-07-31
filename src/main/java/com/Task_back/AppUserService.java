package com.Task_back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        return appUserRepository.findById(id);
    }

    public AppUser createUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUser updateUser(Long id, AppUser userDetails) {
        return appUserRepository.findById(id)
                .map(user -> {
                    user.setFirstName(userDetails.getFirstName());
                    user.setLastName(userDetails.getLastName());
                    user.setEmail(userDetails.getEmail());
                    user.setPhoneNumber(userDetails.getPhoneNumber());
                    return appUserRepository.save(user);
                }).orElse(null);
    }

    public boolean deleteUser(Long id) {
        return appUserRepository.findById(id)
                .map(user -> {
                    appUserRepository.delete(user);
                    return true;
                }).orElse(false);
    }
}
