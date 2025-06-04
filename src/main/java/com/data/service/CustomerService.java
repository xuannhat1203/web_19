package com.data.service;

import com.data.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listCustomers();
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
    List<Customer> getCustomersByName(String name);
}
