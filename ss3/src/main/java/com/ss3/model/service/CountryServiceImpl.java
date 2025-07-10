
package com.ss3.model.service;

import com.ss3.model.dto.CountryDTO;
import com.ss3.model.entity.Continental;
import com.ss3.model.entity.Country;
import com.ss3.model.repo.CountryRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepo countryRepo;

    @Override
    public CountryDTO save(CountryDTO dto) {
        // Kiểm tra trường hợp cập nhật với tên đã tồn tại
        if (dto.getName() != null && !dto.getName().isBlank()) {
            if (dto.getId() == null) {
                // Trường hợp tạo mới
                if (countryRepo.existsByName(dto.getName())) {
                    throw new IllegalArgumentException("Country name already exists.");
                }
            } else {
                // Trường hợp cập nhật - cần kiểm tra xem tên mới có bị trùng với country khác không
                Country existingCountry = countryRepo.findById(dto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Country not found with id " + dto.getId()));

                if (!existingCountry.getName().equals(dto.getName()) && countryRepo.existsByName(dto.getName())) {
                    throw new IllegalArgumentException("Country name already exists.");
                }
            }
        }

        Country country = toEntity(dto);
        country = countryRepo.save(country);
        return toDTO(country);
    }

    @Override
    public CountryDTO findById(Long id) {
        Country country = countryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id " + id));
        return toDTO(country);
    }

    @Override
    public List<CountryDTO> findAll() {
        return countryRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (!countryRepo.existsById(id)) {
            throw new EntityNotFoundException("Country not found.");
        }
        countryRepo.deleteById(id);
    }

    @Override
    public List<CountryDTO> findByNameContainingIgnoreCase(String keyword){
        List<Country> countries = keyword == null || keyword.isBlank()
                ? countryRepo.findAll()
                : countryRepo.findByNameContainingIgnoreCase(keyword);

        return countries.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private CountryDTO toDTO(Country country) {
        return new CountryDTO(
                country.getId(),
                country.getName(),
                country.getContinental().toString(),
                null
        );
    }

    private Country toEntity(CountryDTO dto) {
        Country country = new Country();
        country.setId(dto.getId());
        country.setName(dto.getName());
        country.setContinental(Continental.valueOf(dto.getContinental()));
        return country;
    }
}
