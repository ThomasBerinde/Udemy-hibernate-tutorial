package com.rodeapps.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create a session
        Session session= factory.getCurrentSession();

        try {
            // use the session object to save Java object

            // create 3 student objects
            System.out.println("Creating 3 student objects");
            Student stud1 = new Student("John", "Doe", "john@rodeapps.com");
            Student stud2 = new Student("Mary", "Public", "mary@rodeapps.com");
            Student stud3 = new Student("Bonita", "Applebum", "bonita@rodeapps.com");

            // start transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the students...");
            session.save(stud1);
            session.save(stud2);
            session.save(stud3);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
