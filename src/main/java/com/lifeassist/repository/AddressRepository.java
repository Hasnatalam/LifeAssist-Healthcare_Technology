package com.lifeassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifeassist.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
