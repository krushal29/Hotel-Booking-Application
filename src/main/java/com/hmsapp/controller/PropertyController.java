package com.hmsapp.controller;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.PropertyImage;
import com.hmsapp.payload.PropertyDto;
import com.hmsapp.repository.ImageRepository;
import com.hmsapp.repository.PropertyImageRepository;
import com.hmsapp.repository.PropertyRepository;
import com.hmsapp.service.BucketService;
import com.hmsapp.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private PropertyService  propertyService;
     private BucketService bucketService;
     private PropertyImageRepository propertyImageRepository;


    private PropertyRepository propertyRepository;

    public PropertyController(PropertyService propertyService, BucketService bucketService, PropertyImageRepository propertyImageRepository, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.bucketService = bucketService;
        this.propertyImageRepository = propertyImageRepository;

        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto dto) {
        PropertyDto saved = propertyService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteProperty")
    public String deleteProperty(){

        return "deleted";
    }
    //http://localhost:8080/api/v1/property/{searchParam}
    @GetMapping("/{searchParam}")
    public List<Property> searchProperty(
            @PathVariable String searchParam
    ){
         return propertyRepository.searchProperty(searchParam);

    }

    @PostMapping("/upload/file/{bucketName}/property/{propertyId}")
    public String uplaodPropertyPhotos(
            @RequestParam MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable long propertyId
            ){
        String imageUrl=bucketService.uploadFile(file,bucketName);
        PropertyImage propertyImage=new PropertyImage();
        propertyImage.setUrl(imageUrl);

        //set fk

        Optional<Property> property = propertyRepository.findById(propertyId);
        if (property.isPresent()) {
            propertyImage.setProperty(property.get());
        } else {
            throw new RuntimeException("Property not found with ID: " + propertyId);
        }
        propertyImageRepository.save(propertyImage);
        return "image is Uploaded ";
    }

    @GetMapping("/get/property/images")
    public List<PropertyImage> getPropertyImages(@RequestParam long id){

      Property property=  propertyRepository.findById(id).get();
      return   propertyImageRepository.findByProperty(property);
    }
}
