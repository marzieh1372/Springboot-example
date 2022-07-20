package com.example.bank.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.loader.custom.ScalarReturn;

@Builder
@Getter
@ToString
@JsonDeserialize(builder = CustomerRequest.CustomerRequestBuilder.class)
public class CustomerRequest {

  @ApiModelProperty(value = "id", example = "64")
  Integer id;

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
  public static final class CustomerRequestBuilder{}
}
