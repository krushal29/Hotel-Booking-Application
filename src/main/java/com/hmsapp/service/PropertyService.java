package com.hmsapp.service;

import com.hmsapp.entity.City;
import com.hmsapp.entity.Country;
import com.hmsapp.entity.Property;
import com.hmsapp.payload.PropertyDto;
import com.hmsapp.repository.CityRepository;
import com.hmsapp.repository.CountryRepository;
import com.hmsapp.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private ModelMapper modelMapper;
    private PropertyRepository propertyRepository;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public PropertyService(ModelMapper modelMapper, PropertyRepository propertyRepository, CountryRepository countryRepository, CityRepository cityRepository) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    public PropertyDto save(PropertyDto dto) {
             Property property=mapToEntity(dto);

        City city=cityRepository.findById(dto.getCityId())
                .orElseThrow(()->new RuntimeException("City Not Found with Id "+dto.getCityId()));
        Country country=countryRepository.findById(dto.getCountryId())
                .orElseThrow(()->new RuntimeException("Country Id not found with this id "+ dto.getCountryId()));

     property.setCity(city);
     property.setCountry(country);
     Property savedProperty   =   propertyRepository.save(property);
     PropertyDto propertyDto=mapToDto(savedProperty);
     return propertyDto;
    }


    //mapToDto

    public PropertyDto mapToDto(Property property) {
        PropertyDto dto = new PropertyDto();
        dto.setId(property.getId());
        dto.setName(property.getName());
        dto.setNoOfGuest(property.getNoOfGuest());
        dto.setNoOfBedrooms(property.getNoOfBedrooms());
        dto.setNoOfBathrooms(property.getNoOfBathrooms());
        dto.setCityId(property.getCity().getId());
        dto.setCountryId(property.getCountry().getId());
        return dto;
    }

    //mapToEntity

    public Property mapToEntity(PropertyDto dto) {
        Property property = new Property();
        property.setId(dto.getId());
        property.setName(dto.getName());
        property.setNoOfGuest(dto.getNoOfGuest());
        property.setNoOfBedrooms(dto.getNoOfBedrooms());
        property.setNoOfBathrooms(dto.getNoOfBathrooms());
        // city and country are set separately in save() method
        return property;
    }
}
