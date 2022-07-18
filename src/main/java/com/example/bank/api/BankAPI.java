package com.example.bank.api;


import com.example.bank.model.dto.AccountDepositDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Api(tags = "Bank api")
@Tag(name = "bank")
@RequestMapping("/bank/account")
public interface BankAPI {

  @Operation(summary = "Returns account balance", description = "Returns account balance in SEK")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json"
        , schema = @Schema(implementation = AccountDepositDto.class))})})
  @GetMapping("/{accountId}/balance")
  ResponseEntity<BigDecimal> getBalance(@ApiParam(name = "accountId",value = "ID of account to return", example = "64",required = true)
                                        @PathVariable("accountId") AccountDepositDto AccountDepositDto);

  @Operation(summary = "Deposit to account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
      schema = @Schema(implementation = AccountDepositDto.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid Deposit")
  })
  @PostMapping("/{accountId}/deposit")
  ResponseEntity depositToAccount(@ApiParam(value = "ID of account to deposit to",required = true)
                                  @PathVariable("accountId") AccountDepositDto accountDepositDto);

  @Operation(summary = "Withdraw amount from account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
      schema = @Schema(implementation = AccountDepositDto.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid Withdrawal")
  })
  @PostMapping("/{accountId}/withdrawal")
  ResponseEntity withdrawal(@ApiParam(value = "ID of account to withdraw from", example = "64", required = true)
                            @PathVariable("accountId") AccountDepositDto accountDepositDto);
}
