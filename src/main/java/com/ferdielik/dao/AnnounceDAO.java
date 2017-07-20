package com.ferdielik.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ferdielik.entity.Announce;
import com.ferdielik.entity.AnnounceType;

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

    public void deleteAll()
    {
        getAll().forEach(announce ->
        {
            delete(announce.getId());
        });

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


    public List<Announce> getAnnounces(AnnounceType type)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Criteria criteria = currentSession.createCriteria(Announce.class);
        criteria.add(Restrictions.eq("type", type));

        return criteria.list();
    }
}
