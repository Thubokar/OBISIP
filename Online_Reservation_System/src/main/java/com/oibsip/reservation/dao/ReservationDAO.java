package com.oibsip.reservation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.oibsip.reservation.entity.Reservation;
import com.oibsip.reservation.util.HibernateUtil;

public class ReservationDAO {

    private final SessionFactory sessionFactory =
            HibernateUtil.getSessionFactory();

    public void save(Reservation reservation) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            session.persist(reservation);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException(
                    "Failed to save reservation.",
                    e
            );
        }
    }

    public Reservation findByPnr(long pnr) {

        try (Session session = sessionFactory.openSession()) {

            return session.find(
                    Reservation.class,
                    pnr
            );
        }
    }

    public void delete(Reservation reservation) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            session.remove(
                    session.merge(reservation)
            );

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException(
                    "Failed to cancel reservation.",
                    e
            );
        }
    }
}