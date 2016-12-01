# Play the 2048 game on the console.

Attempt to have the computer beat my personnal "manual" record on a regular basis ...

## How to use

* compile with : mvn install
* run with MainHumanPlay class to play with human.
* run with MainCompareStrategies (default) to compare various stratégies.

`java -jar game2048-1.0-SNAPSHOT.jar`  (default to 4 x 4 board) or 
`java -jar game2048-1.0-SNAPSHOT.jar  6` ( use a 6 x 6 board) 

**Best strategy so far is LTExp using Random with small number of random tries.**


## Performance

Personal manual record playing on a 4x4 board was around 30 000.

Latest strategies have an average score on 4x4 around 13-15 000 on average, 
with peak record at 36 000 after 60 games.