package com.azharstudios.e_commerce_learn_backend.repository;

import com.azharstudios.e_commerce_learn_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
