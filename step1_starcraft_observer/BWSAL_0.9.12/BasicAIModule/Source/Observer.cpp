#include "Observer.h"
#include "../Addons/Util.h"
#include <iostream>
#include <fstream>
using namespace BWAPI;

void Observer::onStart()
{
	currFrame = 0;
	pullFrequency = 500;
	// leave off file extension because curr frame will be added
	dataFilename = "C:\\Users\\dustin\\Desktop\\Current Projects\\StarcraftObserver\\GameStateOutput\\gameStateData-JSON-"; 

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

	// get information on regions once BWTA is finished analyzing
	if (this->analyzed) {
		int currRegionID = 0;
		for (std::set<BWTA::Region*>::const_iterator r = BWTA::getRegions().begin();
			r != BWTA::getRegions().end(); r++) {
				regionIDs.insert(std::make_pair((*r), currRegionID));
				currRegionID++;
		}
	}

	// get information on chokepoints
	if (this->analyzed) {
		int currChokeID = 0;
		for (std::set<BWTA::Chokepoint*>::const_iterator c = BWTA::getChokepoints().begin();
			c != BWTA::getChokepoints().end(); c++) {
				chokePointIDs.insert(std::make_pair((*c), currChokeID));
				currChokeID++;
		}
	}
}

std::string Observer::pullData() {

	std::string output = "";

	// Game data
	output += "{\"gameID\":\"0\",\n";
	output += " \"mapName\":\"" + Broodwar->mapFileName() + "\",\n";

	// Player data
	output += " \"players\":[\n";
	output += "    {\"playerId\":\"" + SSTR(Broodwar->self()->getID()) + "\",\n";

	// Units data for the units our player controls
	output += "     \"myUnits\":[\n";

	std::set<BWAPI::Unit*>::const_iterator u = Broodwar->self()->getUnits().begin();
	while (u != Broodwar->self()->getUnits().end()) {
		output += "         {\"unitID\":\"" + SSTR((*u)->getID());
		output += "\",\"unitTypeID\":\"" + SSTR((*u)->getType().getID());
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
	output = output.substr(0, output.length()-2) + "\n";
	output += "\n               ],\n"; // ends the myUnits array
	// Units data for the units that our player sees but doesn't control
	output += "     \"enemyUnits\":[\n";

	BWAPI::Player* ePlayer = Broodwar->getPlayer(1);
	BWAPI::Player* me = Broodwar->self();
	std::set<BWAPI::Unit*>::const_iterator e = ePlayer->getUnits().begin();
	bool enemyUnitExists = false; 
	while (e != ePlayer->getUnits().end()) {
		if ((*e)->isVisible(me)) {
			output += "         {\"unitID\":\"" + SSTR((*e)->getID());
			output += "\",\"unitTypeID\":\"" + SSTR((*e)->getType().getID());
			output += "\",\"currentHitPoints\":\"" + SSTR((*e)->getHitPoints());
			output += "\",\"maxHitPoints\":\"" + SSTR((*e)->getType().maxHitPoints());
			output += "\",\"isBeingAttacked\":\"" + SSTR((*e)->isUnderAttack());
			output += "\",\"x\":\"" + SSTR((*e)->getPosition().x());
			output += "\",\"y\":\"" + SSTR((*e)->getPosition().y());
			int rID = regionIDs[BWTA::getRegion((*e)->getPosition())];
			output += "\",\"regionID\":\"" + SSTR(rID);
			output += "\",\"armor\":\"" + SSTR((*e)->getType().armor()); // un-upgraded armor
			output += "\",\"mineralCost\":\"" + SSTR((*e)->getType().mineralPrice());
			output += "\",\"gasCost\":\"" + SSTR((*e)->getType().gasPrice());
			output += "\"},\n";	
			enemyUnitExists = true;
		}
		e++;
	}

	if (enemyUnitExists) {
		// remove the last comma (-2 for \n and the ,) then add the newline back
		output = output.substr(0, output.length()-2) + "\n";
	}


	output += "            ]\n"; // ends the enemyUnits array
	output += "    }],\n";
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

	output += "}\n"; // end game object

	return output;
}

// takes a frame argument to make the filename unique if called multiple times
void Observer::writeToFile(std::string filename, int frame, std::string data) {
	std::ofstream outputFile;
	outputFile.open((filename +  SSTR(frame) + ".txt").c_str());
	outputFile << data;
	outputFile.close();
}

Observer::~Observer() {}

void Observer::onEnd(bool isWinner)
{
	log("onEnd(%d)\n",isWinner);
}
void Observer::onFrame()
{
	if (Broodwar->isReplay()) return;
	if (!this->analyzed) return;
	currFrame++;

	if (currFrame % pullFrequency == 0) {
		Broodwar->sendText("Dumping game state data, current Frame is %d",currFrame);
		//Broodwar->sendText(pullData().c_str());
		writeToFile(dataFilename, currFrame, pullData());
		//Broodwar->sendText("Current Frame is %d",currFrame);
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
