/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.goeuro.csvGenerator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.test.goeuro.constants.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 *
 * @author osama.mabrouk
 */
public class CSVFileGenerator {

    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final Object[] FILE_HEADER = {"ID", "NAME", "TYPE", "LATITUDE", "LONGITUDE"};

    /**
     *
     * @param respondArray
     */
    public void createCSVFileAndWriteData(JsonArray respondArray) throws FileNotFoundException, IOException {

        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.EXCEL.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            System.out.println("generating CVS file ....");
            fileWriter = new FileWriter(new File(Constants.FILE_NAME));
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            csvFilePrinter.printRecord(FILE_HEADER);

            for (JsonElement jsonElement : respondArray) {
                List apiRespondData = new ArrayList();
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                apiRespondData.add(jsonObject.get(Constants._ID).getAsString());
                apiRespondData.add(jsonObject.get(Constants.NAME).getAsString());
                apiRespondData.add(jsonObject.get(Constants.TYPE).getAsString());
                JsonObject inGeoPosObject = jsonObject.getAsJsonObject(Constants.GEO_POSITION);
                apiRespondData.add(inGeoPosObject.get(Constants.LATITUDE).getAsDouble());
                apiRespondData.add(inGeoPosObject.get(Constants.LONGITUDE).getAsDouble());
                csvFilePrinter.printRecord(apiRespondData);
            }

            System.out.println("CSV generated successfully");
        } catch (FileNotFoundException fnfex) {
            Logger.getLogger(CSVFileGenerator.class.getName()).log(Level.SEVERE, null, fnfex);
            System.out.println("Error in Open csv file");
            fnfex.printStackTrace();
        } catch (IOException ioex) {
            Logger.getLogger(CSVFileGenerator.class.getName()).log(Level.SEVERE, null, ioex);
            System.out.println("Error in Open/Write CsvFileWriter!!!");
            ioex.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            } catch (Exception ex) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                ex.printStackTrace();
            }
        }
    }
}
