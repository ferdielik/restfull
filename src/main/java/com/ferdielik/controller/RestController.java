package com.ferdielik.controller;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ferdielik.dao.AnnounceDAO;
import com.ferdielik.dao.TestDAO;
import com.ferdielik.entity.Announce;
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

    @Autowired
    private AnnounceDAO announceDAO;

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

    @ResponseBody
    @RequestMapping(value = "/get-main-announces", method = RequestMethod.POST)
    public void getMainAnnounces() throws Exception
    {
        Document doc = Jsoup.connect("http://bilgisayar.kocaeli.edu.tr/").get();
        Elements newsHeadlines = doc.select(".contentList");
        Elements elements = newsHeadlines.get(0).select(".item");

        elements.forEach(element -> {
            String title = element.select(".mainInfo .title a").html();
            String content = element.select(".mainInfo .title .duyuruMetni").html();
            content = Jsoup.clean(content, new Whitelist());

            Announce announce = new Announce();
            announce.setContent(content);
            announce.setTitle(title);

            announceDAO.saveOrUpdate(announce);
        });
    }
}
