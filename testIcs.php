<?php

/* *********************************************************** */
/* Parser ICS, codé par nos soins. Absolument pas maintenable, */ 
/* utilisable seulement avec les fichiers .ics générés par ADE */
/* *********************************************************** */

$c = file_get_contents("http://ent-ng.parisdescartes.fr/export/ics.php?login=if00325");

$test = explode("\n", $c); 	// on "explode" au saut de ligne le fichier .ics ; cf plus bas le fonctionnement
							// de cette fonction
$tab = array();	// tableau "temporaire" qui va contenir à chaque tour de boucle un évènement
$i = 0;			// j'utilise un compteur pour faire mes tours de boucle, parce qu'à l'origine je jouais
				// un peu avec les indices ; ce n'est plus le cas maintenant, donc on remplacera le while
				// par un foreach avec break (je suppose)

/* *************** */
/* *** EN-TÊTE *** */
/* *************** */
				
// récupération de l'entête (en fait, on la lit juste... et peut-être que je me plante, mais on s'en fiche,
// l'en-tête n'est pas ce que l'on cherche à récupérer ici)

while($test[$i] != "BEGIN:VEVENT") { 	// tant qu'on ne tombe pas sur la "balise" de début d'un event,
										// c'est qu'on est toujours dans l'en-tête
	$a = explode(":", $test[$i]);
	$tab[$a[0]] = $a[1];
	$i++;
}

/* ****************** */
/* *** EVENEMENTS *** */
/* ****************** */

$events = array(); // tableau contenant tooous les évènements (stockés sous forme de tableaux associatifs)
$lastKey = ""; 	// la dernière clé utilisée dans le tableau ; va nous servir pour rentrer dans une seule
				// case du tableau la description de l'évènement

while($test[$i] != "END:VCALENDAR") {
	$tmp = $test[$i];
	if(substr($tmp, 0, 1) == "(") { // la seule ligne qui commence par une parenthèse ouvrante dans nos
									// fichiers .ics est une partie de la description ; on va donc l'ajouter
									// à cette dernière, sans se poser de question
		$tab[$lastKey] = $tab[$lastKey] . " " . $tmp;
	}
	else {
		$a = explode(":", $test[$i]); 	// explode : sépare en deux une string au niveau du délimiteur (sans
										// inclure ce dernier. Renvoie un tableau, dans chacun des cases de
										// ce tableau se trouve un peu de la string, qui était délimité
										// par le délimiteur (ici, le symbole ":").
										// Si la string originale ne contient pas le délimiteur spécifié,
										// alors (cf ligne suivante) la fonction renverra un tableau à une
										// case, et cette case contiendra la string originale.
		if($tmp == $a[0]) {
			$tab[$lastKey] = $tab[$lastKey] . " " . $tmp;
		}
		else { // sinon, on a bien affaire à une nouvelle clé, donc on l'ajoute au tableau
			$lastKey = $a[0];
			$tab[$lastKey] = $a[1];
		}
	}
	if($test[$i] == "END:VEVENT") { // si on est arrivé à une fin d'évènement, on stocke l'évènement enregistré
									// dans le tableau $event, puis on recrée un nouveau tableau pour stocker l'évènement suivant
		$events[] = $tab;
		$tab = array();
	}
	$i++;
}

// affichage du tableau des évènements
print_r($events);

?>