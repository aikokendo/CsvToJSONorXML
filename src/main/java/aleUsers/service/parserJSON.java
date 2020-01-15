package aleUsers.service;


import org.json.CDL;
import org.springframework.stereotype.Component;


@Component
public class parserJSON implements Parser {
    @Override
    public String parseFromCsv(String csvContent) {
        return CDL.toJSONArray(csvContent).toString();
    }
}
