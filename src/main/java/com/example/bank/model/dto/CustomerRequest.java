package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonDeserialize(builder = CustomerRequest.CustomerRequestBuilder.class)
public class CustomerRequest {

  @ApiModelProperty(value = "username", required = true)
  String userName;

  @ApiModelProperty(value = "firstname", required = true)
  String firstName;

  @ApiModelProperty(value = "lastname", required = true)
  String lastName;

  @ApiModelProperty(value = "email")
  String email;

  @ApiModelProperty(value = "phone")
  String phone;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonPOJOBuilder(withPrefix = "")
  public static class CustomerRequestBuilder {}
}
