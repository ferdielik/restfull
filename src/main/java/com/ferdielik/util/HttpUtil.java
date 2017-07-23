package com.ferdielik.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public final class HttpUtil
{
    public static String get(String url, Map<String, String> headers) throws Exception
    {
        HttpGet post = new HttpGet(url);
        addHeaders(headers, post);
        HttpResponse connect = connect(post);

        return getStringFromInputStream(connect.getEntity().getContent());
    }

    private static void addHeaders(Map<String, String> headers, HttpRequestBase method)
    {
        if (null != headers)
        {
            for (String header : headers.keySet())
            {
                method.setHeader(header, headers.get(header));
            }
        }
    }

    private static HttpResponse connect(HttpRequestBase method) throws Exception
    {
        HttpResponse response;
        CloseableHttpClient client = HttpClients.createDefault();
        try
        {
            response = client.execute(method);
        }
        finally
        {
            method.releaseConnection();
            client.close();
        }
        return response;
    }

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is)
    {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try
        {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}

