package com.ferdielik.util;

import com.ferdielik.entity.Announce;

import java.util.List;

/**
 * Created by ferdielik on 07/04/2017.
 */
public class App {
    public static void main(String[] args) throws Exception {
        KouParser kouParser = new KouParser();
        List<Announce> announces = kouParser.parseAllAnnounces();
        System.out.println("done.");
    }
}
