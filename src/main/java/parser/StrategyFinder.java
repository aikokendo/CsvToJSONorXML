package parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StrategyFinder {
    private Parser myParser;

    @Autowired
    private parserJSON myParserJSON;

    @Autowired
    private parserXML myParserXML;

    @Autowired
    private parserINSERT myParserINSERT;

    @Autowired
    private parserPIPE myParserPIPE;

    @GetMapping()
    public String parseFromCsv(@RequestParam Map<String,String> query, @RequestBody String csvText){
        String output = query.get("type");
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
