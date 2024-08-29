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
    public Country editCountry(Country country){
        return  countryRepository.save(country);
    }
    public boolean deleteCountry(int id){
        Optional<Country> country = countryRepository.findById(id);
        if(country.isPresent()){
            countryRepository.delete(country.get());
            return true;
        }
        return false;
    }
}
