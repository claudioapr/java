
##Task 1: Pure Java
Having a Player class - an instance of this class with that can communicate with other Player(s) (other instances of this class)
The use case for this task is as below:
1. create 2 players
2. one of the players should send a message to second player (let's call this player "initiator")
3. when a player receives a message should send back a new message that contains the received message concatenated with the
message counter that this player sent.
4. finalize the program (gracefully) after the initiator sent 10 messages and received back 10 messages (stop condition)
5. both players should run in the same java process (strong requirement)
6. document for every class the responsibilities it has.

## Dev Considerations
Item 7 is not applicable as it is the opposite of the item 5 and it was solved in different project 

As the task is simple and require less elaborated effort and the item 7 requires a bit more elaboration the item 7 was done in different project using socket to comunicate @see project player-chat-socket

## run

To run the application just execute the sh script 
	
	sh ./run.sh
	
it will build and execute the program.

Note: it was tested using linux ubuntu terminal, and windows 10 PRO using git bash(git terminal)	
