/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.goeuro.jsonParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.test.goeuro.csvGenerator.CSVFileGenerator;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author osama.mabrouk
 */
public class JsonParserUtil {

    /**
     *
     * @param request
     */
    public JsonArray convertToJSON(HttpURLConnection request) throws IOException{
        InputStreamReader inputStreamReader = null;
        JsonArray respondArray = null;
        try {
            JsonParser jsonParser = new JsonParser();
            inputStreamReader = new InputStreamReader((InputStream) request.getContent());
            JsonElement root = jsonParser.parse(inputStreamReader);
            respondArray = root.getAsJsonArray();
        } catch (IOException ioex) {
            Logger.getLogger(JsonParserUtil.class.getName()).log(Level.SEVERE, null, ioex);
            System.out.println("Error while convert to JSON object : " + ioex.getMessage());
            ioex.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
            } catch (IOException ioex) {
                Logger.getLogger(JsonParserUtil.class.getName()).log(Level.SEVERE, null, ioex);
                System.out.println("Error while closing input stream " + ioex.getMessage());
                ioex.printStackTrace();
            }
        }
        return respondArray;
    }

}
