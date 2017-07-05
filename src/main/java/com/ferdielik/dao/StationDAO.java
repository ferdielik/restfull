package com.ferdielik.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ferdielik.entity.Station;

@Repository
@Transactional
public class StationDAO
{
    @Autowired
    SessionFactory sessionFactory;

    public Station getById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Station.class, id);
    }

    public Station getByName(String name)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Station.class);
        criteria.add(Restrictions.like("name", name));
        return (Station) DataAccessUtils.singleResult(criteria.list());
    }

    public void deleteAll()
    {
        getAll().forEach(station ->
        {
            delete(station.getId());
        });

    }

    public void delete(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(getById(id));
    }

    public List<Station> getAll()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createCriteria(Station.class).list();
    }


    public void saveOrUpdate(Station station)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(station);
    }
}
