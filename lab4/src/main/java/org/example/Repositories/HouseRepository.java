package org.example.Repositories;

import org.example.Entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseRepository extends JpaRepository <House, Long> {
    public List<House> getAllById(Long id);
    public List<House> getAllByName (String name);
}
