package parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parser.service.StrategyFinder;

import java.util.Map;

@RestController
public class parserController {
    @Autowired
    private StrategyFinder myStrategy;

    @GetMapping()
    public String getParser(@RequestParam Map<String,String> query, @RequestBody String csvText){
        return myStrategy.parseFromCsv(csvText,query.get("type"));
    }
}
