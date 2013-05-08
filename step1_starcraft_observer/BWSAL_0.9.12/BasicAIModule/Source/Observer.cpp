#include "Observer.h"
#include "../Addons/Util.h"
#include <iostream>
#include <fstream>
#include <Windows.h>
#include <ctime>
#include "GamestateDumper.h"
using namespace BWAPI;

void Observer::onStart()
{
	currFrame = 0;
	pullFrequency = 50;
	// THIS DISPLAYS THE CURRENT DIRECTORY
	//TCHAR pwd[MAX_PATH];
	//GetCurrentDirectory(MAX_PATH,pwd);
	//MessageBox(NULL,pwd,pwd,0);

	if (Broodwar->isReplay()) return;
	// Enable some cheat flags
	Broodwar->enableFlag(Flag::UserInput);
	//Broodwar->enableFlag(Flag::CompleteMapInformation);

	// Run BWTA
	BWTA::readMap();
	BWTA::analyze();
	this->analyzed=true;

	// get information about our bot
	BWAPI::Race race = Broodwar->self()->getRace();
	BWAPI::Race enemyRace = Broodwar->enemy()->getRace();
	BWAPI::UnitType workerType=race.getWorker();
	double minDist;
	BWTA::BaseLocation* natural=NULL;
	BWTA::BaseLocation* home=BWTA::getStartLocation(Broodwar->self());
	for(std::set<BWTA::BaseLocation*>::const_iterator 
		b=BWTA::getBaseLocations().begin();b!=BWTA::getBaseLocations().end();b++)
	{
		if (*b==home) continue;
		double dist=home->getGroundDistance(*b);
		if (dist>0)
		{
			if (natural==NULL || dist<minDist)
			{
				minDist=dist;
				natural=*b;
			}
		}
	}

	gsDumper.setup(this->analyzed);			
	
}

//void Observer::pullDataAndWriteToFile(std::string outputFileName, int frame) {
//	errorLogOutputFile << "[pullData()] beginning of method\n";
//	errorLogOutputFile.flush();
//
//	std::ofstream outputFile;
//	outputFile.open((outputFileName +  SSTR(frame) + ".txt").c_str());
//
//	std::string output = "";
//
//	// Game data
//	output += "{\"gameID\":\"0\",\n";
//	output += " \"mapName\":\"" + Broodwar->mapFileName() + "\",\n";
//	output += " \"elapsedTime\":\"" + SSTR(Broodwar->elapsedTime()) + "\",\n";
//
//	errorLogOutputFile << "[pullData()] pulled gamestate data\n";
//	errorLogOutputFile.flush();
//
//	// Player data
//	output += " \"players\":[\n";
//
//	// remove the last comma (-2 for \n and the ,) then add the newline back
//	//output = output.substr(0, output.length()-2) + "\n";
//	//output += "\n               ],\n"; // ends the myUnits array
//	// Units data for the units that our player sees but doesn't control
//	//output += "     \"enemyUnits\":[\n";
//
//	BWAPI::Player* me = Broodwar->self();
//
//	//BWAPI::Player* ePlayer;
//
//	boolean currPlayerHasUnit = false;
//
//	// loop over all the players and find the one that is an enemy and not neutral
//	int count = 0; // this is hacky...ewww - but I don't know how to turn an iterator into player 
//	// object
//	int numPlayers = Broodwar->getPlayers().size();
//	for (int i = 0; i < numPlayers; i++) {
//		BWAPI::Player* currPlayer = Broodwar->getPlayer(i);
//		if (!currPlayer->isNeutral()) {
//
//			if (currPlayer == Broodwar->self()) {
//				// I am always player 0
//				output += "    {\"playerId\":\"0\",\n";
//			}else{
//				// Enemy is always player 1
//				output += "    {\"playerId\":\"1\",\n";
//			}
//
//			if (currPlayer->getUnits().size() == 0) {
//				output = output.substr(0, output.length()-2) + "},\n";
//			}else{
//
//				// Units data for the units our player controls
//				output += "     \"myUnits\":[\n";
//
//				std::set<BWAPI::Unit*>::const_iterator u = currPlayer->getUnits().begin();
//				while (u != currPlayer->getUnits().end()) {
//
//					currPlayerHasUnit = true;
//
//
//					output += "         {\"unitID\":\"" + SSTR((*u)->getID());
//					//output += "\",\"unitTypeID\":\"" + SSTR((*u)->getType().getID());
//					output += "\",\"unitType\":\"" + (*u)->getType().getName();
//					output += "\",\"currentHitPoints\":\"" + SSTR((*u)->getHitPoints());
//					output += "\",\"maxHitPoints\":\"" + SSTR((*u)->getType().maxHitPoints());
//					output += "\",\"isBeingAttacked\":\"" + SSTR((*u)->isUnderAttack());
//					output += "\",\"x\":\"" + SSTR((*u)->getPosition().x());
//					output += "\",\"y\":\"" + SSTR((*u)->getPosition().y());
//					int rID = regionIDs[BWTA::getRegion((*u)->getPosition())];
//					output += "\",\"regionID\":\"" + SSTR(rID);
//					output += "\",\"armor\":\"" + SSTR((*u)->getType().armor()); // un-upgraded armor
//					output += "\",\"mineralCost\":\"" + SSTR((*u)->getType().mineralPrice());
//					output += "\",\"gasCost\":\"" + SSTR((*u)->getType().gasPrice());
//					output += "\"},\n";	
//
//					u++;
//				}
//				// remove the last comma (-2 for \n and the ,) then add the newline back
//				if (currPlayerHasUnit) {
//					output = output.substr(0, output.length()-2) + "\n";
//				}
//				output += "\n               ]},\n"; // ends the myUnits array
//			}
//		}
//
//	} // ends the player loop
//
//
//	output = output.substr(0, output.length()-2) + "\n"; // trim the last comma
//
//	
//
//
//	// old version of getting the enemy player
//	// BWAPI::Player* ePlayer = Broodwar->getPlayer(1);
//
//
//	output += "            ],\n"; // ends the players array
//
//	// write to file, flush, and reset the string
//	outputFile << output;
//	outputFile.flush();
//	output = "";
//
//	errorLogOutputFile << "[pullData()] pulled player (including units) data\n";
//	errorLogOutputFile.flush();
//
//
//	// Region data
//	output += "\n \"regions\":[\n";
//	std::map<BWTA::Region*, int>::const_iterator r = regionIDs.begin();
//	while (r != regionIDs.end()) {
//		output += "     {\"regionID\":\"" + SSTR((*r).second);
//		output += "\",\"regionCenterX\":\"" + SSTR((*r).first->getCenter().x());
//		output += "\",\"regionCenterY\":\"" + SSTR((*r).first->getCenter().y());
//		output += "\"},\n";
//		r++;
//	}
//	// remove the last comma (-2 for \n and the ,) then add the newline back
//	output = output.substr(0, output.length()-2) + "\n";
//	output += "\n           ],\n"; // ends the regions array
//
//	errorLogOutputFile << "[pullData()] pulled region data\n";
//	errorLogOutputFile.flush();
//
//	// Chokepoint data
//	output += "\n \"chokepoints\":[\n";
//	//Broodwar->sendText("Dumping game state data, there are %d chokepoints",currFrame);
//	std::map<const BWTA::Chokepoint*, int>::const_iterator c = chokePointIDs.begin();
//	while (c != chokePointIDs.end()) {
//		output += "     {\"chokepointID\":\"" + SSTR((*c).second);
//		output += "\",\"chokepointCenterX\":\"" + SSTR((*c).first->getCenter().x());
//		output += "\",\"chokepointCenterY\":\"" + SSTR((*c).first->getCenter().y());
//		int rID1 = regionIDs[(*c).first->getRegions().first];
//		int rID2 = regionIDs[(*c).first->getRegions().second];
//		output += "\",\"connectedToRegionOneID\":\"" + SSTR(rID1);
//		output += "\",\"connectedToRegionTwoID\":\"" + SSTR(rID2);
//		output += "\"},\n";
//		c++;
//	}
//
//	// remove the last comma (-2 for \n and the ,) then add the newline back
//	output = output.substr(0, output.length()-2) + "\n";
//	output += "\n               ]\n"; // end choke points array
//
//	errorLogOutputFile << "[pullData()] pulled chokepoint data\n";
//	errorLogOutputFile.flush();
//
//	output += "}\n"; // end game object
//
//	errorLogOutputFile << "[pullData()] end of method\n";
//	errorLogOutputFile.flush();
//
//
//
//
//	outputFile << output;
//	outputFile.close();
//
//	// now update log file with this file name
//	// then update the log file
//	std::ofstream logOutputFile;
//	logOutputFile.open(logFilename.c_str(), std::fstream::ate);
//	logOutputFile << "\n" + outputFileName + SSTR(frame) + ".txt";
//	logOutputFile.close();
//}

// takes a frame argument to make the filename unique if called multiple times
//void Observer::writeToFile(std::string filename, int frame, std::string data) {
//	// write the gamestate output file first
//	std::ofstream outputFile;
//	outputFile.open((filename +  SSTR(frame) + ".txt").c_str());
//	outputFile << data;
//	outputFile.close();
//
//	
//
//	
//}

Observer::~Observer() {}

void Observer::onEnd(bool isWinner)
{
	log("onEnd(%d)\n",isWinner);
	gsDumper.finish();
}
void Observer::onFrame()
{
	if (Broodwar->isReplay()) return;
	if (!this->analyzed) return;
	currFrame++;

	if (currFrame % pullFrequency == 0) {
		gsDumper.dumpGameState();
	}
}

void Observer::onUnitDestroy(BWAPI::Unit* unit)
{
	if (Broodwar->isReplay()) return;
	//Broodwar->sendText("Unit %d was destroyed",unit->getID());
}

void Observer::onUnitDiscover(BWAPI::Unit* unit)
{
	if (Broodwar->isReplay()) return;
	//Broodwar->sendText("Unit %d was discovered",unit->getID());
}
void Observer::onUnitEvade(BWAPI::Unit* unit)
{
	if (Broodwar->isReplay()) return;
	//Broodwar->sendText("Unit %d evaded",unit->getID());
}

void Observer::onUnitMorph(BWAPI::Unit* unit)
{
	if (Broodwar->isReplay()) return;
	//Broodwar->sendText("Unit %d morphed",unit->getID());
}
void Observer::onUnitRenegade(BWAPI::Unit* unit)
{
	if (Broodwar->isReplay()) return;
	//Broodwar->sendText("Unit %d is now a renegade",unit->getID());
}

void Observer::onSendText(std::string text)
{
	Broodwar->sendText("%s",text.c_str());
	return;
}
