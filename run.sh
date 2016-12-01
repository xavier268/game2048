#!/bin/bash
mvn clean install
date >> report.txt
java -jar target/game2048* | tee -a report.txt
date >> report.txt
