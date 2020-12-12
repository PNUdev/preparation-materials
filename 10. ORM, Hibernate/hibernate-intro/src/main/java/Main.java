package main.java;

import main.java.dao.StudentDao;
import main.java.dao.UniversityDao;
import main.java.entity.Student;
import main.java.entity.University;
import main.java.util.HibernateUtil;
import main.java.util.ValidationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class Main {

    private static StudentDao studentDao = new StudentDao();
    private static UniversityDao universityDao = new UniversityDao();

    public static void main(String[] args) {

        //<===1===>
        Student student = Student.builder()
                .age(20)
                .firstName("Max")
                .password("password1")
                .lastName("Green")
                .build();

//save
        Long persistentStudentId = studentDao.save(student);

//update
        student.setAge(1000);
        studentDao.update(student);

//getById
        Student foundStudent = studentDao.getById(persistentStudentId);
        //System.out.println("Found Student: "  + foundStudent);
        //exception
        studentDao.getById(persistentStudentId);

//delete
        studentDao.delete(persistentStudentId);




        //<===2===> lifecycle
//transient ---
        Student student2 = Student.builder()
                .age(202)
                .firstName("Max2")
                .password("password2")
                .lastName("Green2")
                .build();


        Transaction transaction = null;
        Long id2 = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

//persistent
            id2 = (Long) session.save(student2);
         //   student2.setFirstName("0000000");
        //session.evict(student2) OR trans.commit
            transaction.commit();

//detached
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

        studentDao.delete(id2);
//removed



        //<===3===> mapping and associations(@OneToMany, @ManyToOne)
        University university = University.builder().name("PNU").build();
        universityDao.save(university);
        Student student3 = Student.builder()
                .age(203)
                .firstName("Max3")
                .lastName("Green3")
                .password("password3")
                .university(university)
                .build();

        Long id3 = studentDao.save(student3);
        studentDao.delete(id3);



        //<====4====> Hibernate validation
        University university2 = University.builder().name("PNU").build();
        Student student4 = Student.builder()
                .age(204)
                .firstName(" ")
                .lastName("  ")
                .password(null)
                .university(university)
                .build();

        //success
        ValidationUtil.validate(university2);
        //error
        university2.setName(null);
        ValidationUtil.validate(university2);

      //  ValidationUtil.validate(student4);

        //<===5===> HQL
        Student student5 = Student.builder()
                .age(205)
                .firstName("Max5")
                .lastName("Green5")
                .password("password5")
                .university(university)
                .build();
        studentDao.save(student5);
        student5.setFirstName("55555");
        studentDao.updateHQL(student5);

    }
}
