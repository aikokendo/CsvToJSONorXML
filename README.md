# CsvToJSONorXML
Simple java spring program that can store users in a POSTGRES DB
It can serve the user information in special parsing JSON, XML, Insert and PIPE.

# POSTGRES Container
in docker pull the postgres image:
```
docker pull postgres
```

and start a postgres instance:
```
docker run -d -p 5432:5432 --name alePos -e POSTGRES_PASSWORD=somePass postgres
```

in case of needing a more complex container with docker-compose, please refer to https://hub.docker.com/_/postgres/



## Endpoints
### USER
{"firstName":"Ale","lastName":"Meraz"}

+ **POST** `localhost:8080/users`  - Returns the ID of the newly created user
+ **GET**  `localhost:8080/users/{id}` - Returns User information for the id provided. Accepts the Header "Accept=application/json" and "Accept=application/xml"
+ **GET**  `localhost:8080/users`      - Returns all Users information. Accepts the Header "Accept=application/json" and "Accept=application/xml"
+ **GET**  `localhost:8080/users/parse/{id}?type={type}` - Returns User information for the id provided. Accepts the types: JSON, XML, INSERT, PIPE
+ **GET**  `localhost:8080/users/parse?type={type}`      - Returns all Users information. Accepts the types: JSON, XML, INSERT, PIPE


## PARSER
+ **GET** `localhost:8080/parser?type={type}`   - returns the CSV provided in the body parsed into type.  Accepts the types: JSON, XML, INSERT, PIPE

Example:

JSON
```
curl --location --request GET 'localhost:8080?type=JSON' \
--header 'Content-Type: text/plain' \
--data-raw 'id,name
1,ale
2,fernando'
```

Response
```
[{"name":"ale","id":"1"},{"name":"fernando","id":"2"}]
```
