package com.ferdielik.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TestDAO
{
    @Autowired
    SessionFactory sessionFactory;

    public Test getById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Test.class, id);
    }

    public void delete(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(getById(id));
    }

    public List<Test> getAll()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createCriteria(Test.class).list();
    }


    public void saveOrUpdate(Test test)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(test);
    }


}
