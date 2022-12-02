# tiny1
A lightweight easy-to-use http-server for static content.

## Features
* Serves at breakneck speed
* File size under 100kb
* Multi-platform (Windows, Linux, MacOS etc.)
* TLS support 
* Extensive list of build-in MIME types
* Scalable (hmm..)

## Supported methods
GET and HEAD

## HTTP implementation
HTTP/1.0

## Security
Tiny1 is a one-man show, therefore unsuitable for 
a high-stakes production environment.  
That said, security has been taken seriously.

* Immune against Path Traversal attacks
* Immune against Buffer Overflow attacks
* Secure logging with good old System.out.println()
* Extra layer of security provided by the JVM
* Extensive penetration- & stress testing

## Requirements
* Java 11

## Build and Run
Clone from main and execute

`./gradlew build`

Then run the resulting .jar with

`java -jar tiny1.jar`





