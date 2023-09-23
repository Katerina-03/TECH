package org.example.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.Entities.Flat;
import org.example.Repositories.FlatRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FlatController {

    private final FlatRepository flatRepository;

    //read
    @GetMapping("/flat/{flatId}")
    private Flat getFlat(@PathVariable Long flatId) throws Exception {
        return this.flatRepository.existsById(flatId) ? this.flatRepository.findById(flatId).get() : null;
    }

    @GetMapping("/flat")
    private List <Flat> getAllFlats(){return flatRepository.findAll();}

    //save
    @PostMapping("/flat")
    private Long saveFlat(Flat flat){
        flatRepository.save(flat);
        return flat.getId();
    }

    //update
    @PutMapping("/flat")
    private Flat updateFlat(@PathVariable Flat flat){return flatRepository.save(flat);}

    @DeleteMapping("/flat/{flatId}")
    private void deleteFlatById (@PathVariable Long flatId) {flatRepository.deleteById(flatId);}


}
