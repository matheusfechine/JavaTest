# JavaTest

This project solves a Wallethub challenge.

# Questions above:

Deliverables
------------

(1) Java program that can be run from command line
	
    java -cp "parser.jar" com.ef.Parser --accesslog=/path/to/file --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100

**NOTE**
I am assuming to pass entire path of the file in --accesslog parameter. EX: *--accesslog=/path/to/file/access.log* 
     
*You can find the jar file located at root of the project*

(2) Source Code for the Java program

*You can find here! :)*

(3) MySQL schema used for the log data

*You can find schema file located at root of the project. **javatest.sql** *

(4) SQL queries for SQL test
*You can find the main query used located at root of the project **queries.sql**.*

# Running

## Eclipse, IntelliJ or any other IDE.

Import maven project, or simply run

*mvn clean install -U*

## Considerations.

You can find all Unit Tests at *src/test/java/com/ef/unit* folder.

Must be compiled in Java 8.
