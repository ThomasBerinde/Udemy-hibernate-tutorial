package com.rodeapps.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    // This inserts a student into the database

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create a session
        Session session= factory.getCurrentSession();

        try {
            // start transaction
            session.beginTransaction();

            // query the students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // display the students
            displayStudents(theStudents);

            // query the students: lastName = "Doe"
            theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            // display the students
            System.out.println("\n\nStudents that have the last name of Doe");
            displayStudents(theStudents);

            // query the students: lastName = "Doe" or firstName = "Daffy"
            theStudents = session.createQuery("from Student s where s.lastName='Doe'" +
                    " OR s.firstName='Daffy'").getResultList();

            // display the students
            System.out.println("\n\nStudents that have the last name of Doe or first name of Daffy");
            displayStudents(theStudents);

            // query the students: email ends in "@rodeapps.com"
            theStudents = session.createQuery("from Student s where s.email LIKE '%@rodeapps.com'").getResultList();

            // display the students
            System.out.println("\n\nStudents that have the email ending in '@rodeapps.com'");
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
             factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents)
            System.out.println(tempStudent);
    }
}
