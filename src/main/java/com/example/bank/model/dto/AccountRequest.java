package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Builder
@Getter
@ToString
@JsonDeserialize(builder = AccountRequest.AccountRequestBuilder.class)
public class AccountRequest {

    @ApiModelProperty(example = "64", value = "id", required = true)
    Integer id;

    @ApiModelProperty(example = "64", value = "customerId", required = true)
    Integer customerId;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountRequestBuilder {
    }
}
