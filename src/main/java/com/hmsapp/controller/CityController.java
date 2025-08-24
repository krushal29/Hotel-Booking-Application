package com.hmsapp.controller;

import com.hmsapp.entity.City;
import com.hmsapp.payload.CityDto;
import com.hmsapp.service.CityService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
        @PostMapping("/addCity")
        public ResponseEntity<CityDto> addCity(@RequestBody CityDto dto){
            City city= cityService.mapToEntity(dto);
            City savedCity=cityService.addCity(city);
            CityDto dtoSaved= cityService.mapToDto(savedCity);
            return new ResponseEntity<>(dtoSaved, HttpStatus.CREATED);
        }
        @GetMapping
        public ResponseEntity<List<CityDto>> getAllCity(){
        List<CityDto> getAll=cityService.getAllCity();
        return new ResponseEntity<>(getAll,HttpStatus.OK);
        }

        @GetMapping("/findBy/{id}")
        public ResponseEntity<CityDto> findCityById(@PathVariable Long id){
       City findCity= cityService.findCityById(id);
       CityDto findDto= cityService.mapToDto(findCity);
        return new ResponseEntity<>(findDto,HttpStatus.OK);
        }

        @PutMapping("/updateCity")
        public ResponseEntity<CityDto> updateCity(@RequestParam Long id,@RequestBody CityDto dto){
                 City updatedCity=cityService.updateCity(id,dto);
                 CityDto updatedDto= cityService.mapToDto(updatedCity);
                 return new ResponseEntity<>(updatedDto,HttpStatus.OK);
                 }

                 @DeleteMapping("/deleteCity/{id}")
                 public ResponseEntity<String> deleteCityById(@PathVariable Long id ){
                       City deletedCity=cityService.deleteCityById(id);
                       return new ResponseEntity<>("City Deleted Successfully",HttpStatus.OK);
                 }
        }

