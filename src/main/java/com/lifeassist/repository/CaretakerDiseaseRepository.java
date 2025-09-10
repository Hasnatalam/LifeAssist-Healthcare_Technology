package com.lifeassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifeassist.entity.CaretakerDisease;
@Repository
public interface CaretakerDiseaseRepository extends JpaRepository<CaretakerDisease, Long>{

}
