package com.example.bank.api;


import com.example.bank.model.dto.AccountDto;
import com.example.bank.model.entity.Account;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "Account")
@RequestMapping("/account")
public interface AccountAPI {

  @Operation(summary = "Update an existing account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "application/json"
          , schema = @Schema(implementation = AccountDto.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
      @ApiResponse(responseCode = "404", description = "Account not found", content = @Content),
      @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content)})
  @PutMapping("/update-account")
  ResponseEntity updateAccount(@Valid @ApiParam(value = "Customer for updating", required = true)
                                            AccountDto accountDto);

  @Operation(summary = "Add a new account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "application/json"
          , schema = @Schema(implementation = Account.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
      @ApiResponse(responseCode = "404", description = "Not found!", content = @Content)})
  @PostMapping("add-account")
  ResponseEntity<AccountDto> addAccount(@ApiParam(required = true)
                                               @RequestBody AccountDto accountDto);

  @Operation(summary = "Find account by ID", description = "Returns a single account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "application/xml"
          , schema = @Schema(implementation = Account.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
      @ApiResponse(responseCode = "404", description = "Account not found")})
  @GetMapping("/get-account/{accountId}")
  ResponseEntity<AccountDto> getAccountById(@ApiParam(value = "ID of account to return", required = true)
                                                   @PathVariable("accountId") Long accountId);

  @Operation(summary = "Deletes an account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
      @ApiResponse(responseCode = "404", description = "Account not found")})
  @DeleteMapping("/delete-account/{accountId}")
  ResponseEntity deleteAccount(@ApiParam(value = "Account id to delete")
                                      @PathVariable("accountId") Long accountId);
}
