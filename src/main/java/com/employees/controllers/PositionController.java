
package com.employees.controllers;

import com.employees.models.Position;
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
public class PositionController {
    
     @Autowired
    private PositionService positionService;

    // Mapeo para mostrar la lista de posiciones
    @GetMapping("/positions")
    public String viewPositionsPage(Model model) {
        model.addAttribute("listPositions", positionService.getAllPositions());
        return "positions";
    }

    // Mapeo para mostrar el formulario de nueva posición
    @GetMapping("/showNewPositionForm")
    public String showNewPositionForm(Model model) {
        Position position = new Position();
        model.addAttribute("position", position);
        return "new_position";
    }

    // Mapeo para guardar la posición
    @PostMapping("/savePosition")
    public String savePosition(@ModelAttribute("position") Position position) {
        positionService.addPosition(position);
        return "redirect:/positions";
    }

    // Mapeo para mostrar el formulario para actualizar una posición
    @GetMapping("/showFormForUpdatePosition/{id}")
    public String showFormForUpdatePosition(@PathVariable(value = "id") int id, Model model) {
        Position position = positionService.getPositionById(id);
        model.addAttribute("position", position);
        return "update_position";
    }

    // Mapeo para eliminar una posición
    @GetMapping("/deletePosition/{id}")
    public String deletePosition(@PathVariable(value = "id") int id) {
        positionService.deletePosition(id);
        return "redirect:/positions";
    }
    
}
