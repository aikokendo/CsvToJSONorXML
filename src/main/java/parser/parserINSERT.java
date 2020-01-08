package parser;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class parserINSERT implements Parser {

    @Override
    public String parseFromCsvFile(String csvFileName) {
        String csvJustName = csvFileName.split("\\.")[0];
        CSVReader reader = null;
        String output = "";

        try {
            reader = new CSVReader(new FileReader(csvFileName));  //CSVReader manages default comma separated values behaviours.
            String[] headers = reader.readNext(); //headers
            String[] line;

            while ((line = reader.readNext()) != null) {
                String result = "INSERT INTO " + csvJustName + " (";
                for (int i = 0; i < headers.length; i++) {
                    result += headers[i] + ",";
                }
                result = result.substring(0, result.length() - 1) + ") VALUES ("; //delete last comma

                for (int i = 0; i < line.length; i++) {
                    result += line[i] + ",";  //add all values
                }
                result = result.substring(0, result.length() - 1) + ");"; //delete last comma
                output += result + "\n";
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}