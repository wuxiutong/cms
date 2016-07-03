/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wxt.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils{
    private static final SessionFactory sessionFactory = buildSessionFactory() ;
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration
                    .buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }
        catch (Throwable ex) {
// Make sure you log the exception, as it might be swallowed
            System. err. println("Initial SessionFactory creation failed." + ex) ;
            throw new ExceptionInInitializerError(ex) ;
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
  
    public static Session getSession(){
    try {
        return sessionFactory.openSession();
    }
    catch (Exception e1){
        e1.printStackTrace();
        return null;
    }
    }  
  
    public static void closeSession(Session session){
        if (session != null){  
            if (session!=null){
                session.close();  
            }  
        }  
    }  
} 
