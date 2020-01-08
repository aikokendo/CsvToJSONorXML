package parser;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class parserPIPE implements Parser {

    @Override
    public String parseFromCsvFile(String csvFileName) {
        CSVReader reader = null;
        String output = "";

        try {
            reader = new CSVReader(new FileReader(csvFileName));  //CSVReader manages default comma separated values behaviours.
            String[] line;

            while ((line = reader.readNext()) != null) {
                String result = "";
                for (int i = 0; i < line.length; i++) {
                    result += line[i] + "|";  //add all values
                }
                result = result.substring(0, result.length() - 1); //delete last pipe
                output += result + "\n";
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}