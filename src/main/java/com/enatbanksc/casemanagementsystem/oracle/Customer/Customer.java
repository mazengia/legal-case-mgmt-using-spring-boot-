//package com.enatbanksc.casemanagementsystem.oracle.Customer;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Data
//@Entity(name = "STTM_CUSTOMER")
//@NamedNativeQuery(name = "Customer.findByCustomerAccountNumber", query = "SELECT STTM_CUSTOMER.CUSTOMER_NO as customerNumber,CUST_AC_NO as accountNumber,FIRST_NAME as firstName,\n" +
//        "MIDDLE_NAME as middleName,LAST_NAME as lastName, MOBILE_NUMBER as phoneNumber from STTM_CUSTOMER JOIN STTM_CUST_ACCOUNT ON STTM_CUST_ACCOUNT.CUST_NO= STTM_CUSTOMER.CUSTOMER_NO JOIN \n" +
//        "sttm_cust_personal ON STTM_CUST_PERSONAL.CUSTOMER_NO = STTM_CUSTOMER.CUSTOMER_NO WHERE STTM_CUST_ACCOUNT.CUST_AC_NO=?",
//        resultSetMapping = "Customer")
//@SqlResultSetMapping(name = "Customer",
//        classes = @ConstructorResult(targetClass = Customer.class,
//                columns = {
//                        @ColumnResult(name = "customerNumber", type = String.class),
//                        @ColumnResult(name = "lastName", type = String.class),
//                        @ColumnResult(name="phoneNumber", type = String.class),
//                        @ColumnResult(name="middleName", type = String.class),
//                        @ColumnResult(name="firstName", type = String.class),
//                        @ColumnResult(name="accountNumber", type = String.class)
//                }))
//@NoArgsConstructor
//@AllArgsConstructor
//public class Customer {
//    private @Id String customerNumber;
//    private  String lastName;
//    private String phoneNumber;
//    private String middleName;
//    private String firstName;
//    private String accountNumber;
//}
