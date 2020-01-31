package aleusers.service;

import org.springframework.stereotype.Component;

@Component
public class ParserPIPE implements Parser {

    @Override
    public String parseFromCsv(String csvContent) {
        String[] csvFileLines = csvContent.split("\n");
        String[] curRow;

        StringBuilder bld = new StringBuilder();
        StringBuilder outputBld = new StringBuilder();

        for (int i = 0; i<csvFileLines.length; i++){
            curRow = csvFileLines[i].split(",");
            for (int j = 0; j < curRow.length; j++){
                bld.append(curRow[j]);
                bld.append("|");
            }
            bld.deleteCharAt(bld.length()-1); //delete last pipe
            outputBld.append(bld.toString());
            bld.setLength(0);
            outputBld.append("\n");
        }

        outputBld.deleteCharAt(outputBld.length()-1); //delete last skip of line
        return outputBld.toString();
    }
}