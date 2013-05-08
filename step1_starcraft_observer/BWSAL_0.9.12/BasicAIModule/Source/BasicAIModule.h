#pragma once
#include <BWAPI.h>
#include <BWTA.h>
#include <Arbitrator.h>
#include <WorkerManager.h>
#include <SupplyManager.h>
#include <BuildManager.h>
#include <BuildOrderManager.h>
#include <TechManager.h>
#include <UpgradeManager.h>
#include <BaseManager.h>
#include <ScoutManager.h>
#include <DefenseManager.h>
#include <InformationManager.h>
#include <BorderManager.h>
#include <UnitGroupManager.h>
#include <EnhancedUI.h>

#include "ChokeAdvisor.h"

class BasicAIModule : public BWAPI::AIModule
{
public:

	enum PHASE
	{
		DRONE, RUSH, FIGHT, DEFENSE, WIN
	};

  virtual void onStart();
  virtual void onEnd(bool isWinner);
  virtual void onFrame();
  virtual void onUnitDiscover(BWAPI::Unit* unit);
  virtual void onUnitEvade(BWAPI::Unit* unit);
  virtual void onUnitMorph(BWAPI::Unit* unit);
  virtual void onUnitRenegade(BWAPI::Unit* unit);
  virtual void onUnitDestroy(BWAPI::Unit* unit);
  virtual void onSendText(std::string text);
  virtual int getIdleWraiths();
  virtual void attack();
  virtual int workerCount();
  virtual int marineCount();
  ~BasicAIModule(); //not part of BWAPI::AIModule
  void showStats(); //not part of BWAPI::AIModule
  void showPlayers();
  void showForces();
  bool analyzed;
  std::map<BWAPI::Unit*,BWAPI::UnitType> buildings;
  Arbitrator::Arbitrator<BWAPI::Unit*,double> arbitrator;
  WorkerManager* workerManager;
  SupplyManager* supplyManager;
  BuildManager* buildManager;
  TechManager* techManager;
  UpgradeManager* upgradeManager;
  BaseManager* baseManager;
  ScoutManager* scoutManager;
  BuildOrderManager* buildOrderManager;
  DefenseManager* defenseManager;
  InformationManager* informationManager;
  BorderManager* borderManager;
  UnitGroupManager* unitGroupManager;
  //ChokeAdvisor* chokeAdvisor;
  EnhancedUI* enhancedUI;
  bool showManagerAssignments;
  
  BWTA::BaseLocation* home;
  BWTA::BaseLocation* enemy;
  PHASE phase;
  int waves;
};