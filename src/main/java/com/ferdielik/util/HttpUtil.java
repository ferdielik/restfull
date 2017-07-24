package com.ferdielik.util;


import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public final class HttpUtil
{
    public static String get(String url, Map<String, String> headers) throws Exception
    {
        HttpGet httpReq = new HttpGet(url);
        return connect(httpReq, headers);
    }

    private static String connect(HttpRequestBase method, Map<String, String> headers) throws Exception
    {
        if (null != headers)
        {
            for (String key : headers.keySet())
            {
                method.setHeader(key, headers.get(key));
            }
        }

        String result;
        CloseableHttpResponse response;
        CloseableHttpClient client = HttpClients.createDefault();

        try
        {
            response = client.execute(method);
            result = EntityUtils.toString(response.getEntity());
        }
        finally
        {
            method.releaseConnection();
            client.close();
        }
        return result;
    }
}

