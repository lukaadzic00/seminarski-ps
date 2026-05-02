/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.44 : Database - ps_seminarski
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ps_seminarski` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ps_seminarski`;

/*Table structure for table `bibliotekar` */

DROP TABLE IF EXISTS `bibliotekar`;

CREATE TABLE `bibliotekar` (
  `id_bibliotekar` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `korisnicko_ime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sifra` varchar(20) NOT NULL,
  PRIMARY KEY (`id_bibliotekar`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `bibliotekar_rs` */

DROP TABLE IF EXISTS `bibliotekar_rs`;

CREATE TABLE `bibliotekar_rs` (
  `id_bibliotekar` int NOT NULL,
  `datum_smene` date NOT NULL,
  `id_radna_smena` int NOT NULL,
  PRIMARY KEY (`id_bibliotekar`,`datum_smene`,`id_radna_smena`),
  KEY `fk_bibliotekar_rs_radna_smena` (`id_radna_smena`),
  CONSTRAINT `fk_bibliotekar_rs_bibliotekar` FOREIGN KEY (`id_bibliotekar`) REFERENCES `bibliotekar` (`id_bibliotekar`) ON UPDATE RESTRICT,
  CONSTRAINT `fk_bibliotekar_rs_radna_smena` FOREIGN KEY (`id_radna_smena`) REFERENCES `radna_smena` (`id_radna_smena`) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `citalac` */

DROP TABLE IF EXISTS `citalac`;

CREATE TABLE `citalac` (
  `id_citalac` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefon` varchar(15) NOT NULL,
  `id_kategorija` int NOT NULL,
  PRIMARY KEY (`id_citalac`),
  KEY `fk_citalac_kategorija` (`id_kategorija`),
  CONSTRAINT `fk_citalac_kategorija` FOREIGN KEY (`id_kategorija`) REFERENCES `kategorija_citaoca` (`id_kategorija`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `iznajmljivanje` */

DROP TABLE IF EXISTS `iznajmljivanje`;

CREATE TABLE `iznajmljivanje` (
  `id_iznajmljivanje` int NOT NULL AUTO_INCREMENT,
  `broj_knjiga` int NOT NULL,
  `datum_uzimanja` date NOT NULL,
  `ukupan_iznos` double NOT NULL,
  `valuta` varchar(5) NOT NULL,
  `id_bibliotekar` int NOT NULL,
  `id_citalac` int NOT NULL,
  PRIMARY KEY (`id_iznajmljivanje`),
  KEY `fk_iznajmljivanje_citalac` (`id_citalac`),
  KEY `fk_iznajmljivanje_bibliotekar` (`id_bibliotekar`),
  CONSTRAINT `fk_iznajmljivanje_bibliotekar` FOREIGN KEY (`id_bibliotekar`) REFERENCES `bibliotekar` (`id_bibliotekar`) ON UPDATE RESTRICT,
  CONSTRAINT `fk_iznajmljivanje_citalac` FOREIGN KEY (`id_citalac`) REFERENCES `citalac` (`id_citalac`) ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `kategorija_citaoca` */

DROP TABLE IF EXISTS `kategorija_citaoca`;

CREATE TABLE `kategorija_citaoca` (
  `id_kategorija` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(20) NOT NULL,
  `popust` int NOT NULL,
  PRIMARY KEY (`id_kategorija`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `knjiga` */

DROP TABLE IF EXISTS `knjiga`;

CREATE TABLE `knjiga` (
  `id_knjiga` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `autor` varchar(50) NOT NULL,
  `zanr` varchar(20) NOT NULL,
  `iznos_po_danu` double NOT NULL,
  `valuta` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id_knjiga`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `radna_smena` */

DROP TABLE IF EXISTS `radna_smena`;

CREATE TABLE `radna_smena` (
  `id_radna_smena` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(20) NOT NULL,
  `trajanje` int NOT NULL,
  PRIMARY KEY (`id_radna_smena`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `stavka_iznajmljivanja` */

DROP TABLE IF EXISTS `stavka_iznajmljivanja`;

CREATE TABLE `stavka_iznajmljivanja` (
  `id_iznajmljivanje` int NOT NULL,
  `rb` int NOT NULL,
  `datum_vracanja` date NOT NULL,
  `broj_dana` int NOT NULL,
  `iznos_po_danu` double NOT NULL,
  `iznos` double NOT NULL,
  `valuta` varchar(5) NOT NULL,
  `id_knjiga` int NOT NULL,
  PRIMARY KEY (`id_iznajmljivanje`,`rb`),
  KEY `rb` (`rb`),
  KEY `fk_stavka_knjiga` (`id_knjiga`),
  CONSTRAINT `fk_stavka_iznajmljivanje` FOREIGN KEY (`id_iznajmljivanje`) REFERENCES `iznajmljivanje` (`id_iznajmljivanje`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_stavka_knjiga` FOREIGN KEY (`id_knjiga`) REFERENCES `knjiga` (`id_knjiga`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
