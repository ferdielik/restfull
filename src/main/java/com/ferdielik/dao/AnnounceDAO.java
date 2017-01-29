package com.ferdielik.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ferdielik.entity.Announce;
import com.ferdielik.entity.Test;

@Repository
@Transactional
public class AnnounceDAO
{
    @Autowired
    SessionFactory sessionFactory;

    public Announce getById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Announce.class, id);
    }

    public void delete(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(getById(id));
    }

    public List<Announce> getAll()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createCriteria(Announce.class).list();
    }


    public void saveOrUpdate(Announce announce)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(announce);
    }


}
