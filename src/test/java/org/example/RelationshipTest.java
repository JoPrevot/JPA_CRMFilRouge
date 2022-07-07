package org.example;

import org.example.dao.AddressDAO;
import org.example.dao.CustomerDAO;
import org.example.dao.PaymentDAO;
import org.example.dao.ProductDAO;
import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.Payment;
import org.example.entity.Product;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RelationshipTest {

    @Test
    public void oneToOne()
    {
        Payment p = new Payment();
        p.setCardNumber("010101010101");
        p.setConfidentialCode("3630");
        p.setBank("Allo pere noel");

        PaymentDAO.createPayment(p);

        Customer customer = new Customer();
        customer.setFirstName("Jean");
        customer.setLastName("Valjean");
        customer.setPayment(p);

        CustomerDAO.create(customer);

        /*********************/

        Customer c = CustomerDAO.findById(customer.getId());
        System.out.println(c.getPayment().getBank());
        assertNotNull(c.getPayment());
    }

    @Test
    public void manyToOne()
    {
        Address adresse1 = new Address();
        adresse1.setStreet("Rue du vieux port");
        adresse1.setCity("Marseille");
        adresse1.setCountry("France");
        adresse1.setZipCode("13000");

        AddressDAO.createAddress(adresse1);

        Customer alain = new Customer();
        alain.setFirstName("Alain");
        alain.setLastName("Valjean");
        alain.setDeliveryAddress(adresse1);

        CustomerDAO.create(alain);

        Customer brigitte = new Customer();
        brigitte.setFirstName("Brigitte");
        brigitte.setLastName("Valjean");
        brigitte.setDeliveryAddress(adresse1);

        CustomerDAO.create(brigitte);
    }

    @Test
    public void ManyToMany()
    {
        Product ipad = new Product("Ipad","Tablette",50);
        Product gourde = new Product("gourde","1.2L",15);

        ProductDAO.createProduct(ipad);
        ProductDAO.createProduct(gourde);

        Customer alain = new Customer();
        alain.setFirstName("Alain");
        alain.setLastName("Valjean");
        alain.addProduct(gourde);
        alain.addProduct(ipad);

        CustomerDAO.create(alain);

        Customer brigitte = new Customer();
        brigitte.setFirstName("Brigitte");
        brigitte.setLastName("Valjean");
        brigitte.addProduct(gourde);
        brigitte.addProduct(ipad);

        CustomerDAO.create(brigitte);

        /**********************/

        for(Product product : brigitte.getProducts())
        {
            System.out.println(product);
        }
    }
}
