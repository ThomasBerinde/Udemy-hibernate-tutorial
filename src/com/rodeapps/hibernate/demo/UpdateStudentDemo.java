package com.rodeapps.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();



        try {
            int studentId = 1;

            // create a session
            Session session= factory.getCurrentSession();
            session.beginTransaction();

            // retrieve the student based on the id: primary key
            System.out.println("\nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);    // returns null if it doesn't exist
            System.out.println("Get complete: " + myStudent);

            // update the student
            // this will be cached in memory and the updating will be done when we commit the transaction
            // this happens automatically because myStudent is a persistent object retrieved in line 28
            System.out.println("Updating student");
            myStudent.setFirstName("Scooby");

            // commit transaction
            session.getTransaction().commit();

            // BULK UPDATE
            // create a session
            session= factory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("Update email for all students");

            session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
