package com.paymentchain.customer.api.controllers;


import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.repository.CustomerRepository;
import com.paymentchain.customer.services.CustomerSerivice;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerSerivice customerSerivice;

    @GetMapping()
    @Operation(summary = "return a customer list")
    public ResponseEntity<List<Customer>>finAll(){
        return ResponseEntity.ok(this.customerSerivice.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "return a customer by id")
    public ResponseEntity<?> getById(@PathVariable Long id){
     var customer = this.customerSerivice.getCustomerById(id);
     return ResponseEntity.ok(Objects.requireNonNullElse(customer, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "return a customer that was save in the DB")
    public ResponseEntity<Customer>createCustomer(@RequestBody Customer customerRequest){
       return ResponseEntity.ok(this.customerSerivice.createCustomer(customerRequest));
    }

    @PutMapping("{id}")
    @Operation(summary = "update a customer  in the DB")
    public ResponseEntity<?>updateCustomer(@PathVariable Long id,@RequestBody Customer request){
       var response = this.customerSerivice.updateCustomer(id,request);

        return ResponseEntity.ok(Objects.requireNonNullElse(response, HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("{id}")
    @Operation(summary = "Delete a Customer from the DB")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
     var customer =  this.customerSerivice.deleteCustomer(id);
      return ResponseEntity.ok(Objects.requireNonNullElse(customer,HttpStatus.NOT_FOUND));
    }

}
