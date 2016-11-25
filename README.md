# Play the 2048 game on the console.

## How to use

* compile with : mvn install
* run with : 

`java -jar game2048-1.0-SNAPSHOT.jar`  (default to 4 x 4 board) or 
`java -jar game2048-1.0-SNAPSHOT.jar  6` ( use a 6 x 6 board) 


## What's implemented so far

* Board/BoardImpl object that allow to play a move (up, dow, left or right), compute 
allowed moves, manages board updates, compute  scores

* a simple commandline interface to play with the keyboard

## Ideas, todos for the mid/longuer term :

* implement a nicer javaFx based GUI
* allow simultaneous play against the machine with the same both random seeds
* apply machine learning techniques to optimize automatic play ( question : 
how to generate feature vectors and the associated labe, ie the move to perform, 
so that the machine can 'learn')