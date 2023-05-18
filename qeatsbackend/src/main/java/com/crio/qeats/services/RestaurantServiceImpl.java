
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import com.crio.qeats.repositoryservices.RestaurantRepositoryServiceDummyImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  @Autowired
  private RestaurantRepositoryService restaurantRepositoService;

  // TODO: CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
  // Check RestaurantService.java file for the interface contract.
  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {

    List<Restaurant> ans = null;
    LocalTime t1 = LocalTime.parse("08:00:00");
    LocalTime t2 = LocalTime.parse("10:00:00");
    LocalTime t3 = LocalTime.parse("13:00:00");
    LocalTime t4 = LocalTime.parse("14:00:00");
    LocalTime t5 = LocalTime.parse("19:00:00");
    LocalTime t6 = LocalTime.parse("21:00:00");
    
    if (currentTime.compareTo(t1) >= 0 && currentTime.compareTo(t2) <= 0) {
      ans = restaurantRepositoService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(),
          getRestaurantsRequest.getLongitude(), currentTime, peakHoursServingRadiusInKms);
    } else if (currentTime.compareTo(t3) >= 0 && currentTime.compareTo(t4) <= 0) {
      ans = restaurantRepositoService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(),
          getRestaurantsRequest.getLongitude(), currentTime, peakHoursServingRadiusInKms);
    } else if (currentTime.compareTo(t5) >= 0 && currentTime.compareTo(t6) <= 0) {
      ans = restaurantRepositoService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(),
          getRestaurantsRequest.getLongitude(), currentTime, peakHoursServingRadiusInKms);
    } else {  
      ans = restaurantRepositoService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(),
          getRestaurantsRequest.getLongitude(), currentTime, normalHoursServingRadiusInKms);
    }
    
    return new GetRestaurantsResponse(ans); 
  }

}
