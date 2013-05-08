#pragma once
#include <BWAPI.h>
#include <BWTA.h>
#include <sstream>
#include <iostream>
#include <fstream>
#include "GamestateDumper.h"


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
  void pullDataAndWriteToFile(std::string, int);
  void writeToFile(std::string, int frame, std::string);

private:
	// represents the current frame, updated every time onFrame() is called
	int currFrame;
	int pullFrequency;
	GamestateDumper gsDumper;
	


	

};