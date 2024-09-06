package com.country.Service;

import com.country.Model.Country;
import com.country.Repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    public Country addCountry(Country country){
        return countryRepository.save(country);
    }
    public List<Country> getCountry(){
        return countryRepository.findAll();
    }
    public Country getCountryById(long id) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            return country.get();
        } else {
            throw new RuntimeException("Country not found with ID: " + id);
        }
    }

    public Country editCountry(long id, Country country) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        existingCountry.setName(country.getName());
        existingCountry.setCode(country.getCode());
        existingCountry.setCapital(country.getCapital());
        existingCountry.setPopulation(country.getPopulation());


        return countryRepository.save(existingCountry);
    }
    public boolean deleteCountry(long id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
