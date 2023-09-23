package org.example.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.Entities.Street;
import org.example.Repositories.StreetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class StreetController {
    private  final StreetRepository streetRepository;
    //read
    @GetMapping("/street/{streetId}")
    private Street getStreet(@PathVariable Long streetId){return streetRepository.findById(streetId).get();}

    @GetMapping("/street")
    private List<Street> getAllStreets(@PathVariable Street street){return streetRepository.findAll();}

    //save
    @PostMapping("/street")
    private Long saveStreet(Street street){
            streetRepository.save(street);
            return  street.getId();
    }

    //update
    @PutMapping("/street")
    private Street updateStreet(@PathVariable Street street){return  streetRepository.save(street);}

    @DeleteMapping("/street/{streetId}")
    private void deleteStreetById(@PathVariable Long streetId){streetRepository.deleteById(streetId);}

}
