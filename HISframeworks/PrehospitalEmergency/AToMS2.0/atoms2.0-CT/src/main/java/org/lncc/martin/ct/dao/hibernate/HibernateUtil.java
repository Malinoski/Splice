/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

///Classe auxiliar para criação e encerramento de conexões

package org.lncc.martin.ct.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    SessionFactory sessionFactory;

    public Session getConnection() {

        try {
            Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(cfg.getProperties());

            StandardServiceRegistry standardServiceRegistry = sb.build();

            sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);

            return sessionFactory.openSession();

        } catch (Exception e) {
            System.out.println("Problemas ao criar Session Factory: " + e);
            return null;
        }
    }

    public void closeConnection() {
        try {
            sessionFactory.close();
        } catch (Exception e) {
            System.out.println("Problemas ao fechar conexão: " + e);
        }
    }

}
