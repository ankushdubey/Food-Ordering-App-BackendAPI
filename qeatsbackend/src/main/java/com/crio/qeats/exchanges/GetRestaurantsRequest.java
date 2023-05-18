/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.exchanges;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


// TODO: CRIO_TASK_MODULE_RESTAURANTSAPI
//  Implement GetRestaurantsRequest.
//  Complete the class such that it is able to deserialize the incoming query params from
//  REST API clients.
//  For instance, if a REST client calls API
//  /qeats/v1/restaurants?latitude=28.4900591&longitude=77.536386&searchFor=tamil,
//  this class should be able to deserialize lat/long and optional searchFor from that.

//@Controller
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRestaurantsRequest {
  @NotNull
  @DecimalMin(value = "-90.0")
  @DecimalMax(value = "90.0")
  private Double latitude;
  @NotNull
  @DecimalMin(value = "-90.0")
  @DecimalMax(value = "90.0")
  private Double longitude;
  
  private String searchFor;

  // public GetRestaurantsRequest(double d, double e) {
  //   latitude = d;
  //   longitude = e;
  // } 

  public GetRestaurantsRequest(double d, double e) {
    this.latitude = d;
    this.longitude = e;
  }

  // @RequestMapping(path = "/restaurants", method = RequestMethod.GET)
  public void setLongitude(@RequestParam(value = "longitude", required = true) Double longitude) {
    this.longitude = longitude;
  }

  // @RequestMapping(value = "/restaurants", params = { "latitude" }, method = RequestMethod.GET)
  public void setLatitude(@RequestParam(value = "latitude", required = true) Double latitude) {
    System.out.println("here lati");
    this.latitude = latitude;
  }

  // @RequestMapping(value = "/restaurants", params = { "searchFor"}, method = RequestMethod.GET)
  public void setSearchFor(@RequestParam(value = "searchFor", required = false) 
      String searchFor) {
    System.out.println("here");
    this.searchFor = searchFor;
  }
  
  public Double getLongitude() {
    return this.longitude;
  }

  public Double getLatitude() {
    return this.latitude;
  }

  public String getSearchFor() {
    return this.searchFor;
  }

}
