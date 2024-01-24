
package com.employees.repositories;

import com.employees.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author SamuelBoada
 */
public interface PositionRepository extends JpaRepository<Position, Integer> {
    
}
