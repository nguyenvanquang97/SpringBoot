package com.example.CustomerDemo.mapper;

import com.example.CustomerDemo.model.Customer;
import com.example.CustomerDemo.model.CustomerPOJO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerPOJO customerToPojo(Customer customer);
    Customer pojoToCustomer(CustomerPOJO customerPOJO);
}
