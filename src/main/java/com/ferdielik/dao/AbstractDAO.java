package com.ferdielik.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

public abstract class AbstractDAO<T>
{
    protected Class<T> persistentClass;

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    private HikariDataSource dataSource;

    @SuppressWarnings("unchecked")
    public AbstractDAO()
    {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    protected JdbcTemplate getJdbcTemplate()
    {
        return new JdbcTemplate(dataSource);
    }

    protected Criteria createCriteria()
    {
        return getSession().createCriteria(persistentClass);
    }

    protected Criteria createCriteria(Class className)
    {
        return getSession().createCriteria(className);
    }

    public T getById(Long id)
    {
        return (T) getSession().get(persistentClass, id);
    }

    public void deleteById(Long id)
    {
        getSession().delete(getById(id));
    }

    public List<T> getAll()
    {
        return getSession().createCriteria(persistentClass).list();
    }

    public void saveOrUpdate(T test)
    {
        getSession().saveOrUpdate(test);
    }
}
