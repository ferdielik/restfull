package com.ferdielik.util;

import com.ferdielik.entity.Station;
import com.ferdielik.entity.StationStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class KobisParser {
    private static final String KOBIS_STATIONS_URL = "http://www.kobis.com.tr/istasyonlar.aspx";

    private static final int NAME_INDEX = 1;
    private static final int TOWN_INDEX = 3;
    private static final int STATUS_INDEX = 5;
    private static final int EMPTY_INDEX = 7;
    private static final int BICYCLE_INDEX = 9;
    private static final int UPDATE_INDEX = 11;

    public List<Station> parseAllStations() throws Exception {
        List<Station> stations = new ArrayList<>();

        Document doc = Jsoup.connect(KOBIS_STATIONS_URL).get();
        Elements elements = doc.select(".box");

        elements.forEach(element ->
        {
            Station station = buildStation(element);
            stations.add(station);

        });

        return stations;
    }

    private Station buildStation(Element element) {
        Station station = new Station();
        station.setLastUpdate(new Date());
        station.setEmpty(getEmptyPark(element));
        station.setBicycle(getBicycle(element));
        station.setStatus(getStatus(element));
        station.setName(getName(element));
        station.setTown(getTown(element));

        return station;
    }

    private String getTown(Element element) {
        return element.select(".boxheader td").get(TOWN_INDEX).html();
    }

    private String getName(Element element) {
        String name = element.select(".boxheader td").get(NAME_INDEX).html();
        String index = name.split("\\.")[0];
        return name.replaceFirst(index + ".", "").trim();
    }

    private StationStatus getStatus(Element element) {
        String label = element.select(".boxheader td").get(STATUS_INDEX).html();
        return StationStatus.byLabel(label);
    }

    private int getEmptyPark(Element element) {
        return returnNullIfException(element.select(".boxheader td").get(EMPTY_INDEX));
    }

    private int getBicycle(Element element) {
        return returnNullIfException(element.select(".boxheader td").get(BICYCLE_INDEX));
    }

    private int returnNullIfException(Element element) {
        try {
            return Integer.valueOf(element.html());
        } catch (Exception e) {
            return -1;
        }
    }

}
