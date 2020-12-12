package main.java.dao;

import main.java.entity.Student;
import main.java.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.Optional;

public class StudentDao {

    public Long save(Student student){

        Transaction transaction = null;
        Long persistentStudentId = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            persistentStudentId = (Long) session.save(student);

            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

        return persistentStudentId;
    }


    public Student getById(Long id){
        Transaction transaction = null;
        Student persistentStudent = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            persistentStudent = Optional.ofNullable(session.get(Student.class, id))
                    .orElseThrow(
                            () -> new EntityNotFoundException(
                                    String.format("Student by a specified id: [%s] not found!", id)
                            )
                    );

            transaction.commit();

        }catch (HibernateException | EntityNotFoundException e){
            e.printStackTrace();
        }

        return persistentStudent;
    }

    public void update(Student student){
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Student persistentStudent = Optional.ofNullable(session.get(Student.class, student.getId()))
                    .orElseThrow(
                            () -> new EntityNotFoundException(
                                    String.format("Student by a specified id: [%s] not found!", student.getId())
                            )
                    );
            persistentStudent.setFirstName(student.getFirstName());
            persistentStudent.setAge(student.getAge());
            session.update(persistentStudent);

            transaction.commit();
        }catch (HibernateException | EntityNotFoundException e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateHQL(Student student){
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            String hqlQuery = "UPDATE Student set age = :age, firstName = :firstName WHERE id = :id";
            Query query = session.createQuery(hqlQuery);
            query.setParameter("id", student.getId());
            query.setParameter("age", student.getAge());
            query.setParameter("firstName", student.getFirstName());

            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);

            transaction.commit();
        }catch (HibernateException | EntityNotFoundException e){
            transaction.rollback();
            e.printStackTrace();
        }
    }


    public void delete(Long id){
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Student persistentStudent = Optional.ofNullable(session.get(Student.class, id))
                    .orElseThrow(
                            () -> new EntityNotFoundException(
                                    String.format("Student by a specified id: [%s] not found!", id)
                            )
                    );
            session.delete(persistentStudent);

            transaction.commit();
        }catch (HibernateException | EntityNotFoundException e){
            e.printStackTrace();
        }
    }




}
