# tiny1
A lightweight easy-to-use http-server for static content.

## Features
* Serves at breakneck speed
* Binary size under 50kb
* Multi-platform (Windows, Linux, MacOS etc.)
* TLS support 
* Build-in MIME types for 900+ extensions
* 301 redirects
* HEAD method

## Supported methods
GET and HEAD

## HTTP implementation
HTTP/1.0

## Security
Tiny1 was conceived as an exercise project, therefore unsuitable for 
production. 
That being said, security should always be taken seriously, therefore
Tiny1 provides basic security against common attacking methods like:

* Path Traversal
* Buffer Overflow
* Denial Of Service

Further, logging is secure (good old println), and
an extra layer of security is provided naturally by the JVM.
In addition, tiny1 has undergone extensive stress- and pen-testing
by the author.

## Requirements
* Java 11

## Build and Run
To spin up an instance of tiny1 with its defaults, follow these 5 simple steps:

1. Clone the project:

`git clone https://github.com/stefanoskapa/tiny1.git`

2. Move to the tiny1 directory

`cd tiny1`

3. Build the jar:

`gradlew build`

4. Move to the build directory 

`cd /build/libs`

5. Start the server!

`java -jar tiny1.jar` 





