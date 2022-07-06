package com.rodeapps.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

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
            // use the session object to save Java object

            // create a student object
            System.out.println("Creating a new student object");
            Student tempStudent = new Student("Daffy", "Duck", "daffy@rodeapps.com");

            // start transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            // MY NEW CODE
            // find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + tempStudent.getId());

            // now get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve the student based on the id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());    // returns null if it doesn't exist
            System.out.println("Get complete: " + myStudent);

            // commit the transaction
            session.getTransaction().commit();


            System.out.println("Done!");
        }
        finally {
             factory.close();
        }
    }
}
