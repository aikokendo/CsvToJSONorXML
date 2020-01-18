package aleUsers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import aleUsers.errorHandling.badRequestException;
import aleUsers.service.StrategyFinder;

import java.util.Map;

@RestController
@RequestMapping("/parser")
public class parserController {
    @Autowired
    private StrategyFinder myStrategy;

    @GetMapping()
    public String getParser(@RequestParam(required = false) Map<String,String> query, @RequestBody(required = false) String csvText) throws Exception {
        if (csvText == null || !query.containsKey("type")){
//            return "text error error";
            throw new badRequestException("A type must be provided.");
        }
        else{
            return myStrategy.parseFromCsv(csvText, query.get("type"));
        }
    }
}
