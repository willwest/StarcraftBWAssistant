#pragma once
#include <BWAPI.h>
#include <BWTA.h>
#include <sstream>

// macro for converting ints to strings
#define SSTR( x ) dynamic_cast< std::ostringstream & >( \
        ( std::ostringstream() << std::dec << x ) ).str()

class Observer : public BWAPI::AIModule
{
public:
  virtual void onStart();
  virtual void onEnd(bool isWinner);
  virtual void onFrame();
  virtual void onUnitDiscover(BWAPI::Unit* unit);
  virtual void onUnitEvade(BWAPI::Unit* unit);
  virtual void onUnitMorph(BWAPI::Unit* unit);
  virtual void onUnitRenegade(BWAPI::Unit* unit);
  virtual void onUnitDestroy(BWAPI::Unit* unit);
  virtual void onSendText(std::string text);
  ~Observer(); //not part of BWAPI::AIModule
  void showStats(); //not part of BWAPI::AIModule
  void showPlayers();
  void showForces();
  bool analyzed;
  std::map<BWAPI::Unit*,BWAPI::UnitType> buildings;

  // returns a JSON formatted string that can be turned into RDF triples easily
  std::string pullData();
  void writeToFile(std::string, int frame, std::string);

private:
	// represents the current frame, updated every time onFrame() is called
	int currFrame;
	// how often to dump gamestate data to file
	int pullFrequency; 

	// filename where JSON formatted game state data will be written
	std::string dataFilename;

	// maps every region to an id using the BWAPI::Position of the center of the region as its
	// unique identifier
	std::map<BWTA::Region*, int> regionIDs;

	std::map<const BWTA::Chokepoint*, int> chokePointIDs;


	

};