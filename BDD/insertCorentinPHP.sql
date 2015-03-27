INSERT INTO salle(numSalle, nbGroupePossibles)VALUES( '%s' , 0);

INSERT INTO modules(nomModule,couleur)VALUES('%s','%s');

INSERT INTO groupe(numGroupe) VALUES ('%s');

INSERT INTO projet(intitulé,dateRendu,fk_idModule)
VALUES('%s','%s',%d);

SELECT idProf FROM professeur prof, personne pers 
WHERE prof.idProf = pers.idPers
AND pers.nom = '%s';

SELECT idSalle FROM salle WHERE numsalle = '%s';

SELECT idGroupe from groupe WHERE numGroupe = '%s';

INSERT INTO cours(duree,fk_idModule,fk_idSalle,dates)
VALUES('%s',%d,%d,'%s');

SELECT idcours 
FROM cours
ORDER BY idcours DESC
LIMIT 1;

INSERT INTO personne(nom,prenom,password,mail,sexe,identifiant)
VALUES ('%s','%s','%s','%s','%s','%s');

INSERT INTO professeur(idProf,burean,telephone,initiales)
VALUES(%d,'%s','%s','%s');