package org.example.Repositories;

import org.example.Entities.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
    List<Flat> getAllById(Long id);
    List<Flat> getAllByNumber (int number);
}
