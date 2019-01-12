# APIs for StlAyyappaSannidhanam
This project runs on Spring Boot

## Database setup
Use Flyway to initialize the DB and maintain DDLs

Set the below environment variables on your local system -

`FLYWAY_URL` = database url in the format `jdbc:postgresql://host:port/database`  
`FLYWAY_USER` = username  
`FLYWAY_PASSWORD` = password  
`FLYWAY_SCHEMAS` = postgres

For initial setup, run `mvn flyway:migrate`

For every DDL add SQL file under src\main\resources\db\migrations\<Vnumber>__\<desription>.sql
and then `mvn flyway:migrate`
