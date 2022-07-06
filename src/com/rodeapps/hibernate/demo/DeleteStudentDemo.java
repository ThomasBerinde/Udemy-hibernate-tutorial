package com.rodeapps.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try {

            /** METHOD 1 */
            int studentId = 1;

            // create a session
            Session session= factory.getCurrentSession();
            session.beginTransaction();

            // we must retrieve the student we want to delete
            System.out.println("\nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);    // returns null if it doesn't exist
            System.out.println("Get complete: " + myStudent);

            // delete the student
//            System.out.println("Deleting student with id = 1");
//            session.delete(myStudent);

            // commit transaction
            session.getTransaction().commit();

            /** METHOD 2 */
            // create a session
            session= factory.getCurrentSession();
            session.beginTransaction();

            // delete with a query
            System.out.println("Delete student Mary");

            session.createQuery("delete from Student where firstName = 'Mary'").executeUpdate();

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
