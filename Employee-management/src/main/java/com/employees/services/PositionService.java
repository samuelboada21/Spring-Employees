
package com.employees.services;

import com.employees.models.Position;
import com.employees.repositories.PositionRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SamuelBoada
 */
@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;
    
    public Position addPosition(Position position){
        return positionRepository.save(position);
    }
    
    public List<Position> getAllPositions(){
        return positionRepository.findAll();
    }
    
    public Position getPositionById(Integer id) {
        return positionRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Position not found with id: " + id));
    }
    
    public Position updatePosition(Position position) {
        if (position.getId() == null || !positionRepository.existsById(position.getId())) {
            throw new EntityNotFoundException("Position not found with id: " + position.getId());
        }
        return positionRepository.save(position);
    }
    
    public void deletePosition(Integer id) {
        if (!positionRepository.existsById(id)) {
            throw new EntityNotFoundException("Position not found with id: " + id);
        }
        positionRepository.deleteById(id);
    }
    
}
