package com.lifeassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifeassist.entity.CaretakerDetails;
@Repository
public interface CaretakerRepository extends JpaRepository<CaretakerDetails, Long>{

}
