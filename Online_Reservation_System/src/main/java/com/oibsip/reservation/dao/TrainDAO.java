package com.oibsip.reservation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.oibsip.reservation.entity.Train;
import com.oibsip.reservation.util.HibernateUtil;

public class TrainDAO {

    private final SessionFactory sessionFactory =
            HibernateUtil.getSessionFactory();

    public Train findByTrainNumber(int trainNumber) {

        try (Session session = sessionFactory.openSession()) {

            return session.find(
                    Train.class,
                    trainNumber
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to find train.",
                    e
            );
        }
    }
}