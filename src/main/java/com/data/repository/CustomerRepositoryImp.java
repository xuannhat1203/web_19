package com.data.repository;

import com.data.entity.Customer;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImp implements CustomerRepository {
    private final SessionFactory sessionFactory;

    public CustomerRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Customer> listCustomers() {
        return sessionFactory.openSession().createQuery("from Customer", Customer.class)
                .getResultList();
    }
    @Override
    public boolean addCustomer(Customer customer) {
        try {
            sessionFactory.openSession().save(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            sessionFactory.openSession().update(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteCustomer(int id) {
        try {
            Customer customer = sessionFactory.openSession().get(Customer.class, id);
            if (customer != null) {
                sessionFactory.openSession().delete(customer);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Customer> getCustomersByName(String name) {
        return sessionFactory.openSession()
                .createQuery("from Customer where firstName like :name or lastName like :name", Customer.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
}
