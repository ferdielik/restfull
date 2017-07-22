package com.ferdielik.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ferdielik.entity.Announce;
import com.ferdielik.entity.AnnounceType;

@Repository
@Transactional
public class AnnounceDAO extends AbstractDAO<Announce>
{
    public List<Announce> getAnnounces(AnnounceType type)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Criteria criteria = currentSession.createCriteria(Announce.class);
        criteria.add(Restrictions.eq("type", type));

        return criteria.list();
    }
}
