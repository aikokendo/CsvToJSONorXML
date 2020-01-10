import parser.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class app {
    public static void main(String[] args){

        if (args.length > 1) {   //parameters provided
            StrategyFinder mySF = new StrategyFinder(args[1]);

            String contents = "";
            String csvFileName = "test.csv";
            try {
                contents = Files.lines(Paths.get(csvFileName)).collect(Collectors.joining("\n"));
            }
            catch(IOException e){
                e.printStackTrace();
            }
            System.out.println(mySF.parseFromCsv(contents));
//            System.out.println(mySF.parseFromCsv(args[0]));
        }

    }


}
