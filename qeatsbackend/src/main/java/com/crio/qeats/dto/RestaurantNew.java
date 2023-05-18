
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class RestaurantNew {
  
  private String id;
  private String name;
  private String restaurantId; 
  private String city;
  private String imageUrl;
  private Double latitude;
  private Double longitude;
  private String opensAt;
  private String closesAt;
  private List<String> attributes;
  
}
