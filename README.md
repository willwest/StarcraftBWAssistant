StarcraftBWAssistant
====================

Developers:
Will West (wmw212 at lehigh dot edu)
Dustin Dannenhauer (dtd212 at lehigh dot edu)
Kostas Hatalis (kmh511 at lehigh dot edu)

Project Description
===================

There are two major components in this project, a special interface through which a user plays Starcraft Brood War (patch 1.16.1). From the user's perspective there is no difference in playing through this interface and playing through the standard interface that shipped with the game. However, this interface provided by this project, referred to as the observer component, consistently dumps gamestate data in a JSON format which can then be used by the second component of this project, referred to as the assistant. This assistant displays information about the game after loading the most recent gamestate data, importing it into an semantic web model using the JENA api, and reasoning over it with an ontology. The assistant has two main features: 1. An image of the entire map superimposed with unit and region data from the current state of the game from the players perspective and 2. Inferences about what units the enemy player can build/train. The observer is written in C++ and is loaded in the same way any BWAPI module is loaded, using a hack loader such as Chaoslauncher. The assistant is written in Java and runs standalone. The observer communicates with the assistant by writing files that the assistant then reads, when the user clicks the update button. 

Currently there are some issues creating a local portable folder containing the source code of both projects due to trouble linking all the necessary libraries involved in reasoning over the JENA model. If you would like to download this and run it on your machine, you will need to manually change some hardcoded strings in both the observer code and the assistant code (yes I realize this is horrible, its one of the first next issues).

As a disclaimer, this was a group project turned in for a class, and most likely will not be used by itself again. Consider it a proof of concept. Still not a good justification for ugly code. Our apologies.





