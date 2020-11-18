# KB ADAA Event API example

#### Links
* [KB API Business portal](https://www.kb.cz/api)
* [KB API Developer portal](https://api.kb.cz/open/apim/store)

---

This Java web application serves as a reference example, or an inspiration for developers who want to develop software based on the KB ADAA Event API. 
This example is based on the Spring Boot framework.

#### How to run
1. Complete application properties located in the `./server/src/main/resources/application.yml` file

2. You should build this project with Maven
    ```
    mvn clean install
    ```
    and then run the embedded Tomcat server using command in web maven module:
    ```
    mvn spring-boot:run
    ```
3. Or you can deploy a built `war` file to your own instance of the application server

#### Description
The ADAA Event API must be subscribed to the KB ADAA API.
The ADAA Event API then receives a notification after each transaction, but no more than once every 5 minutes.

---

*If you still have any questions please contact a [KB API support team](mailto:api@kb.cz).*


