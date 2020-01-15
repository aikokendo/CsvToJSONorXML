package aleUsers.service;

import org.springframework.stereotype.Component;

@Component
public class parserINSERT implements Parser {

    @Override
    public String parseFromCsv(String csvContent) {
        String[] csvFileLines = csvContent.split("\n");
        String output = "";
        String[] headers = null;
        String[] curRow = null;
        String curInsert = "";
        if (csvFileLines.length > 1){  //there is at least 1 row available
            headers = csvFileLines[0].split(",");
        }
        for (int i = 1; i<csvFileLines.length; i++){
            curRow = csvFileLines[i].split(",");
            curInsert = "INSERT INTO TABLE (";
            for (int j = 0; j < headers.length; j++){
                curInsert += headers[j] + ",";
            }
            curInsert = curInsert.substring(0, curInsert.length() - 1) + ") VALUES ("; //delete last comma

            for (int j = 0; j < curRow.length; j++){
                curInsert += curRow[j] + ",";
            }
            curInsert = curInsert.substring(0, curInsert.length() - 1) + ");"; //delete last comma
            output += curInsert  + "\n";
        }
        return output;
    }
}