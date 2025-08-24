package com.hmsapp.service;

import com.hmsapp.entity.City;
import com.hmsapp.payload.CityDto;
import com.hmsapp.repository.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    //mapToDto
    public CityDto mapToDto(City city){
        CityDto dto=new CityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        return dto;
    }

    //mapToEntity

    public City mapToEntity(CityDto dto){
        City city=new City();
        city.setId(dto.getId());
        city.setName(dto.getName());
        return city;
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public List<CityDto> getAllCity() {
        List<City> getAll=cityRepository.findAll();
       List<CityDto> cityList= getAll.stream().map(city->mapToDto(city)).collect(Collectors.toList());
     return cityList;
     }

    public City findCityById(Long id) {
        Optional<City> opCity = cityRepository.findById(id);
        if (opCity.isPresent()) {
          return opCity.get();
        } else {
            return null;
        }
    }

    public City updateCity(Long id, CityDto dto) {
        Optional<City> opCity=cityRepository.findById(id);
        if(opCity.isPresent()){
          City city=opCity.get();
          city.setName(dto.getName());
          return cityRepository.save(city);
        }else{
            return null;
        }
    }

    public City deleteCityById(Long id) {
        Optional<City> opCity=cityRepository.findById(id);
        if(opCity.isPresent()){
       City city=opCity.get();
       cityRepository.deleteById(id);
       return city;
        }else {
            return null;
        }
    }
}
