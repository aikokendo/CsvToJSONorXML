package parser;

public class StrategyFinder {
    private Parser myParser;

    public StrategyFinder(String output){
        if (output.equals("JSON")){
            this.myParser = new parserJSON();
        }
        else if(output.equals("XML")){
            this.myParser = new parserXML();
        }
        else if(output.equals("INSERT")){
            this.myParser = new parserINSERT();
        }
        else{
            this.myParser = new parserPIPE();
        }

    }

    public String parseFromCsv(String csvContent){
        return myParser.parseFromCsv(csvContent);
    }
}
