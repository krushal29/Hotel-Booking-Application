package com.hmsapp.payload;

public class PropertyDto {

    private String name;
    private Integer noOfGuest;
    private Integer noOfBedrooms;
    private Integer noOfBathrooms;
    private Long cityId;
    private Long countryId;
    private Long id;

    public Long getCityId() {
        return cityId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Integer getNoOfGuest() {
        return noOfGuest;
    }

    public Integer getNoOfBathrooms() {
        return noOfBathrooms;
    }

    public void setNoOfBathrooms(Integer noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    public Integer getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public void setNoOfBedrooms(Integer noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public void setNoOfGuest(Integer noOfGuest) {
        this.noOfGuest = noOfGuest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id)
    {
        this.id=id;
    }

    public Long getId() {
        return id;
    }
}
