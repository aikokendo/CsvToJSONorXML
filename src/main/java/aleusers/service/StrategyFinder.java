package aleusers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyFinder {
    @Autowired
    private ParserJSON myParserJSON;
    @Autowired
    private ParserXML myParserXML;
    @Autowired
    private ParserINSERT myParserINSERT;
    @Autowired
    private ParserPIPE myParserPIPE;

    public String parseFromCsv(String csvText, String output){
        Parser myParser;
        if (output.equals("JSON")){
            myParser = myParserJSON;
        }
        else if(output.equals("XML")){
            myParser = myParserXML;
        }
        else if(output.equals("INSERT")){
            myParser = myParserINSERT;
        }
        else{
            myParser = myParserPIPE;
        }
        return myParser.parseFromCsv(csvText);

    }
}
