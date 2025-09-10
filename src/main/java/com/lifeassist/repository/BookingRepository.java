package com.lifeassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifeassist.entity.CareServiceBooking;

public interface BookingRepository extends JpaRepository<CareServiceBooking, Long> {

}
