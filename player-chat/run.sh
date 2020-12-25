#!/bin/sh
###############################
##Claudio Resende 30/07/2020###
###############################
mvn clean install

java -jar target\\player-chat.jar
$SHELL	