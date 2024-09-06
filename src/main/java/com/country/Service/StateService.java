package com.country.Service;

import com.country.DTO.StateDTO;
import com.country.Model.Country;
import com.country.Model.State;
import com.country.Repository.CountryRepository;
import com.country.Repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public StateService(StateRepository stateRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    public StateDTO addState(StateDTO stateDto) {
      Country country = countryRepository.findByName(stateDto.getCountry());
        State state = new State();
        state.setName(stateDto.getName());
        state.setCode(stateDto.getCode());
        state.setPopulation(stateDto.getPopulation());
        state.setCountry(country);
        stateRepository.save(state);
        stateDto.setId(state.getId());
        return stateDto;
    }

    public List<StateDTO> getAllStates() {
        List<State> states = stateRepository.findAll();

        return states.stream().map(state -> {
            StateDTO dto = new StateDTO();
            dto.setId(state.getId());
            dto.setName(state.getName());
            dto.setCode(state.getCode());
            dto.setPopulation(state.getPopulation());
            dto.setCountry(state.getCountry() != null ? state.getCountry().getName() : "Unknown Country");
            return dto;
        }).collect(Collectors.toList());
    }

public StateDTO getStateById(long id) {
    State state = stateRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));
    StateDTO dto1 = new StateDTO();
    dto1.setId(state.getId());
    dto1.setName(state.getName());
    dto1.setCode(state.getCode());
    dto1.setPopulation(state.getPopulation());
    dto1.setCountry(state.getCountry() != null ? state.getCountry().getName() : "Unknown Country");
    return dto1;
}


    public StateDTO editState(long id, StateDTO stateDto) {
        State existingState = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));

        Country country = countryRepository.findByName(stateDto.getCountry());
        if (country == null) {
            throw new RuntimeException("Country not found with name: " + stateDto.getCountry());
        }
        existingState.setName(stateDto.getName());
        existingState.setCode(stateDto.getCode());
        existingState.setPopulation(stateDto.getPopulation());
        existingState.setCountry(country);
        existingState = stateRepository.save(existingState);
        stateDto.setId(existingState.getId());
        return stateDto;
    }

    public boolean deleteState(long id) {
        if (stateRepository.existsById(id)) {
            stateRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
