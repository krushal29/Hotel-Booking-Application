package com.hmsapp.service;

import com.hmsapp.entity.Country;
import com.hmsapp.payload.CountryDto;
import com.hmsapp.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country save(Country country) {
        countryRepository.save(country);
        return country;
    }

    public Country findCountryById(Long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            return optionalCountry.get();

        }else{
            return null;
        }

    }
    //mapToDto

    public CountryDto mapToDto(Country country){
        CountryDto dto= new CountryDto();
        dto.setId(country.getId());
        dto.setName(country.getName());
        return dto;
    }
    //mapToEntity

    public Country mapToEntity(CountryDto dto){
        Country country=new Country();
        country.setId(dto.getId());
        country.setName(dto.getName());
        return country;
    }


    public List<CountryDto> findAllCountry() {
       List<Country> findAll= countryRepository.findAll();
      List<CountryDto> dtoList= findAll.stream().map(country->mapToDto(country)).collect(Collectors.toList());
      return dtoList;
    }

    public Country updateCountryById(Long id, CountryDto dto) {
       Optional<Country> existing=countryRepository.findById(id);
       if(existing.isPresent()){
       Country country=existing.get();
       country.setName(dto.getName());
       return countryRepository.save(country);
       }else{
           return null;
       }
    }

    public void  deleteCountryById(Long id) {
        Optional<Country>  find=countryRepository.findById(id);
        if(find.isPresent()){
            countryRepository.deleteById(id);
        }else{
            System.out.println("Country Not Found with this id "+id);
        }
    }
}
