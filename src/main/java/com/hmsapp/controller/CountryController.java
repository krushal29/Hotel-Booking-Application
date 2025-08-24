package com.hmsapp.controller;

import com.hmsapp.entity.Country;
import com.hmsapp.payload.CountryDto;
import com.hmsapp.repository.CountryRepository;
import com.hmsapp.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    private CountryService countryService;
    private final CountryRepository countryRepository;

    public CountryController(CountryService countryService,
                             CountryRepository countryRepository) {
        this.countryService = countryService;
        this.countryRepository = countryRepository;
    }
@PostMapping("/addCountry")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto dto ){
        Country country = countryService.mapToEntity(dto);
      Country saveCountry=  countryService.save(country);
      CountryDto saveDto=countryService.mapToDto(country);
      return new ResponseEntity<>(saveDto,HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<CountryDto>> findAllCountry(){
        List<CountryDto> findAll=countryService.findAllCountry();
        return new ResponseEntity<>(findAll,HttpStatus.OK);
    }
    @GetMapping("/findBy/{id}")
    public ResponseEntity<CountryDto> findCountryById(@PathVariable Long id){
      Country findCountry = countryService.findCountryById(id);
      CountryDto findDto=countryService.mapToDto(findCountry);
        return new ResponseEntity<>(findDto,HttpStatus.OK);
    }

    @PutMapping("/updateCountry")
    public ResponseEntity<CountryDto> updateCountryById(@RequestParam Long id,@RequestBody CountryDto dto){
        Country updatedCountry=countryService.updateCountryById(id,dto);
        CountryDto updatedDto=countryService.mapToDto(updatedCountry);
        return new ResponseEntity<>(updatedDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCountryById(@PathVariable Long id){
             countryService.deleteCountryById(id);
             return new ResponseEntity<>("Delete Country SuccesFully this id "+id ,HttpStatus.OK);
    }
}
