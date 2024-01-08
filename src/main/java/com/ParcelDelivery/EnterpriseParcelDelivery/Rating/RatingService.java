package com.ParcelDelivery.EnterpriseParcelDelivery.Rating;

import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.DeliveryRequest;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Rating;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.RatingFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RatingService {
    private final RatingRepository repository;
    private final DeliveryRequestRepository deliveryRequestRepository;

    private final RatingFactory ratingFactory;

    public RatingDTO createRating(RatingDTO ratingDTO){
        DeliveryRequest deliveryRequest =  deliveryRequestRepository.findById(ratingDTO.getDelivery_request_id()).orElse(null);
        if(deliveryRequest==null){
            throw new BadRequestException("Delivery request not found");
        }
        Rating rating = ratingFactory.createEntity(ratingDTO,deliveryRequest);
        repository.save(rating);
        RatingDTO dto = ratingFactory.createDTO(rating);
        return dto;

    }
    public RatingDTO findRatingById(int id){

        Rating rating  = repository.findById(id).orElse(null);
        if(rating==null){
            throw new BadRequestException("Rating not found");
        }
        RatingDTO dto = ratingFactory.createDTO(rating);
        return dto;
    }
}
