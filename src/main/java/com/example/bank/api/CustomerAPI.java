package com.example.bank.api;

import com.example.bank.model.entity.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.validation.Valid;

@Api(tags = {"Customer"})
@RequestMapping("/customer")
public interface CustomerAPI {

  @Operation(summary = "Get all customers")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "list"
          , schema = @Schema(implementation = Customer.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request!", content = @Content),
      @ApiResponse(responseCode = "404", description = "Not found!", content = @Content)})
  @GetMapping("/customers")
  public ResponseEntity<List<Customer>> getAllCustomer();

  @Operation(summary = "Add a new customer")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "application/json"
          , schema = @Schema(implementation = Customer.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request!", content = @Content),
      @ApiResponse(responseCode = "404", description = "Not found!", content = @Content)})
  @PostMapping("/add-customer")
  public ResponseEntity<Customer> addCustomer(@ApiParam(value = "New customer to add", required = true)
                                              @Valid @RequestBody Customer customer);

  @Operation(summary = "Get customer by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "application/json"
          , schema = @Schema(implementation = Customer.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request!", content = @Content),
      @ApiResponse(responseCode = "404", description = "Not found!", content = @Content)})
  @GetMapping("/find-customer/{customerId}")
  public ResponseEntity<Customer> getCustomerById(@ApiParam(value = "Id of customer", required = true)
                                                  @PathVariable("customerId") Long customerId);
  @Operation(summary = "Update customer by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "application/json"
          , schema = @Schema(implementation = Customer.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request!", content = @Content),
      @ApiResponse(responseCode = "404", description = "Not found!", content = @Content)})
  @PutMapping("/update-customer")
  public ResponseEntity updateCustomer(@ApiParam(value = "Customer for updating") Customer customer);

  @Operation(summary = "Delete customer by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "application/json"
          , schema = @Schema(implementation = Customer.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request!", content = @Content),
      @ApiResponse(responseCode = "404", description = "Not found!", content = @Content)})
  @DeleteMapping("/delete-customer/{customerId}")
  public ResponseEntity deleteCustomer(@ApiParam(value = "customer id for deleting", required = true)
                                       @PathVariable(value = "customerId") Long customerId);
}
