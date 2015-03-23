-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 23 Mars 2015 à 16:46
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `pjs4`
--

-- --------------------------------------------------------

--
-- Structure de la table `chargede`
--

CREATE TABLE IF NOT EXISTS `chargede` (
  `idChargeDe` int(11) NOT NULL AUTO_INCREMENT,
  `fk_idProf` int(11) DEFAULT NULL,
  `fk_idModule` int(11) DEFAULT NULL,
  PRIMARY KEY (`idChargeDe`),
  KEY `FK_prof_charge` (`fk_idProf`),
  KEY `FK_module_charge` (`fk_idModule`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `chargede`
--

INSERT INTO `chargede` (`idChargeDe`, `fk_idProf`, `fk_idModule`) VALUES
(1, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE IF NOT EXISTS `cours` (
  `idCours` int(11) NOT NULL AUTO_INCREMENT,
  `duree` int(11) NOT NULL,
  `fk_idModule` int(11) DEFAULT NULL,
  `fk_idSalle` int(11) DEFAULT NULL,
  `dates` datetime DEFAULT NULL,
  PRIMARY KEY (`idCours`),
  KEY `FK_mod_cours` (`fk_idModule`),
  KEY `FK_salle_cours` (`fk_idSalle`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `cours`
--

INSERT INTO `cours` (`idCours`, `duree`, `fk_idModule`, `fk_idSalle`, `dates`) VALUES
(1, 90, NULL, NULL, '2015-03-03 00:00:00'),
(2, 95, 2, 4, '0000-00-00 00:00:00'),
(3, 60, 2, 5, '2015-04-02 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `enseigne`
--

CREATE TABLE IF NOT EXISTS `enseigne` (
  `idEnseigne` int(11) NOT NULL AUTO_INCREMENT,
  `fk_idProf` int(11) DEFAULT NULL,
  `fk_idCours` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEnseigne`),
  KEY `FK_prof_enseign` (`fk_idProf`),
  KEY `FK_cour_enseign` (`fk_idCours`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `enseigne`
--

INSERT INTO `enseigne` (`idEnseigne`, `fk_idProf`, `fk_idCours`) VALUES
(1, 2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE IF NOT EXISTS `etudiant` (
  `idEtu` int(11) NOT NULL AUTO_INCREMENT,
  `fk_numgroupe` int(11) DEFAULT NULL,
  `ine` varchar(11) NOT NULL,
  PRIMARY KEY (`idEtu`),
  KEY `FK_group_etu` (`fk_numgroupe`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `etudiant`
--

INSERT INTO `etudiant` (`idEtu`, `fk_numgroupe`, `ine`) VALUES
(1, 1, '001');

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE IF NOT EXISTS `groupe` (
  `idGroupe` int(11) NOT NULL AUTO_INCREMENT,
  `numGroupe` int(11) NOT NULL,
  PRIMARY KEY (`idGroupe`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `groupe`
--

INSERT INTO `groupe` (`idGroupe`, `numGroupe`) VALUES
(1, 201);

-- --------------------------------------------------------

--
-- Structure de la table `modules`
--

CREATE TABLE IF NOT EXISTS `modules` (
  `idModule` int(11) NOT NULL AUTO_INCREMENT,
  `nomModule` varchar(10) NOT NULL,
  `couleur` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`idModule`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `modules`
--

INSERT INTO `modules` (`idModule`, `nomModule`, `couleur`) VALUES
(2, 'MathMod', '#FD6C9E');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE IF NOT EXISTS `personne` (
  `idPers` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `sexe` varchar(1) NOT NULL,
  `identifiant` varchar(30) NOT NULL,
  PRIMARY KEY (`idPers`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`idPers`, `nom`, `prenom`, `password`, `mail`, `sexe`, `identifiant`) VALUES
(1, 'bob', 'bob', 'bob', 'bob@gmail.com', 'H', 'boby'),
(2, 'tom', 'tom', 'tom', 'tom@hotmail.fr', 'F', 'tomcat');

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

CREATE TABLE IF NOT EXISTS `professeur` (
  `idProf` int(11) NOT NULL AUTO_INCREMENT,
  `bureau` varchar(10) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `initiales` varchar(3) NOT NULL,
  PRIMARY KEY (`idProf`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `professeur`
--

INSERT INTO `professeur` (`idProf`, `bureau`, `telephone`, `initiales`) VALUES
(2, 'B206', '014324445', 'PH');

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

CREATE TABLE IF NOT EXISTS `projet` (
  `idProjet` int(11) NOT NULL AUTO_INCREMENT,
  `intitulé` varchar(25) NOT NULL,
  `dateRendu` date NOT NULL,
  `fk_idModule` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProjet`),
  KEY `FK_module_projet` (`fk_idModule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE IF NOT EXISTS `salle` (
  `idSalle` int(11) NOT NULL AUTO_INCREMENT,
  `numSalle` varchar(5) DEFAULT NULL,
  `nbGroupePossibles` int(11) NOT NULL,
  PRIMARY KEY (`idSalle`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `salle`
--

INSERT INTO `salle` (`idSalle`, `numSalle`, `nbGroupePossibles`) VALUES
(1, 'B2-20', 0),
(2, 'B2-13', 0),
(3, 'B2-14', 0),
(4, 'B2-15', 0),
(5, 'B2-16', 0),
(6, 'B2-17', 0),
(7, 'B1-16', 0),
(8, 'B1-17', 0),
(9, 'B1-11', 0),
(10, 'B1-12', 0),
(11, 'B1-13', 0),
(12, 'B1-4', 0);

-- --------------------------------------------------------

--
-- Structure de la table `suit`
--

CREATE TABLE IF NOT EXISTS `suit` (
  `idSuit` int(11) NOT NULL AUTO_INCREMENT,
  `fk_idCours` int(11) DEFAULT NULL,
  `fk_idGroupe` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSuit`),
  KEY `FK_cour_suit` (`fk_idCours`),
  KEY `FK_group_suit` (`fk_idGroupe`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `suit`
--

INSERT INTO `suit` (`idSuit`, `fk_idCours`, `fk_idGroupe`) VALUES
(1, 3, 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `chargede`
--
ALTER TABLE `chargede`
  ADD CONSTRAINT `FK_module_charge` FOREIGN KEY (`fk_idModule`) REFERENCES `modules` (`idModule`),
  ADD CONSTRAINT `FK_prof_charge` FOREIGN KEY (`fk_idProf`) REFERENCES `professeur` (`idProf`);

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `FK_mod_cours` FOREIGN KEY (`fk_idModule`) REFERENCES `modules` (`idModule`),
  ADD CONSTRAINT `FK_salle_cours` FOREIGN KEY (`fk_idSalle`) REFERENCES `salle` (`idSalle`);

--
-- Contraintes pour la table `enseigne`
--
ALTER TABLE `enseigne`
  ADD CONSTRAINT `FK_cour_enseign` FOREIGN KEY (`fk_idCours`) REFERENCES `cours` (`idCours`),
  ADD CONSTRAINT `FK_prof_enseign` FOREIGN KEY (`fk_idProf`) REFERENCES `professeur` (`idProf`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `FK_group_etu` FOREIGN KEY (`fk_numgroupe`) REFERENCES `groupe` (`idGroupe`),
  ADD CONSTRAINT `FK_person_etu` FOREIGN KEY (`idEtu`) REFERENCES `personne` (`idPers`);

--
-- Contraintes pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD CONSTRAINT `FK_person_prof` FOREIGN KEY (`idProf`) REFERENCES `personne` (`idPers`);

--
-- Contraintes pour la table `projet`
--
ALTER TABLE `projet`
  ADD CONSTRAINT `FK_module_projet` FOREIGN KEY (`fk_idModule`) REFERENCES `modules` (`idModule`);

--
-- Contraintes pour la table `suit`
--
ALTER TABLE `suit`
  ADD CONSTRAINT `FK_cour_suit` FOREIGN KEY (`fk_idCours`) REFERENCES `cours` (`idCours`),
  ADD CONSTRAINT `FK_group_suit` FOREIGN KEY (`fk_idGroupe`) REFERENCES `groupe` (`idGroupe`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
