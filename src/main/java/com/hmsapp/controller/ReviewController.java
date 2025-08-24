package com.hmsapp.controller;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.Reviews;
import com.hmsapp.entity.User;
import com.hmsapp.repository.PropertyRepository;
import com.hmsapp.repository.ReviewsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    //http://localhost:8080/api/v1/review
    private PropertyRepository propertyRepository;
    private final ReviewsRepository reviewsRepository;

    public ReviewController(PropertyRepository propertyRepository,
                            ReviewsRepository reviewsRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewsRepository;
    }

    @PostMapping
    public String addReview(@RequestBody Reviews reviews,
                            @RequestParam long propertyId,
                            @AuthenticationPrincipal User user
    ) {

        Property property = propertyRepository.findById(propertyId).get();
     Reviews reviewsStatus =  reviewsRepository.findByPropertyAndUser(property,user);
     if(reviewsStatus!=null){
         reviews.setProperty(property);
         reviews.setUser(user);
         reviewsRepository.save(reviews);
         return "added";
     }
     return "review already given";
    }

    //i want to particular person review details  who logs in either there is user or any other
    @GetMapping("/user/reviews")
    public List<Reviews> viewMyReviews(
            @AuthenticationPrincipal User user
    ) {
       return  reviewsRepository.findByUser(user);
    }

}
