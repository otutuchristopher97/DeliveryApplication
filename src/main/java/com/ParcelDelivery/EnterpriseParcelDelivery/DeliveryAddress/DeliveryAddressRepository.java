package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.RecipientAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<RecipientAddress,Integer> {

    List<RecipientAddress> findRecipientAddressByUserId(int user_id);
}
