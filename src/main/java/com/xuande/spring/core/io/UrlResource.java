package com.xuande.spring.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author : xuande
 * @date : 2022-02-19 14:12
 **/
public class UrlResource implements Resource{

    private URL url;

    public UrlResource(URL url) {
        Assert.notNull(url, "url must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection connection = this.url.openConnection();
        try {
            return connection.getInputStream();
        }catch (IOException e){
            if (connection instanceof HttpURLConnection){
                ((HttpURLConnection) connection).disconnect();
            }
            throw e;
        }
    }
}
