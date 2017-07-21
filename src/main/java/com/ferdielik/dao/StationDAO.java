package com.ferdielik.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ferdielik.entity.Station;

@Repository
@Transactional
public class StationDAO extends AbstractDAO<Station>
{
    public Station getByName(String name)
    {
        Criteria criteria = createCriteria(Station.class);
        criteria.add(Restrictions.like("name", name));
        return (Station) DataAccessUtils.singleResult(criteria.list());
    }
}
