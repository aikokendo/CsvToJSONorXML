package aleUsers.service;

import org.springframework.stereotype.Component;

@Component
public class ParserPIPE implements Parser {

    @Override
    public String parseFromCsv(String csvContent) {
        String[] csvFileLines = csvContent.split("\n");
        String output = "";
        String[] curRow = null;
        String curPipe = "";
        for (int i = 0; i<csvFileLines.length; i++){
            curRow = csvFileLines[i].split(",");
            curPipe = "";
            for (int j = 0; j < curRow.length; j++){
                curPipe += curRow[j] + "|";
            }
            curPipe = curPipe.substring(0, curPipe.length() - 1) ; //delete last Pipe

            output += curPipe  + "\n";
        }
        return output;
    }
}