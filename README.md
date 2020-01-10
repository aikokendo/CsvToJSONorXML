# CsvToJSONorXML
Simple java program that receives a CSV file and converts it to JSON, XML, Insert statements

## Execution
Program expects two parameters: CSV file address (test.csv) and either "JSON", "XML" or "INSERT". 
A sample test file has been provided "test.csv" 


## Sample Response
**parser.app _test.csv_ _JSON_**

```
[{"name":"ale","city":"reston","id":"1"},
{"name":"nakano","city":"reston","id":"2"},
{"name":"john","city":"fairfax","id":"3"}]

```

**parser.app _test.csv_ _XML_**

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

**parser.app _test.csv_ _INSERT_**
```
INSERT INTO test (id,name,city) VALUES (1,ale,reston);
INSERT INTO test (id,name,city) VALUES (2,nakano,reston);
INSERT INTO test (id,name,city) VALUES (3,john,fairfax);

```

** parser.app _test.csv_ _PIPE_ **

```
id|name|city
1|ale|reston
2|nakano|reston
3|john|fairfax
```
