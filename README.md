# One Force API

Building Application
---
1. In Terminal run `export API_URL="http://localhost:8080/"`
2. Run `mvn clean install` to build your application
3. Add Database properties, open terminal and enter each point below with the correct information (examples below)
- Enter the host `export DB_HOST="EXAMPLE"`;
- Enter the Database name `export DB_NAME="EXAMPLE"`
- Enter your Database Username `export DB_USERNAME="EXAMPLE"`
- Enter your Database Password `export DB_PASSWORD="EXAMPLE"`
- Enter Admin Username `export ADMIN_USERNAME="EXAMPLE`
- Enter Admin Password `export ADMIN_PASSWORD="EXAMPLE`

- Note: Your API will be running on the URL `http://localhost:8080` which is your base URL for the API

How to start the application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/Team1_Backend-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080` + `endpoint_URL (e.g. /api/job-roles)`
- Note: if you haven't just a `/` endpoint then you won't get anything when loading base URL localhost you may need to look at swagger documentation for an endpoint to visit.

How to use swagger
---
1. Start Application
2. Go to you `API_URL/swagger` and for local it would be `http://localhost:8080/swagger`

Health Check
---

1. To see your applications health enter url `http://localhost:8081/healthcheck` while the application is running

How to Run Testing
---
1. To run unit tests use `mvn test -DtestGroup=unit`
2. To run Integration tests `mvn test -DtestGroup=integration`

Install JHusky
---

1. To install JHusky use `mvn jhusky:install -Ddirectory=.husky`

 