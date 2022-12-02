# tiny1
A lightweight easy-to-use http-server for static content.

## Features
* Serves at breakneck speed
* Binary size under 50kb
* Multi-platform (Windows, Linux, MacOS etc.)
* TLS support 
* Build-in MIME types for 900+ extensions

## Supported methods
GET and HEAD

## HTTP implementation
HTTP/1.0

## Security
Tiny1 is a one-man exercise project, therefore unsuitable for 
production. 
That being said, security should always be taken seriously, therefore
Tiny1 is protected against common attacking methods like:

* Path Traversal
* Buffer Overflow
* Denial Of Service

Further, logging is secure with Java's good old println, and
an extra layer of security is provided naturally by the JVM.
An addition to that, tiny1 has undergone extensive stress-
and pen-testing.

## Requirements
* Java 11

## Build and Run
To spin up an instance of tiny1 with its defaults, follow these 5 steps:

1. Clone:

`git clone https://github.com/stefanoskapa/tiny1.git`

2. Move to /tiny1

`cd tiny1`

3. Build the jar:

`./gradlew build`

4. Move to /build/libs 

`cd /build/libs`

5. Start the server!

`java -jar tiny1.jar` 





