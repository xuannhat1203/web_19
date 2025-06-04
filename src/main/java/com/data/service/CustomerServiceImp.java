package com.data.service;

import com.data.entity.Customer;
import com.data.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CustomerServiceImp implements CustomerService {
    public CustomerRepository customerRepository;
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public List<Customer> listCustomers() {
        return customerRepository.listCustomers();
    }
    @Override
    public boolean addCustomer(Customer customer) {
        return customerRepository.addCustomer(customer);
    }
    @Override
    public boolean updateCustomer(Customer customer) {
        return customerRepository.updateCustomer(customer);
    }
    @Override
    public boolean deleteCustomer(int id) {
        return customerRepository.deleteCustomer(id);
    }
    @Override
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.getCustomersByName(name);
    }
}
