package com.example.bank.api;

import com.example.bank.model.dto.AccountRequest;
import com.example.bank.model.entity.Account;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Account")
@RequestMapping("/account")
public interface AccountAPI {

  @Operation(summary = "Update an existing account")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK!",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = AccountRequest.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Account not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content)
      })
  @PutMapping("/update-account")
  ResponseEntity updateAccount(
          @ApiParam(value = "ID of account to update", required = true)
          @PathVariable("accountId")
          Integer accountId,
          @Validated // mamolan roye vorodi haye method va roye object ha!
          @ApiParam(value = "Account for updating", required = true) @RequestBody
          AccountRequest accountRequest);

  @Operation(summary = "Add a new account")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK!",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Account.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found!", content = @Content)
      })
  @PostMapping("/add-account")
  ResponseEntity<AccountRequest> addAccount(
      @ApiParam(required = true) @RequestBody AccountRequest accountRequest);

  @Operation(summary = "Find account by ID", description = "Returns a single account")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK!",
            content = {
              @Content(
                  mediaType = "application/xml",
                  schema = @Schema(implementation = Account.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Account not found")
      })
  @GetMapping("/get-account/{accountId}")
  ResponseEntity<AccountRequest> getAccountById(
      @ApiParam(value = "ID of account to return", example = "64", required = true)
          @PathVariable("accountId")
          Integer accountId);

  @Operation(summary = "Deletes an account")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Account not found")
      })
  @DeleteMapping("/delete-account/{accountId}")
  ResponseEntity deleteAccount(
      @ApiParam(value = "Account id to delete", example = "64", required = true)
          @PathVariable("accountId")
          Integer accountId);

  @Operation(summary = "Get All Accounts", description = "Returns list of account")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK!",
            content = {
              @Content(mediaType = "application/xml", schema = @Schema(implementation = List.class))
            })
      })
  @GetMapping("/get-accounts")
  ResponseEntity<List<Account>> getAllAccounts();
}
