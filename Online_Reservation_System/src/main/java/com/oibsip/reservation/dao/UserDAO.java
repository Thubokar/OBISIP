package com.oibsip.reservation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.oibsip.reservation.entity.User;
import com.oibsip.reservation.util.HibernateUtil;

public class UserDAO {

    private final SessionFactory sessionFactory =
            HibernateUtil.getSessionFactory();

    public User authenticate(String username, String password) {

        try (Session session = sessionFactory.openSession()) {

            return session.createQuery(
                    "FROM User u WHERE u.username = :username " +
                    "AND u.password = :password",
                    User.class
            )
            .setParameter("username", username)
            .setParameter("password", password)
            .uniqueResult();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Login authentication failed.",
                    e
            );
        }
    }
}