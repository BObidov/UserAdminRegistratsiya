package com.company.userandadminauthorities.repository;


import com.company.userandadminauthorities.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserIdAndDeletedAtIsNull(Integer id);


    Optional<User> findByUsernameAndDeletedAtIsNull(String username);

}
