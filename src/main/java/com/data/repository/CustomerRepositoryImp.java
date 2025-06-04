package com.data.repository;

import com.data.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
//      try (Session session = sessionFactory.openSession()) {
//        Transaction tx = session.beginTransaction();
//        ProductCart cart = session.get(ProductCart.class, cartId);
//        if (cart != null) {
//            cart.setQuantity(quantity);
//            session.update(cart);
//            tx.commit();
//            return true;
//        }
//        return false;
//    } catch (Exception e) {
//        e.printStackTrace();
//        return false;
//    }
    @Override
    public boolean updateCustomer(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Customer customers = session.get(Customer.class, customer.getId());
            if (customers != null) {
                customers.setFirstName(customer.getFirstName());
                customers.setLastName(customer.getLastName());
                customers.setEmail(customer.getEmail());
                customers.setPhone(customer.getPhone());
                customers.setAddress(customer.getAddress());
                customers.setFileImage(String.valueOf(customer.getFileImage()));
                session.update(customers);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteCustomer(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            if (c != null) {
                session.delete(c);
                tx.commit();
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
