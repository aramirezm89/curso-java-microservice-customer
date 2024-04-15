package com.paymentchain.customer.api.controllers;


import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping()
    @Operation(summary = "return a customer list")
    public List<Customer>finAll(){
        return this.customerRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "return a customer by id")
    public Customer getById(@PathVariable Long id){
        return this.customerRepository.findById(id).orElseThrow();
    }

    @PostMapping
    @Operation(summary = "return a customer that was save in the DB")
    public ResponseEntity<Customer>createCustomer(@RequestBody Customer customerRequest){


        var customerSaved = this.customerRepository.save(customerRequest);

        return  ResponseEntity.ok(customerSaved);
    }

}
