# CsvToJSONorXML
Simple java spring program that receives a CSV content and converts it to JSON, XML, Insert or PIPE statements

## Execution
Program expects two parameters: CSV file address (test.csv) and either "JSON", "XML", "INSERT" or "PIPE". 

## Sample Response
### JSON
```
curl --location --request GET 'localhost:8080?type=JSON' \
--header 'Content-Type: text/plain' \
--data-raw 'id,name
1,ale
2,fernando'

```
### Response
```
[{"name":"ale","id":"1"},{"name":"fernando","id":"2"}]
```
