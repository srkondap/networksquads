package com.ups.hackathon.networkSquads.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long>{

	
	@Query(nativeQuery=true,value = "SELECT * from address where LAT IS null or LNG IS null")
    List<Address> findAllWithNullLatOrLng();
	

}
