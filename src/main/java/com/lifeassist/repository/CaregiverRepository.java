package com.lifeassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifeassist.entity.CaregiverDetails;
@Repository
public interface CaregiverRepository extends JpaRepository<CaregiverDetails, Long>{


}
