package aleUsers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyFinder {
    private Parser myParser;

    @Autowired
    private ParserJSON myParserJSON;
    @Autowired
    private ParserXML myParserXML;
    @Autowired
    private ParserINSERT myParserINSERT;
    @Autowired
    private ParserPIPE myParserPIPE;

    public String parseFromCsv(String csvText, String output){
        if (output.equals("JSON")){
            this.myParser = myParserJSON;
        }
        else if(output.equals("XML")){
            this.myParser = myParserXML;
        }
        else if(output.equals("INSERT")){
            this.myParser = myParserINSERT;
        }
        else{
            this.myParser = myParserPIPE;
        }
        return myParser.parseFromCsv(csvText);

    }
}
