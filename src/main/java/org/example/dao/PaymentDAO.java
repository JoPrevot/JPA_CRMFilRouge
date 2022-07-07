package org.example.dao;

import org.example.entity.Customer;
import org.example.entity.Payment;
import org.example.jpa.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class PaymentDAO {

    public static void createPayment(Payment paymentToCreate)
    {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(paymentToCreate);
        tx.commit();
    }

    public static List<Payment> findAllPayment()
    {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        Query findAllQuery = entityManager.createQuery("select p from Payment p");

        findAllQuery.getResultList();

        return findAllQuery.getResultList();
    }

    public static Payment findPaymentById(long id)
    {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        Payment payment = entityManager.find(Payment.class, id);

        return payment;
    }

    public static void updatePayment(Long id, Payment newPaymentData)
    {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        Payment paymentToUpdate = entityManager.find(Payment.class,id);

        paymentToUpdate.setNotNullDataPayment(newPaymentData);

        EntityTransaction tx = entityManager.getTransaction();;

        try
        {
            tx.begin();
            entityManager.merge(paymentToUpdate);
            tx.commit();
        }
        catch (Exception e)
        {
            tx.rollback();
        }

    }

    public static void deletePayment(Payment payment)
    {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();
        entityManager.remove(payment);
        tx.commit();
    }

    public static void deletePaymentById(long id)
    {
        Payment paymentToDelete = findPaymentById(id);
        deletePayment(paymentToDelete);
    }
}
