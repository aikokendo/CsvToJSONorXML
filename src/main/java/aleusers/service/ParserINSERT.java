package aleusers.service;

import org.springframework.stereotype.Component;

@Component
public class ParserINSERT implements Parser {

    @Override
    public String parseFromCsv(String csvContent) {
        String[] csvFileLines = csvContent.split("\n");
        String[] headers = new String[1];
        String[] curRow;
        StringBuilder bld = new StringBuilder();
        StringBuilder outputBld = new StringBuilder();
        if (csvFileLines.length > 1){  //there is at least 1 row available
            headers = csvFileLines[0].split(",");
        }
        for (int i = 1; i<csvFileLines.length; i++){
            curRow = csvFileLines[i].split(",");
            bld.append("INSERT INTO TABLE (");
            for (int j = 0; j < headers.length; j++){
                bld.append(headers[j]);
                bld.append(",");
            }
            bld.deleteCharAt(bld.length()-1); //delete last comma
            bld.append(") VALUES (");

            for (int j = 0; j < curRow.length; j++){
                bld.append(curRow[j]);
                bld.append(",");
            }
            bld.deleteCharAt(bld.length()-1); //delete last comma
            bld.append(");");
            outputBld.append(bld.toString());
            bld.setLength(0);
            outputBld.append("\n");
        }
        return outputBld.toString();
    }
}