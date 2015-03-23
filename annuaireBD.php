<?php 
	/*Fonctions-modèle réalisant les requètes de gestion des contacts en base de données*/

	// liste_contact_bd : retourne la liste des informations pour chaque contact de l'utilisateur $id
	function liste_contacts_bd($term) {
		require('modele/configSQL.php');
		$tmp = explode(' ', $term);
		$req = "SELECT * FROM professeur WHERE ";
		$tour = false;
		$tab = array();
		foreach($tmp as $t) {
			if($tour)
				$req = $req . " OR ";
			$req = $req . "nom LIKE " . 
			$t . " OR prenom LIKE " . $t . " OR initiales LIKE " . $t;
			$tour = true;
		}
		$req = $req . ";";
		
		$res = mysqli_query($link, $req)
		    or die ("erreur de requête : " . $req); 
	
		return mysqli_fetch_assoc($res);
	}
?>
