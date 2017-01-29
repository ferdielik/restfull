package com.ferdielik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ferdielik.dao.TestDAO;
import com.ferdielik.entity.Test;

/**
 * Created by ferdielik on 29/01/2017.
 */
@Controller
@RequestMapping("/rest")
public class RestController
{
    @Autowired
    TestDAO testDAO;

    @ResponseBody
    @RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
    public Test getEmployee(@PathVariable("id") int empId)
    {
        return testDAO.getById(Long.valueOf(empId));
    }

    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public List<Test> getAll()
    {
        return testDAO.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/create-dummy-data", method = RequestMethod.POST)
    public void createDummyData()
    {
        Test test = new Test();
        test.setName("test");

        testDAO.saveOrUpdate(test);


        test = new Test();
        test.setName("test 2");

        testDAO.saveOrUpdate(test);


        test = new Test();
        test.setName("test3 ");

        testDAO.saveOrUpdate(test);

    }
}
