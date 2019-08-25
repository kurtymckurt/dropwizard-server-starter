# dropwizard-server-starter
This project is a proof of concept starter for drop wizard.  The purpose is to have an out of the box running server with
minimal configuration.  The out of the box configuration comes with:


* A Display Banner
* Hibernate
  * H2 (not meant for production)
* DB Migrations
  * Simple XML db migration support 
    * See the src/main/java/resources/migrations.xml
* Basic HTTP connection (8080 without SSL)
  * See dropwizard configuration for more about SSL
* Health Checks
  * Basic health checks that need to be filled in
* Resource
  * Simple resources provided
* Lombok
  * Boiler plate generation (See https://projectlombok.org/)
* Dagger
  * Simple compile time DI built for speed
* Model Mapper
  * Simple way to map DTOs to Models and vice versa
