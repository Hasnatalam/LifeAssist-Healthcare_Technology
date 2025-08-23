package com.lifeassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifeassist.entity.GuardianDetails;

public interface GuardianRepository extends JpaRepository<GuardianDetails, Integer>{

}
