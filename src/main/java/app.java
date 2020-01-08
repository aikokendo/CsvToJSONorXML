import parser.*;

public class app {
    public static void main(String[] args){

        if (args.length > 1) {   //parameters provided
            StrategyFinder mySF = new StrategyFinder(args[1]);
            System.out.println(mySF.parseFromCsvFile(args[0]));
        }

    }


}
