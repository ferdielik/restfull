package com.ferdielik.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class BtcParser
{
    public static void main(String[] args) throws Exception
    {
        new BtcParser().run();
    }

    private void run() throws Exception
    {
        // dolllar: $('.piyasalar td')[6] -- https://www.garanti.com.tr/tr/
        // btc: $('#last-price strong')[0] -- https://www.bitstamp.net/
        // btcturk: $('.askPrice')[0] -- https://www.btcturk.com/
        // koinim: $('.nav-BTC-price')[0] -- https://koinim.com/
        // paribu: $('.header-summary-container span') -- https://www.paribu.com/

        HttpEntity entity = HttpUtil.get("https://www.bitstamp.net/api/v2/ticker/btcusd", null).getEntity();


        //        System.out.println(getValue(".piyasalar td", 6, "https://www.garanti.com.tr/tr/"));
        System.out.println(getValue("#last-price strong", 0, "https://www.bitstamp.net/"));
        System.out.println(getValue(".askPrice", 0, "https://www.btcturk.com/"));
        System.out.println(getValue(".nav-BTC-price", 0, "https://koinim.com/"));
        System.out.println(getValue(".header-summary-container span", 0, "https://www.paribu.com/"));
    }

    private String getValue(String find, int index, String url) throws IOException
    {
        Document doc = Jsoup.connect(url).get();
        Elements select = doc.select(find);

        return select.get(index).html();
    }


    class BtcSummary
    {
        private double dollar;
        private double bitStamp;
        private Date lastUpdate;

        private List<BtcAlternative> trPrices;

        public double getDollar()
        {
            return dollar;
        }

        public void setDollar(double dollar)
        {
            this.dollar = dollar;
        }

        public double getBitStamp()
        {
            return bitStamp;
        }

        public void setBitStamp(double bitStamp)
        {
            this.bitStamp = bitStamp;
        }

        public Date getLastUpdate()
        {
            return lastUpdate;
        }

        public void setLastUpdate(Date lastUpdate)
        {
            this.lastUpdate = lastUpdate;
        }

        public List<BtcAlternative> getTrPrices()
        {
            return trPrices;
        }

        public void setTrPrices(List<BtcAlternative> trPrices)
        {
            this.trPrices = trPrices;
        }
    }

    class BtcAlternative
    {
        private String label;
        private double value;
        private double diff;


        public String getLabel()
        {
            return label;
        }

        public void setLabel(String label)
        {
            this.label = label;
        }

        public double getValue()
        {
            return value;
        }

        public void setValue(double value)
        {
            this.value = value;
        }

        public double getDiff()
        {
            return diff;
        }

        public void setDiff(double diff)
        {
            this.diff = diff;
        }
    }
}
