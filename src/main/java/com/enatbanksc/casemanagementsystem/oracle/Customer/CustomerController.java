//package com.enatbanksc.casemanagementsystem.oracle.Customer;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/customers")
//@RequiredArgsConstructor
//public class CustomerController implements CustomerApi{
//    private final CustomerMapper customerMapper;
//    private final CustomerService customerService;
//    @Override
//    public CustomerDto getCustomerByAccountNumber(String accountNumber) {
//        return customerMapper.toCustomerDto(customerService.getCustomerByAccountNumber(accountNumber));
//    }
//}
