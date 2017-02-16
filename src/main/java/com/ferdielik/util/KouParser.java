package com.ferdielik.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.ferdielik.entity.Announce;
import com.ferdielik.entity.AnnounceType;

@Component
public class KouParser
{
    private static final String KOU_COMPUTER_URL = "http://bilgisayar.kocaeli.edu.tr/";

    public List<Announce> parseAllAnnounces() throws Exception
    {
        List<Announce> announces = new ArrayList<>();

        Document doc = Jsoup.connect(KOU_COMPUTER_URL).get();
        Elements newsHeadlines = doc.select(".contentList");
        Elements elements = newsHeadlines.get(0).select(".item");
        Elements eventElements = newsHeadlines.get(1).select(".item");

        elements.forEach(element ->
        {
            Announce announce = buildAnnounce(element, AnnounceType.GENERAL);
            announces.add(announce);

        });

        eventElements.forEach(element ->
        {
            Announce announce = buildAnnounce(element, AnnounceType.SECTION);
            announces.add(announce);
        });

        return announces;
    }

    private Announce buildAnnounce(Element element, AnnounceType type)
    {
        Announce announce = new Announce();
        announce.setContent(getContent(element));
        announce.setTitle(getTitle(element));
        announce.setType(type);
        announce.setAuthor(getAuthor(element));
        announce.setDate(getDate(element));

        return announce;
    }

    private String getAuthor(Element element)
    {
        return element.select(".mainInfo .author").html();
    }

    private String getTitle(Element element)
    {
        return element.select(".mainInfo .title a").html();
    }

    private String getContent(Element element)
    {
        String content = element.select(".mainInfo .title .duyuruMetni").html();
        content = Jsoup.clean(content, new Whitelist());

        return content;
    }

    private String getDate(Element element)
    {
        return element.select(".dateBox .day").html() + " " + element.select(".dateBox .month").html();
    }
}
