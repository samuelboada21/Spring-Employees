package com.employees.repositories;

import com.employees.models.UserA;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author SamuelBoada
 */
@Repository
public interface UserRepository extends JpaRepository<UserA, Integer> {
    Optional<UserA> findByEmail(String email);
}

