package com.ups.hackathon.networkSquads.service;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class GeocodingService {
	
	
	@Autowired
	private AddressRepo addressRepo;

    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public double[] getLatLng(String address) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonElement locationElement = jsonObject.getAsJsonArray("results")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("geometry")
                .getAsJsonObject("location");

        double lat = locationElement.getAsJsonObject().get("lat").getAsDouble();
        double lng = locationElement.getAsJsonObject().get("lng").getAsDouble();

        return new double[]{lat, lng};
    }
    
    
    @Scheduled(cron = "0 * * * * *")
    public void runBatchJob() 
    
    {
    	System.out.println("Starting batch process at : "+System.currentTimeMillis());
    	processBatchGeoCoding();     
    	System.out.println("Batch process completed at : "+System.currentTimeMillis());
    	}   
    
    
    public void processBatchGeoCoding() {
    	System.out.println("Getting all the address");
        List<Address> addresses = addressRepo.findAllWithNullLatOrLng();
 
         for(Address address:addresses) {
        	 processAddress(address);
        	 System.out.println("Addresss"+address);
         }
        
        
 
//        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
//        allFutures.join(); 
//        
     
    }
    
    private void processAddress(Address address) {
    	String fullAddress = address.getFullAddress();
        double[] location = getLatLng(fullAddress);

        if(location.length==2) {
            address.setLat(location[0]);
            address.setLng(location[1]);
            System.out.println("Address : "+address);
            addressRepo.save(address);
        }else {
        	System.out.println(" Location :" +location);
        }
    }
 
    private CompletableFuture<Void> processAddressAsync(Address address) {
        return CompletableFuture.runAsync(() -> {
            String fullAddress = address.getFullAddress();
            double[] location = getLatLng(fullAddress);
 
                address.setLat(location[0]);
                address.setLng(location[1]);
                System.out.println("Address : "+address);
                addressRepo.save(address);
            
        });
    }
 
    
}

