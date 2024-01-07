package com.ParcelDelivery.EnterpriseParcelDelivery.driver;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
