# PtutS2Grp12

Bienvenue sur le dépot du projet tutoré du groupe 12
vous trouverez dans ce dépot le livrables.


## Procédure d'instalation de java Fx sur ton pc 

### déja t'installe :
- e(fx) pc de l'iut -> déja installer  
      ton pc -> Help / Eclipse Marketplace et la tu cherche e(fx)clipse

- si c un nouveau projet java Fx tu fait new / other / JavaFx / JavaFx Project
	puis directement finish  
	ensuite tu rajoute les lib :  
	clic droit sur ton dossier de projet puis properties  
	dans Java Build Path puis les onglet en haut tu choisi librairies  
	ensuite tu click sur  le ClassPath et tu fait add library...  
	tu clique sur User Library puis next  
	tu clique sur User Libraries....  
	ensuite tu fait new tu tape Java Fx 15  
	tu clique sur la librairie que tu a créer  
	tu clique sur Add External JARS  
	tu selectionne TOUT les jar qui sont stocker dans le dépot  
	Github dans .jdk puis javafx-sdk-15.0.1 puis lib et tu clique sur ouvrir  
	tu clique sur apply and close   
	tu selectionne la librairy java Fx 15 puis sur finish  
	tu clique sur Apply and Close  

- si c un projet que tu a ouvert a importé dans éclipse tu doit importé les lib
	clic droit sur ton dossier de projet puis properties  
	dans Java Build Path puis les onglet en haut tu choisi librairies  
	ensuite tu click sur  le ClassPath et tu fait add library...  
	tu clique sur User Library puis next  
	tu clique sur User Libraries....  
	ensuite tu fait new tu tape Java Fx 15  
	tu clique sur la librairie que tu a créer  
	tu clique sur Add External JARS  
	tu selectionne TOUT les jar qui sont stocker dans le dépot  
	Github dans .jdk puis javafx-sdk-15.0.1 puis lib et tu clique sur ouvrir  
	tu clique sur apply and close   
	tu selectionne la librairy java Fx 15 puis sur finish  
	tu clique sur Apply and Close  

- ensuite dans tout les cas tu doit rajouter une ligne de commande pour la génération de l'application  
	sur le petit bouton vert qui te permet de lancer l'app tu clique sur la fleche pour afficher les option 
	puis tu clique sur Run configurations  
	dans les onglet en haut tu clique sur Arguments  
	* 2  option sois tu as importé ce dépot github et tu l'à ouvert avec éclipse alors  
		* tu rajoute cette ligne dans VM arguments:  
		```
		--module-path ../.jdk/javafx-sdk-15.0.1/lib --add-modules=javafx.controls,javafx.fxml  
		```
	* sinon  
		* tu modifie cette ligne de commande
		```  
		--module-path CHANGEMOI --add-modules=javafx.controls,javafx.fxml  
		```
		en changean CHANGEMOI par le chemin vers le dossier lib de javafx-sdk  
	tu clique sur Apply puis  close ou run  

ensuite tu peux configurer scene builder pour éclipse  
	Windows / préférences  
	clique sur Java Fx   
	tu clique sur le button Browse ... de SceneBuilder executable  
	tu sélectionne l'executable dans le dépot github  
	et maintenant tu peux ouvrir n'importe qu'elle fichier fxml  

et normalement tout est bon  


cette procédure n'est certainement pas parfaite donc merci de ne 
pas faire chier si il y a des erreur ou que sa marche pas dans tout 
les cas je vous répond
"dommage" 
