package com.ferdielik.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ferdielik.entity.Btc;

@Repository
@Transactional
public class BtcDAO
{
    @Autowired
    SessionFactory sessionFactory;

    public Btc getByR(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Btc.class, id);
    }

    public Btc getLast()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Btc.class);
        criteria.addOrder(Order.desc("date"));
        criteria.setMaxResults(1);
        return (Btc) DataAccessUtils.singleResult(criteria.list());
    }


    public void saveOrUpdate(Btc btc)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(btc);
    }
}
