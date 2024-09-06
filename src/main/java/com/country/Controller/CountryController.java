package com.country.Controller;

import com.country.Model.Country;
import com.country.Service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@CrossOrigin(origins = "http://localhost:3000")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country ){
        return ResponseEntity.ok(countryService.addCountry(country));
    }

    @GetMapping
    public ResponseEntity<List<Country>> getName(){
        return ResponseEntity.ok(countryService.getCountry());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> editCountry(@PathVariable long id, @RequestBody Country country) {
        return ResponseEntity.ok(countryService.editCountry(id, country));
    }
    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable long id) {
        return countryService.getCountryById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCountry(@PathVariable long id) {
        return ResponseEntity.ok(countryService.deleteCountry(id));
    }
}
