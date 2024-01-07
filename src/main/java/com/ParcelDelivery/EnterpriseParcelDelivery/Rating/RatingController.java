package com.ParcelDelivery.EnterpriseParcelDelivery.Rating;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RatingController {
    private final RatingService service;
    @PostMapping("/rating/create")
    public RatingDTO createRating(@RequestBody RatingDTO ratingDTO){

        return service.createRating(ratingDTO);
    }
    @GetMapping("rating/view/{rating_id}")
    public RatingDTO viewRatingById(@PathVariable int rating_id){
        return service.findRatingById(rating_id);
    }
}
