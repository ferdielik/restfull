package com.ferdielik.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ferdielik.entity.Test;

// todo
public abstract class AbstractDAO<T>
{
    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDAO()
    {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public T getById(Long id)
    {
        return getCurrentSession().get(persistentClass, id);
    }

    public void deleteById(Long id)
    {
        getCurrentSession().delete(getById(id));
    }

    public List<Test> getAll()
    {
        return getCurrentSession().createCriteria(persistentClass).list();
    }

    public void saveOrUpdate(T test)
    {
        getCurrentSession().saveOrUpdate(test);
    }
}
