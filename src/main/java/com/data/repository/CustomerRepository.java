package com.data.repository;

import com.data.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> listCustomers();
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
    List<Customer> getCustomersByName(String name);
}
