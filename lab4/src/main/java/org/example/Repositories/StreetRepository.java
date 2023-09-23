package org.example.Repositories;

import org.example.Entities.Flat;
import org.example.Entities.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StreetRepository extends JpaRepository<Street, Long> {
    public List<Street> getAllById(Long id);
    public List<Street> getAllByName (String name);
}
