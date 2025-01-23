package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.UserRepository;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // before saving the new user, we have to generate the userId dynamically
        // we can use UUID for this purpose
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        // we can also encode password here before saving it to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User userReturned = userRepository.findById(user.getUserId())
                            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // update userReturned with user
        userReturned.setName(user.getName());
        userReturned.setEmail(user.getEmail());
        userReturned.setPassword(user.getPassword());
        userReturned.setPhoneNumber(user.getPhoneNumber());
        userReturned.setAbout(user.getAbout());
        userReturned.setProfilePic(user.getProfilePic());
        userReturned.setEnabled(user.isEnabled());
        userReturned.setEmailVerified(user.isEmailVerified());
        userReturned.setPhoneVerified(user.isPhoneVerified());
        userReturned.setProvider(user.getProvider());
        userReturned.setProviderUserId(user.getProviderUserId());

        // save userReturned in database
        return Optional.ofNullable(userRepository.save(userReturned));
    }

    @Override
    public void deleteUser(String id) {
        User userReturned = userRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(userReturned);
    }

    @Override
    public boolean isUserExist(String id) {
        // one liner code for checking if the user exists using findById method
        // return userRepository.findById(id).orElse(null) != null ? true : false;

        // one liner code for checking if the user exists using existsById method
        // return userRepository.existsById(id);

        User userReturned = userRepository.findById(id).orElse(null);
        return userReturned != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User userReturned = userRepository.findByEmail(email).orElse(null);
        return userReturned != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
}
