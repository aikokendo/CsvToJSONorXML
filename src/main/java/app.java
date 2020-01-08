import parser.*;

public class app {
    public static void main(String[] args){

        if (args.length > 1) {   //parameters provided

            Parser myParser = null;

            if (args[1].equals("JSON")){
                myParser = new parserJSON();
            }
            else if(args[1].equals("XML")){
                myParser = new parserXML();
            }
            else if(args[1].equals("INSERT")){
                myParser = new parserINSERT();
            }
            else{
                myParser = new parserPIPE();
            }

            System.out.println(myParser.parseFromCsvFile(args[0]));
        }

    }


}
