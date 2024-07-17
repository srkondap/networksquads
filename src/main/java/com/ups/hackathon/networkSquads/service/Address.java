package com.ups.hackathon.networkSquads.service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="address")
public class Address
{    
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;    
private String fullAddress;    
private Double lat;    
private Double lng;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFullAddress() {
	return fullAddress;
}
public void setFullAddress(String fullAddress) {
	this.fullAddress = fullAddress;
}
public Double getLat() {
	return lat;
}
@Override
public String toString() {
	return "Address [id=" + id + ", fullAddress=" + fullAddress + ", lat=" + lat + ", lng=" + lng + "]";
}
public void setLat(Double lat) {
	this.lat = lat;
}
public Double getLng() {
	return lng;
}
public void setLng(Double lng) {
	this.lng = lng;
}


}
