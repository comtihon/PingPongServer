#!/bin/bash
redis-server &
mvn clean;
mvn package;
cp -r conf target;
java -jar target/Ping-1.0-0.jar