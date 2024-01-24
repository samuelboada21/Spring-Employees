
package com.employees.services;

import com.employees.models.Employee;
import com.employees.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SamuelBoada
 */
@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    // Agregar un nuevo empleado
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Obtener todos los empleados
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Obtener un empleado por ID
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
    }

    // Actualizar un empleado existente
    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null || !employeeRepository.existsById(employee.getId())) {
            throw new EntityNotFoundException("Employee not found with id: " + employee.getId());
        }
        return employeeRepository.save(employee);
    }

    // Eliminar un empleado
    public void deleteEmployee(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
    
}
