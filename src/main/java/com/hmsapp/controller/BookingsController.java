package com.hmsapp.controller;

import com.hmsapp.entity.Booking;
import com.hmsapp.entity.Property;
import com.hmsapp.entity.RoomAvailability;
import com.hmsapp.repository.BookingRepository;
import com.hmsapp.repository.PropertyRepository;
import com.hmsapp.repository.RoomAvailabilityRepository;
import com.hmsapp.service.PDFGenerator;
import com.hmsapp.service.TwilioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingsController {

    private RoomAvailabilityRepository roomAvailabilityRepository;
     private PropertyRepository propertyRepository;
     private BookingRepository bookingRepository;
     private PDFGenerator pdfGenerator;
     private TwilioService twilioService;

    public BookingsController(RoomAvailabilityRepository roomAvailabilityRepository, PropertyRepository propertyRepository, BookingRepository bookingRepository, PDFGenerator pdfGenerator, TwilioService twilioService) {
        this.roomAvailabilityRepository = roomAvailabilityRepository;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
        this.pdfGenerator = pdfGenerator;
        this.twilioService = twilioService;
    }

    @GetMapping("/search/rooms")
    public ResponseEntity<?> searchRoomsAndBook(
         @RequestParam LocalDate fromDate,
           @RequestParam LocalDate toDate,
           @RequestParam String roomType,
            @RequestParam long propertyId,
          @RequestBody  Booking bookings
    ){
      List<RoomAvailability> rooms=roomAvailabilityRepository.findAvailableRooms(
                fromDate,
                toDate,
                roomType,
              propertyId
        );
    Property property=  propertyRepository.findById(propertyId).get();

        for(RoomAvailability roomAvailability:rooms){
          if(roomAvailability.getTotal_rooms()==0){
              return new ResponseEntity<>("no rooms available", HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }
        bookings.setProperty(property);
      Booking savedBookings =  bookingRepository.save(bookings);
        pdfGenerator.generatePdf("C:\\Booking_docs\\bookings"+"_"+savedBookings.getId()+".pdf",savedBookings);
         twilioService.sendSms("+919741297046","Test");
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

}
