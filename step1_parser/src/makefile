CP=.:../lib/*
INPUT=gameStateData-JSON-10000.txt

all:
	javac -classpath ${CP} BWAPIParser.java
	java -classpath ${CP} -Dlog4j.configuration=jena-log4j.properties BWAPIParser ${INPUT}
	
