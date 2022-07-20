package com.example.bank.api;

import com.example.bank.model.dto.CustomerRequest;
import com.example.bank.model.entity.Customer;
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

import java.util.List;

import javax.validation.Valid;

@Tag(name = "customer")
@RequestMapping("/customer")
public interface CustomerAPI {

  @Operation(summary = "Get all customers")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "application/list"
          , schema = @Schema(implementation = Customer.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request!", content = @Content),
      @ApiResponse(responseCode = "404", description = "Not found!", content = @Content)})
  @GetMapping("/get-customers")
  ResponseEntity<List<Customer>> getAllCustomer();

  @Operation(summary = "Add a new customer")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "application/json"
          , schema = @Schema(implementation = Customer.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request!", content = @Content),
      @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content)})
  @PostMapping("/add-customer")
  ResponseEntity<Customer> addCustomer(@ApiParam(value = "New customer to add", required = true)
                                              @Valid @RequestBody CustomerRequest customerRequest);

  @Operation(summary = "Find customer by ID", description = "Return a single customer")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json"
          , schema = @Schema(implementation = Customer.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
      @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)})
  @GetMapping("/find-customer/{customerId}")
  ResponseEntity<Customer> getCustomerById(@ApiParam(value = "Id of customer", required = true)
                                                  @PathVariable("customerId") Integer customerId);
  @Operation(summary = "Update an existing customer")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK!", content = {@Content(mediaType = "application/json"
          , schema = @Schema(implementation = Customer.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
      @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Validation exception", content = @Content)})
  @PutMapping("/update-customer")
  ResponseEntity updateCustomer(@ApiParam(value = "Customer for updating", required = true) CustomerRequest customerRequest);

  @Operation(summary = "Deletes a customer")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
      @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)})
  @DeleteMapping("/delete-customer/{customerId}")
  ResponseEntity deleteCustomer(@ApiParam(value = "customer id for deleting", required = true)
                                       @PathVariable(value = "customerId") Integer customerId);
}
