package com.ferdielik.util;


import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;


public final class HttpUtil
{
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final int DEFAULT_TIMEOUT = 0;

    public static HttpResponse get(String url, Map<String, String> headers) throws Exception
    {
        HttpGet post = new HttpGet(url);
        addHeaders(headers, post);
        return connect(post);
    }

    public static HttpResponse post(String url, String content, String contentType, Map<String, String> headers) throws Exception
    {
        HttpPost post = new HttpPost(url);
        try
        {
            post.setEntity(new StringEntity(content));
            post.setHeader("Content-type", contentType);

        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("unsupported charset", e);
        }

        addHeaders(headers, post);

        return connect(post);
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
        HttpClient client = HttpClients.createDefault();
        try
        {
            response = client.execute(method);
        }
        finally
        {
            method.releaseConnection();
        }
        return response;
    }

}

