package com.country.Controller;

import com.country.Model.Country;
import com.country.Service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @PostMapping("/addCountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country ){
        return ResponseEntity.ok(countryService.addCountry(country));
    }

    @GetMapping("/getName")
    public ResponseEntity<List<Country>> getName(){
        return ResponseEntity.ok(countryService.getCountry());
    }

    @PutMapping("/editCountry")
    public ResponseEntity<Country> editCountry(@RequestBody Country country ){
        return ResponseEntity.ok(countryService.editCountry(country));
    }

    @DeleteMapping("/deleteCountry")
    public boolean deleteCountry(@RequestBody Country country){
        int id =country.getId();
        return countryService.deleteCountry(id);
    }
}
