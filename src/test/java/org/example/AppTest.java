package org.example;

import org.example.dao.CustomerDAO;
import org.example.entity.Customer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AppTest 
{
    @Test
    public void createCustomer()
    {
        Customer customer = new Customer();
        customer.setFirstName("Jean");
        customer.setLastName("Valjean");

        CustomerDAO.create(customer);

        assertTrue(true);
    }

    @Test
    public void findById()
    {
        Customer customer = new Customer();
        customer.setFirstName("Jean");
        customer.setLastName("Valjean");

        CustomerDAO.create(customer);

        Customer customer1 = CustomerDAO.findById(customer.getId());

        assertEquals("Jean",customer1.getFirstName());
    }

    @Test
    public void dontFindById()
    {
        Customer customer = CustomerDAO.findById(5);
        assertNull(customer);
    }

    @Test
    public void findAll()
    {
        CustomerDAO.create(new Customer("Marie"));
        CustomerDAO.create(new Customer("Michel"));
        CustomerDAO.create(new Customer("Alex"));

        List<Customer> customers = CustomerDAO.findAll();
        assertEquals(3,customers.size());
    }

}