
package com.employees.repositories;

import com.employees.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SamuelBoada
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
    
}
