
package com.employees.repositories;

import com.employees.models.Position;
import com.employees.models.UserA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SamuelBoada
 */
@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    List<Position> findByManager(UserA manager);
}
