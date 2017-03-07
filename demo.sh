#!/bin/bash
mvn clean install > compile.log

java -jar target/game2048* 

