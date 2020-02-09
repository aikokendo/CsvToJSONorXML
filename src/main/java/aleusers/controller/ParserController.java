package aleusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import aleusers.errorhandling.BadRequestException;
import aleusers.service.StrategyFinder;

import java.util.Map;

@RestController
@RequestMapping("/parser")
public class ParserController {
    @Autowired
    private StrategyFinder myStrategy;

    @PostMapping()
    public String getParser(@RequestParam(required = false) Map<String,String> query, @RequestBody(required = false) String csvText){
        if (csvText == null || !query.containsKey("type")){
            throw new BadRequestException("A type must be provided.");
        }
        else{
            return myStrategy.parseFromCsv(csvText, query.get("type"));
        }
    }
}
