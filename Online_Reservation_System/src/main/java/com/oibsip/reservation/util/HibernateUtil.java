package com.oibsip.reservation.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.oibsip.reservation.entity.Reservation;
import com.oibsip.reservation.entity.Train;
import com.oibsip.reservation.entity.User;

public class HibernateUtil {

    private static final SessionFactory sessionFactory =
            new Configuration()
                    .configure()
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Train.class)
                    .addAnnotatedClass(Reservation.class)
                    .buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}