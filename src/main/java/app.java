import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import org.json.CDL;
import org.json.JSONArray;
import org.json.XML;

public class app {
    public static void main(String[] args){
        JSONArray itemData = new JSONArray();

        if (args.length > 1) {   //parameters provided
            String csvFileName = args[0];
            String csvJustName = csvFileName.split("\\.")[0];
            CSVReader reader = null;

            if (args[1].equals("JSON")) {
                System.out.println(getJsonArray(csvFileName));
            }
            else if(args[1].equals("XML")) {
                String XMLresult = "<"+ csvJustName+">" + XML.toString(getJsonArray(csvFileName)) + "</"+csvJustName+">";  //added root for XML
                System.out.println(XMLresult);
            }
            else {
                try {
                    reader = new CSVReader(new FileReader(csvFileName));  //CSVReader manages default comma separated values behaviours.
                    String[] headers = reader.readNext(); //headers
                    String[] line;

                    while ((line = reader.readNext()) != null) {
                        String result = "INSERT INTO " + csvJustName + " (";
                        for (int i = 0; i < headers.length; i++){
                            result += headers[i] + ",";
                        }
                        result = result.substring(0, result.length() - 1) + ") VALUES ("; //delete last comma

                        for (int i = 0; i < line.length; i++) {
                            result +=  line[i] + ",";  //add all values
                        }
                        result = result.substring(0, result.length() - 1) + ");"; //delete last comma
                        System.out.println(result);
                    }
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static JSONArray getJsonArray(String csvFileName){
        String contents = "";
        try {
            contents = Files.lines(Paths.get(csvFileName)).collect(Collectors.joining("\n"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return CDL.toJSONArray(contents);
    }



}
