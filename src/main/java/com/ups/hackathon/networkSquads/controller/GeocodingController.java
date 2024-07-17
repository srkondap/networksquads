package com.ups.hackathon.networkSquads.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ups.hackathon.networkSquads.service.GeocodingService;

@RestController
public class GeocodingController {

    @Autowired
    private GeocodingService geocodingService;

    @GetMapping("/geocode")
    public double[] geocode(@RequestParam String address) {
        return geocodingService.getLatLng(address);
    }
    
   
    
}

