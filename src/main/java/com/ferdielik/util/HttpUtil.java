package com.ferdielik.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.Map;


public final class HttpUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final int DEFAULT_TIMEOUT = 0;

    public static String get(String url, Map<String, String> headers) throws Exception {
        HttpGet post = new HttpGet(url);
        addHeaders(headers, post);
        HttpResponse connect = connect(post);

        return getStringFromInputStream(connect.getEntity().getContent());
    }

    public static HttpResponse post(String url, String content, String contentType, Map<String, String> headers) throws Exception {
        HttpPost post = new HttpPost(url);
        try {
            post.setEntity(new StringEntity(content));
            post.setHeader("Content-type", contentType);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("unsupported charset", e);
        }

        addHeaders(headers, post);

        return connect(post);
    }

    private static void addHeaders(Map<String, String> headers, HttpRequestBase method) {
        if (null != headers) {
            for (String header : headers.keySet()) {
                method.setHeader(header, headers.get(header));
            }
        }
    }

    private static HttpResponse connect(HttpRequestBase method) throws Exception {
        HttpResponse response;
        HttpClient client = HttpClients.createDefault();
        try {
            response = client.execute(method);
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}

