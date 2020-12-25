

##Task 1: Pure Java
Having a Player class - an instance of this class with that can communicate with other Player(s) (other instances of this class)
The use case for this task is as below:
1. create 2 players
2. one of the players should send a message to second player (let's call this player "initiator")
3. when a player receives a message should send back a new message that contains the received message concatenated with the
message counter that this player sent.
4. finalize the program (gracefully) after the initiator sent 10 messages and received back 10 messages (stop condition)

6. document for every class the responsibilities it has.

7. additional challenge (nice to have) opposite to 5: have every player in a separate JAVA process.

## Dev Considerations
Item 5 is not applicable as it is the oposite of the item 7 and this project solve the problem using the item 7 approach

## run

To run the we need to run 3 script, one for building and starting the server and the others for run the client and connect to the server, so the server script shall be ran first.
	
	sh ./run-server-socket.sh
	sh ./run-client-player1.sh
	sh ./run-client-player2.sh
	
it will build and execute the program.

Note: it was tested using linux ubuntu terminal, and windows 10 PRO using git bash(git terminal)	
