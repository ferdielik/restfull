package com.ferdielik.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ferdielik.entity.Station;
import com.ferdielik.entity.StationStatus;

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

    public List<Station> load(StationStatus status)
    {
        Criteria criteria = createCriteria(Station.class);
        criteria.add(Restrictions.like("status", status));
        return criteria.list();
    }
}
