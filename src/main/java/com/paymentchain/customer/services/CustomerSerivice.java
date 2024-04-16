package com.paymentchain.customer.services;


import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class CustomerSerivice {

    private CustomerRepository customerRepository;

    public List<Customer>getAllUsers(){
        return  this.customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){

        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);

        return optionalCustomer.orElse(null);


    }

    public Customer createCustomer(Customer customer){
        return this.customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer){
        Optional<Customer> customerOptional = this.customerRepository.findById(id);

        if(customerOptional.isPresent()){
            var updateCustomer = customerOptional.get();
            updateCustomer.setName(customer.getName());
            updateCustomer.setPhone(customer.getPhone());
            return this.customerRepository.save(updateCustomer);
        }else{
            return null;
        }
    }

    public Customer deleteCustomer(Long id){
        Optional<Customer> customer = this.customerRepository.findById(id);

        if(customer.isPresent()){
            this.customerRepository.delete(customer.get());
            return customer.get();
        }else{
            return null;
        }



    }


}
