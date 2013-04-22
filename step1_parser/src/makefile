CP=.:../lib/*
INPUT=gameStateData-JSON-10000.txt

all:
	javac -classpath ${CP} BWAPIParser.java
	java -classpath ${CP} BWAPIParser ${INPUT}
	
