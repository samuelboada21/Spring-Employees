package com.employees.controllers;

import com.employees.models.Position;
import com.employees.models.UserA;
import com.employees.security.AuthenticationFacade;
import com.employees.services.PositionService;
import com.employees.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author SamuelBoada
 */
@Controller
public class PositionController {

    @Autowired
    private PositionService positionService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationFacade authenticationFacade;

    // Mapeo para mostrar la lista de posiciones
    @GetMapping("/positions")
    public String viewPositionsPage(Model model) {
        String email = authenticationFacade.getAuthentication().getName();
        UserA currentUser = userService.findByEmail(email); // Asegúrate de tener este método en UserService
        List<Position> positions = userService.getPositionsForUser(currentUser);
        model.addAttribute("listPositions", positions);
        model.addAttribute("position", new Position());
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

    /*----- JSON -----*/
    @GetMapping("/api/positions")
    @ResponseBody
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

}
