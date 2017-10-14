# hioa-library-app
This is a simple application that I use for teaching SOAP / REST / REST-Hateoas webservices at HiOA

It implements the standard spring controller / service / repository layers to show how to interact with 
a webservice. SOAP, REST and REST-Hateaos  endpoints/controllers are implemented.

The difference between REST and REST-Hateoas is that the REST endpoint is only up to level 2 in Fieldings stack, while
REST-Hateaos is level 3. We make the distinction as our impression is that a lot of software is implemented to level 2
and it makes an interesting teaching / discussion point.

It uses H2 as a in-memory db and inserts some data on startup so it should be possible to play with this 
"out-of-the-box" without having to install anything but Java 1.8 and maven.

There is no security on this application as we want to focus on the message exchange formats (XML, JSON, SOAP) and the
different ways of interacting with a webservice.

Once you do a git clone, cd into the project directory containing pom.xml. From there it should be possible to just run
 
  mvn spring-boot:run

SOAP endpoint:
Use the WSDL file as a starting point for interacting with the endpoint. It has been tested with SOAPUI
  
  http://localhost:9984/library/soap/library.wsdl

REST Controller:
You will find a list of authors and books by issuing the following:

  http://localhost:9984/library/rest/authors
  http://localhost:9984/library/rest/books
  
You have the standard CRUD options available to you. You can either use SOAPUI or e.g Advanced REST client in a browser
 to interact with REST-service.

A WADL-file is located at the following address  
  http://localhost:9984/library/rest/library.wadl

A swagger browsable description is available from

REST-Hateaos Controller:
The starting point for the application is

  http://localhost:9984/library/rest-hateoas


Future work includes adding JWT support to show security and to get the authors/books entities embedded within 
each other for the REST approaches. 


If you run this not on localhost, then you have to change the following files:

  * library.wsdl change <soap:address location="http://localhost:9984/library/soap/service"/>
  * application.properties change hateoas.publicAddress=http://localhost:9984
