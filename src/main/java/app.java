import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;
import org.json.JSONArray;
import org.json.XML;

public class app {
    public static void main(String[] args){
        JSONArray itemData = new JSONArray();
        if (args.length > 1) {   //parameters provided
            String csvFileName = args[0];
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(csvFileName));  //CSVReader manages default comma separated values behaviours.
                String[] headers = reader.readNext(); //headers
                String[] line;
                while ((line = reader.readNext()) != null){
                    Map<String, String> lineSelected = new HashMap();
                    for (int i = 0; i<line.length; i++){
                        lineSelected.put(headers[i],line[i]);  //add all row elements into map
                    }
                    itemData.put(lineSelected);  //add map to json result
                }
                if (args[1].equals("JSON")) {
                    System.out.println(itemData);
                }
                else {
                    String XMLresult = "<result>" + XML.toString(itemData) + "</result>";  //added root for XML
                    System.out.println(XMLresult);
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }


        }

    }



}
