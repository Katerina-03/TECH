package org.example.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.Entities.House;
import org.example.Repositories.FlatRepository;
import org.example.Repositories.HouseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HouseController {
    private final HouseRepository houseRepository;

    //read
    @GetMapping("/house/{houseId}")
    private House getHouse(@PathVariable Long houseId){return  houseRepository.findById(houseId).get();}

    @GetMapping("/house")
    private List<House> getAllHouses () {return houseRepository.findAll();}

    //save
    @PostMapping("/house")
    private Long saveHouse (House house){
        houseRepository.save(house);
        return house.getId();
    }

    //update
    @PutMapping("/house")
    private House updateHouse(@PathVariable House house){return houseRepository.save(house);}

    @DeleteMapping("/house/{houseId}")
    private void deleteHouseById (@PathVariable Long houseId){houseRepository.deleteById(houseId);}
}
