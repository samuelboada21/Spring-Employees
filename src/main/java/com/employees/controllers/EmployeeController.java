
package com.employees.controllers;

import com.employees.models.Employee;
import com.employees.services.EmployeeService;
import com.employees.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author SamuelBoada
 */
@Controller
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private PositionService positionService;
    
    @GetMapping("/employees")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "employees";
    }
    
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // crea un modelo de atributo para vincular datos de formulario
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("listPositions", positionService.getAllPositions());
        return "new_employee";
    }
    
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/";
    }
    
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {
        // obtener empleado del servicio
        Employee employee = employeeService.getEmployeeById(id);

        // establecer empleado como atributo del modelo para pre-poblar el formulario
        model.addAttribute("employee", employee);
        model.addAttribute("listPositions", positionService.getAllPositions());
        return "update_employee";
    }
    
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") int id) {
        // llamar al método de eliminar empleado
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
}
