package com.ionii.euroaviabucuresti.repository;

import com.ionii.euroaviabucuresti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByMailPersonal(String mailPersonal);
    Optional<User> findByUserId(Long id);
    Optional<User> findByMailEuroavia(String mailEuroavia);
    List<User> findAllByOrderByLastName();
}
