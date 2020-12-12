package main.java.dao;

import main.java.entity.University;
import main.java.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UniversityDao {

    public Long save(University university){

        Transaction transaction = null;
        Long persistentUniversityId = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            persistentUniversityId = (Long) session.save(university);

            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

        return persistentUniversityId;
    }
}
