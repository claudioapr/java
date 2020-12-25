#!/bin/sh
###############################
##Claudio Resende 30/07/2020###
###############################

# building the projects
mvn clean install

# running the server socket
java -jar player-chat-server\\target\\player-chat-server.jar

$SHELL	