#!/bin/bash
echo "Compiling : see output in compile.log"
mvn clean install > compile.log
echo "Done - starting autoplay mode"
java -jar target/game2048* 

