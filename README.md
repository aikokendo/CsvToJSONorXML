# CsvToJSONorXML
Simple java program that receives a CSV file and converts it to JSON or XML

## Execution
Program expects two parameters: CSV file address (test.csv) and either "JSON" or "XML". 
A sample test file has been provided "test.csv" 


## Sample Response
**app _test.csv_ _JSON_**

```
[{"name":"ale","city":"reston","id":"1"},
{"name":"nakano","city":"reston","id":"2"},
{"name":"john","city":"fairfax","id":"3"}]

```

**app _test.csv_ _XML_**

```
<result>
	<array>
		<name>ale</name>
		<city>reston</city>
		<id>1</id>
	</array>
	<array>
		<name>nakano</name>
		<city>reston</city>
		<id>2</id>
	</array>
	<array>
		<name>john</name>
		<city>fairfax</city>
		<id>3</id>
	</array>
</result>

```
