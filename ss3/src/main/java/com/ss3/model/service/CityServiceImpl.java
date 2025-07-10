// CityServiceImpl.java
package com.ss3.model.service;

import com.ss3.model.dto.CityDTO;
import com.ss3.model.entity.City;
import com.ss3.model.entity.Country;
import com.ss3.model.entity.Season;
import com.ss3.model.repo.CityRepo;
import com.ss3.model.repo.CountryRepo;
import com.ss3.model.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepo cityRepo;
    private final CountryRepo countryRepo;

    @Override
    public CityDTO save(CityDTO dto) {
        if (!countryRepo.existsById(dto.getCountryId())) {
            throw new EntityNotFoundException("Country not found with id " + dto.getCountryId());
        }

        if (dto.getId() == null &&
            cityRepo.existsByNameAndCountryId(dto.getName(), dto.getCountryId())) {
            throw new IllegalArgumentException("City already exists in the specified country.");
        }

        City city = toEntity(dto);
        city = cityRepo.save(city);
        return toDTO(city);
    }

    @Override
    public CityDTO findById(Long id) {
        City city = cityRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found with id " + id));
        return toDTO(city);
    }

    @Override
    public List<CityDTO> findAll() {
        return cityRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (!cityRepo.existsById(id)) {
            throw new EntityNotFoundException("City not found.");
        }
        cityRepo.deleteById(id);
    }

    @Override
    public List<CityDTO> findByNameContainingIgnoreCase(String keyword) {
        List<City> cities = keyword == null || keyword.isBlank()
                ? cityRepo.findAll()
                : cityRepo.findByNameContainingIgnoreCase(keyword);

        return cities.stream().map(this::toDTO).collect(Collectors.toList());
    }


    private CityDTO toDTO(City city) {
        return new CityDTO(
                city.getId(),
                city.getName(),
                city.getCountry().getId(),
                city.getSeason().toString(),
                city.getArea(),
                city.getPopulation()
        );
    }

    private City toEntity(CityDTO dto) {
        City city = new City();
        city.setId(dto.getId());
        city.setName(dto.getName());
        city.setSeason(Season.valueOf(dto.getSeason()));
        city.setArea(dto.getArea());
        city.setPopulation(dto.getPopulation());

        Country country = countryRepo.findById(dto.getCountryId())
                .orElseThrow(() -> new EntityNotFoundException("Country not found."));
        city.setCountry(country);
        return city;
    }
}
