package com.azharstudios.e_commerce_learn_backend.services;


import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.User;
import com.azharstudios.e_commerce_learn_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long user_id) {
        return userRepository.findById(user_id).orElseThrow(
                () -> new NotFoundException("User Not Found !"));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User userUpdated){
        User userExist = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User Not Found, with id" + userId));
        userExist.setName(userUpdated.getName());
        userExist.setAddress(userUpdated.getAddress());
        userExist.setEmail(userUpdated.getEmail());
        userExist.setPassword(userUpdated.getPassword());
        userExist.setDateOfBirth(userUpdated.getDateOfBirth());

        return userRepository.save(userExist);
    }

    public void deleteUser(Long user_id){
        userRepository.deleteById(user_id);
    }

}
