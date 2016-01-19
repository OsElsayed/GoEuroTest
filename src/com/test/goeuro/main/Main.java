/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.goeuro.main;

import com.google.gson.JsonArray;
import com.test.goeuro.connectionUtils.ConnectionManager;
import com.test.goeuro.constants.Constants;
import com.test.goeuro.csvGenerator.CSVFileGenerator;
import com.test.goeuro.jsonParser.JsonParserUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 *
 * @author osama.mabrouk
 */
public class Main {

    /**
     *
     * @param args
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("System starts..");
        try {
            String cityNameParam = args[0];
            if (cityNameParam != null && !cityNameParam.trim().equals("")) {
                StringBuilder url = new StringBuilder(Constants.URL);
                url.append(cityNameParam);
                ConnectionManager connectionManager = new ConnectionManager();
                HttpURLConnection request = connectionManager.openUrlStream(url.toString());
                JsonParserUtil parserUtil = new JsonParserUtil();
                JsonArray respondArray = parserUtil.convertToJSON(request);
                if (respondArray.size() > 0) {
                    System.out.println("Query returned successfully with data");
                    CSVFileGenerator cSVFileGenerator = new CSVFileGenerator();
                    cSVFileGenerator.createCSVFileAndWriteData(respondArray);
                } else {
                    System.out.println("No city name matches are found, An empty JSON array is returned.");
                }
                System.out.println("System ends.");
            } else {
                System.out.println("Invalid city name, Please provide one.");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Invalid city name, Please provide one.");
        }
    }
}