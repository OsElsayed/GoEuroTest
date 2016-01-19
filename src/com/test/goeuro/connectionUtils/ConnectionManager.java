/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.goeuro.connectionUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author osama.mabrouk
 */
public class ConnectionManager {

    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public HttpURLConnection openUrlStream(String url) throws IOException {

        HttpURLConnection request = null;
        try {
            URL urlObject = new URL(url);
            request = (HttpURLConnection) urlObject.openConnection();
            request.connect();
            System.out.println("connected successfully to URL :" + url);
            System.out.println("Getting info from URL :" + url);
        } catch (IOException ioex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ioex);
            System.out.println("Error while open URL : " + ioex.getMessage());
            ioex.printStackTrace();
        }
        return request;
    }

}
