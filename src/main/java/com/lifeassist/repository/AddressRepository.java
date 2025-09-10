package com.lifeassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lifeassist.entity.Address;
import com.lifeassist.entity.User;

import jakarta.transaction.Transactional;

public interface AddressRepository extends JpaRepository<Address, Long>{
	@Transactional
	@Modifying
	@Query("UPDATE Address a SET a.isPrimary = false WHERE a.user = :user")
	void resetPrimaryForUser(User user);

}
