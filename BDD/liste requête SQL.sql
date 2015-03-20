//Coté client lourd
//AJOUTER CONTRAINTE INTEGERITEE SUR PROF ET ELEVES
//après connection !
//requête de récupération des cours

select m.nomModule, c.dates, c.duree, g.numGroupe, sa.numSalle, pro.initiales
FROM modules m, Cours c, Groupe g, suit su, salle sa, enseigne e, professeur pro, personne pers
WHERE c.dates > NOW()
AND c.idCours = su.fk_idCours
AND su.fk_idGroupe = g.idGroupe
AND c.fk_idSalle = sa.idSalle
AND c.idCours = e.fk_idCours
AND e.fk_idProf = pro.idProf
AND pro.idProf = pers.idPers