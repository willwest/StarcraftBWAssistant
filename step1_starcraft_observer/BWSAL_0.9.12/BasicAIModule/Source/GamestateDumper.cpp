// This class encapsulates the functionalities needed to capture game state data in the form
// of an ontology and output the data in JSON format

#include "GamestateDumper.h"
//#include "../Addons/Util.h"
//#include <iostream>
//#include <fstream>
//#include <Windows.h>
#include <ctime>
using namespace BWAPI;

 // - called during the onStart() method in an agent
// - IMPORTANT - this can only be run after BWTA finishes analzing the map
void GamestateDumper::setup(bool doneAnalyzing) {

	// logging setup
	errorLogFilename = "error-log.txt";
	errorLogOutputFile.open(errorLogFilename.c_str(), std::fstream::ate);
	errorLogOutputFile << "[NEW GAME] Start time is "+ currentDateTime() + "\n";
	errorLogOutputFile << "[setup()] Beginning of method\n";
	errorLogOutputFile.flush();

	// leave off file extension because curr frame will be added
	//dataFilename = "C:\\Users\\dustin\\Desktop\\Current Projects\\StarcraftObserver\\GameStateOutput\\gameStateData-JSON-"; 
	dataFilenamePrefix = "C:\\Users\\dustin\\Dropbox\\StarcraftBWAssistantParserBranch\\StarcraftBWAssistant\\gameStateDumps\\gameStateData-JSON-"; 
	logFilename = "C:\\Users\\dustin\\Dropbox\\StarcraftBWAssistantParserBranch\\StarcraftBWAssistant\\gameStateDumps\\log.txt"; 
	
	// get information on regions once BWTA is finished analyzing
	if (doneAnalyzing) {
		int currRegionID = 0;
		for (std::set<BWTA::Region*>::const_iterator r = BWTA::getRegions().begin();
			r != BWTA::getRegions().end(); r++) {
				regionIDs.insert(std::make_pair((*r), currRegionID));
				currRegionID++;
		}
	}

	// get information on chokepoints
	if (doneAnalyzing) {
		int currChokeID = 0;
		for (std::set<BWTA::Chokepoint*>::const_iterator c = BWTA::getChokepoints().begin();
			c != BWTA::getChokepoints().end(); c++) {
				chokePointIDs.insert(std::make_pair((*c), currChokeID));
				currChokeID++;
		}
	}

	//Broodwar->sendText("Map Height is %d",Broodwar->mapHeight()); // 96 * 32
	//Broodwar->sendText("Map Width is %d",Broodwar->mapWidth()); // 128 * 32
	Broodwar->sendText("Good Luck!!"); // 128 * 32
	// create a stream for error logging - to help with debugging
	// then update the log file
	
	errorLogOutputFile << "[setup()] end of method\n";
	errorLogOutputFile.flush();
}

 // - called anytime the player wishes to dump gamestate data
void GamestateDumper::dumpGameState() {
	errorLogOutputFile << "[pullData()] beginning of method\n";
	errorLogOutputFile.flush();

	std::ofstream outputFile;
	std::string dataFilename = dataFilenamePrefix + currentDateTime() + ".txt";
	outputFile.open(dataFilename.c_str());
	
	std::string output = "";

	// Game data
	output += "{\"gameID\":\"0\",\n";
	output += " \"mapName\":\"" + Broodwar->mapFileName() + "\",\n";
	output += " \"elapsedTime\":\"" + SSTR(Broodwar->elapsedTime()) + "\",\n";

	errorLogOutputFile << "[pullData()] pulled gamestate data\n";
	errorLogOutputFile.flush();

	// Player data
	output += " \"players\":[\n";

	// remove the last comma (-2 for \n and the ,) then add the newline back
	//output = output.substr(0, output.length()-2) + "\n";
	//output += "\n               ],\n"; // ends the myUnits array
	// Units data for the units that our player sees but doesn't control
	//output += "     \"enemyUnits\":[\n";

	BWAPI::Player* me = Broodwar->self();

	//BWAPI::Player* ePlayer;

	boolean currPlayerHasUnit = false;

	// loop over all the players and find the one that is an enemy and not neutral
	int count = 0; // this is hacky...ewww - but I don't know how to turn an iterator into player 
	// object
	int numPlayers = Broodwar->getPlayers().size();
	for (int i = 0; i < numPlayers; i++) {
		BWAPI::Player* currPlayer = Broodwar->getPlayer(i);
		if (!currPlayer->isNeutral()) {

			if (currPlayer == Broodwar->self()) {
				// I am always player 0
				output += "    {\"playerId\":\"0\",\n";
			}else{
				// Enemy is always player 1
				output += "    {\"playerId\":\"1\",\n";
			}

			if (currPlayer->getUnits().size() == 0) {
				output = output.substr(0, output.length()-2) + "},\n";
			}else{

				// Units data for the units our player controls
				output += "     \"myUnits\":[\n";

				std::set<BWAPI::Unit*>::const_iterator u = currPlayer->getUnits().begin();
				while (u != currPlayer->getUnits().end()) {

					currPlayerHasUnit = true;


					output += "         {\"unitID\":\"" + SSTR((*u)->getID());
					//output += "\",\"unitTypeID\":\"" + SSTR((*u)->getType().getID());
					output += "\",\"unitType\":\"" + (*u)->getType().getName();
					output += "\",\"currentHitPoints\":\"" + SSTR((*u)->getHitPoints());
					output += "\",\"maxHitPoints\":\"" + SSTR((*u)->getType().maxHitPoints());
					output += "\",\"isBeingAttacked\":\"" + SSTR((*u)->isUnderAttack());
					output += "\",\"x\":\"" + SSTR((*u)->getPosition().x());
					output += "\",\"y\":\"" + SSTR((*u)->getPosition().y());
					int rID = regionIDs[BWTA::getRegion((*u)->getPosition())];
					output += "\",\"regionID\":\"" + SSTR(rID);
					output += "\",\"armor\":\"" + SSTR((*u)->getType().armor()); // un-upgraded armor
					output += "\",\"mineralCost\":\"" + SSTR((*u)->getType().mineralPrice());
					output += "\",\"gasCost\":\"" + SSTR((*u)->getType().gasPrice());
					output += "\"},\n";	

					u++;
				}
				// remove the last comma (-2 for \n and the ,) then add the newline back
				if (currPlayerHasUnit) {
					output = output.substr(0, output.length()-2) + "\n";
				}
				output += "\n               ]},\n"; // ends the myUnits array
			}
		}

	} // ends the player loop


	output = output.substr(0, output.length()-2) + "\n"; // trim the last comma

	// old version of getting the enemy player
	// BWAPI::Player* ePlayer = Broodwar->getPlayer(1);


	output += "            ],\n"; // ends the players array

	// write to file, flush, and reset the string
	outputFile << output;
	outputFile.flush();
	output = "";

	errorLogOutputFile << "[pullData()] pulled player (including units) data\n";
	errorLogOutputFile.flush();


	// Region data
	output += "\n \"regions\":[\n";
	std::map<BWTA::Region*, int>::const_iterator r = regionIDs.begin();
	while (r != regionIDs.end()) {
		output += "     {\"regionID\":\"" + SSTR((*r).second);
		output += "\",\"regionCenterX\":\"" + SSTR((*r).first->getCenter().x());
		output += "\",\"regionCenterY\":\"" + SSTR((*r).first->getCenter().y());
		output += "\"},\n";
		r++;
	}
	// remove the last comma (-2 for \n and the ,) then add the newline back
	output = output.substr(0, output.length()-2) + "\n";
	output += "\n           ],\n"; // ends the regions array

	errorLogOutputFile << "[pullData()] pulled region data\n";
	errorLogOutputFile.flush();

	// Chokepoint data
	output += "\n \"chokepoints\":[\n";
	//Broodwar->sendText("Dumping game state data, there are %d chokepoints",currFrame);
	std::map<const BWTA::Chokepoint*, int>::const_iterator c = chokePointIDs.begin();
	while (c != chokePointIDs.end()) {
		output += "     {\"chokepointID\":\"" + SSTR((*c).second);
		output += "\",\"chokepointCenterX\":\"" + SSTR((*c).first->getCenter().x());
		output += "\",\"chokepointCenterY\":\"" + SSTR((*c).first->getCenter().y());
		int rID1 = regionIDs[(*c).first->getRegions().first];
		int rID2 = regionIDs[(*c).first->getRegions().second];
		output += "\",\"connectedToRegionOneID\":\"" + SSTR(rID1);
		output += "\",\"connectedToRegionTwoID\":\"" + SSTR(rID2);
		output += "\"},\n";
		c++;
	}

	// remove the last comma (-2 for \n and the ,) then add the newline back
	output = output.substr(0, output.length()-2) + "\n";
	output += "\n               ]\n"; // end choke points array

	errorLogOutputFile << "[pullData()] pulled chokepoint data\n";
	errorLogOutputFile.flush();

	output += "}\n"; // end game object

	errorLogOutputFile << "[pullData()] end of method\n";
	errorLogOutputFile.flush();

	outputFile << output;
	outputFile.close();

	// now update log file with this file name
	// then update the log file
	std::ofstream logOutputFile;
	logOutputFile.open(logFilename.c_str(), std::fstream::ate);
	logOutputFile << "\n" + dataFilename;
	logOutputFile.close();
}

// basically to close all the file ports
void GamestateDumper::finish() {
	errorLogOutputFile.close();
}

// useful for getting date and time
const std::string GamestateDumper::currentDateTime() {
    time_t     now = time(0);
    struct tm  tstruct;
    char       buf[80];
    tstruct = *localtime(&now);
    // Visit http://www.cplusplus.com/reference/clibrary/ctime/strftime/
    // for more information about date/time format
    strftime(buf, sizeof(buf), "%Y-%m-%d---%H-%M-%S", &tstruct);

    return buf;
	
}
