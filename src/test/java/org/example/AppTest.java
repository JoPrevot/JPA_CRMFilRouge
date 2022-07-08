package org.example;

import org.example.dao.CustomerDAO;
import org.example.dao.PaymentDAO;
import org.example.entity.Customer;
import org.example.entity.Payment;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AppTest {
    @Test
    public void createCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Jean");
        customer.setLastName("Valjean");

        CustomerDAO.create(customer);

        assertTrue(true);
    }

    @Test
    public void findById() {
        Customer customer = new Customer();
        customer.setFirstName("Jean");
        customer.setLastName("Valjean");

        CustomerDAO.create(customer);

        Customer customer1 = CustomerDAO.findById(customer.getId());

        assertEquals("Jean", customer1.getFirstName());
    }

    @Test
    public void dontFindById() {
        Customer customer = CustomerDAO.findById(5);
        assertNull(customer);
    }

    @Test
    public void findAll() {
        CustomerDAO.create(new Customer("Marie"));
        CustomerDAO.create(new Customer("Michel"));
        CustomerDAO.create(new Customer("Alex"));

        List<Customer> customers = CustomerDAO.findAll();
        assertEquals(3, customers.size());
    }

    @Test
    public void deleteCustomer() {
        Customer marie = new Customer("Marie");
        CustomerDAO.create(marie);

        List<Customer> customers = CustomerDAO.findAll();
        assertEquals(1, customers.size());

        CustomerDAO.delete(marie);

        customers = CustomerDAO.findAll();
        assertEquals(0, customers.size());
    }

    @Test
    public void deleteCustomerById() {
        CustomerDAO.create(new Customer("Marie"));
        CustomerDAO.create(new Customer("Michel"));
        CustomerDAO.create(new Customer("Alex"));
//        Customer jean = new Customer("Jean");
//        CustomerDAO.create(jean);

        List<Customer> customers = CustomerDAO.findAll();
        assertEquals(3, customers.size());

        CustomerDAO.deleteCustomerById(3);

//        CustomerDAO.deleteCustomerById(jean.getId());

        customers = CustomerDAO.findAll();
        assertEquals(2, customers.size());
    }
    @Test
    public void deleteCustomerByIdV2() {
        Customer marie = new Customer("Marie");
        CustomerDAO.create(marie);
        Customer michel = new Customer("Michel");
        CustomerDAO.create(michel);
        Long michelId = michel.getId();
        Customer alex = new Customer("Alex");
        CustomerDAO.create(alex);

        CustomerDAO.deleteCustomerByIdV2(michel.getId());

        assertNull(CustomerDAO.findById(michelId));
        assertNotNull(CustomerDAO.findById(marie.getId()));
        assertNotNull(CustomerDAO.findById(alex.getId()));

        for(Customer c : CustomerDAO.findAll()){
            System.out.println(c);
        }

        System.out.println(michelId);
        Customer c = CustomerDAO.findById(michelId);
        System.out.println(c);
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Alain");
        customer.setLastName("Delon");
        customer.setAddress("rue de la mairie");
        customer.setCity("Paris");
        customer.setCompanyName("Google");
        customer.setCountry("France");
        customer.setEmail("alain@delon.fr");
        customer.setPhone("0606060606");
        customer.setZipCode("75000");
        customer.setState(1);

        CustomerDAO.create(customer);

        /********************/

        Customer newCustomerData = new Customer();
        newCustomerData.setFirstName("Jean-Paul");
        newCustomerData.setLastName("Belmondo");

        CustomerDAO.update(customer.getId(), newCustomerData);

        assertEquals("Jean-Paul", newCustomerData.getFirstName());
        assertEquals("Belmondo", newCustomerData.getLastName());
    }

    @Test
    public void selectWhere() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Alain");
        customer1.setLastName("Delon");
        CustomerDAO.create(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Alain");
        customer2.setLastName("Prost");
        CustomerDAO.create(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Marie");
        customer3.setLastName("Dupont");
        CustomerDAO.create(customer3);

        /*****************/

        List<Customer> alains = CustomerDAO.findByFirstName("Alain");

        /*****************/

        assertEquals(2, alains.size());

        for (Customer c : alains) {
            if (!c.getFirstName().equals("Alain")) {
                assertTrue(false);
            }
        }
    }

    // Partie Exercice

    @Test
    public void createPayment() {
        Payment payment = new Payment();
        payment.setCardNumber("010101010101");
        payment.setConfidentialCode("3630");
        payment.setBank("Allo père noel");

        PaymentDAO.createPayment(payment);

        assertTrue(true);
    }

    @Test
    public void findAllPayment() {
        PaymentDAO.createPayment(new Payment("010101010101","3630","Allo pere noel"));
        PaymentDAO.createPayment(new Payment("020202020202","1212","Pages jaunes"));
        PaymentDAO.createPayment(new Payment("030303030303","8080","Rothschild"));

        List<Payment> payments = PaymentDAO.findAllPayment();

        assertEquals(3, payments.size());
    }

    @Test
    public void findPaymentById() {
        Payment payment = new Payment();
        payment.setCardNumber("010101010101");
        payment.setConfidentialCode("3630");
        payment.setBank("Allo père noel");

        PaymentDAO.createPayment(payment);

        Payment paymentFind = PaymentDAO.findPaymentById(payment.getId());

        assertEquals("3630", paymentFind.getConfidentialCode());
    }

    @Test
    public void updatePayment() {
        Payment payment = new Payment();
        payment.setCardNumber("010101010101");
        payment.setConfidentialCode("3630");
        payment.setBank("Allo père noel");

        PaymentDAO.createPayment(payment);

        /********************/

        Payment newPaymentData = new Payment();
        newPaymentData.setConfidentialCode("9999");
        newPaymentData.setBank("Santa Capitalism");

        PaymentDAO.updatePayment(payment.getId(),newPaymentData);

        assertEquals("Santa Capitalism", newPaymentData.getBank());
    }
    @Test
    public void deletePayment() {
        Payment boulangerie = new Payment("010101010101","3630","Baguette Invest");
        PaymentDAO.createPayment(boulangerie);

        List<Payment> payments = PaymentDAO.findAllPayment();
        assertEquals(1, payments.size());

        PaymentDAO.deletePayment(boulangerie);

        payments = PaymentDAO.findAllPayment();
        assertEquals(0, payments.size());
    }
    @Test
    public void deletePaymentById() {
        PaymentDAO.createPayment(new Payment("010101010101","3630","Allo pere noel"));
        PaymentDAO.createPayment(new Payment("020202020202","1212","Pages jaunes"));
        PaymentDAO.createPayment(new Payment("030303030303","8080","Rothschild"));

        List<Payment> payments = PaymentDAO.findAllPayment();
        assertEquals(3, payments.size());

        PaymentDAO.deletePaymentById(3);

//        CustomerDAO.deleteCustomerById(jean.getId());

        payments = PaymentDAO.findAllPayment();
        assertEquals(2, payments.size());
    }

}
