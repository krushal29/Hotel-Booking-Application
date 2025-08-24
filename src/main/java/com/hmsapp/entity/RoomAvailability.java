package com.hmsapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "room_availability")
public class RoomAvailability {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_type", nullable = false)
    private String room_type;

    @Column(name = "total_rooms", nullable = false)
    private Long total_rooms;

    @Column(name = "nightly_price", nullable = false)
    private Long nightlyPrice;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "date",nullable = false)
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Long getNightlyPrice() {
        return nightlyPrice;
    }

    public void setNightlyPrice(Long nightlyPrice) {
        this.nightlyPrice = nightlyPrice;
    }

    public Long getTotal_rooms() {
        return total_rooms;
    }

    public void setTotal_rooms(Long total_rooms) {
        this.total_rooms = total_rooms;
    }

    public Long getId() {
        return id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public void setId(Long id) {
        this.id = id;
    }
}