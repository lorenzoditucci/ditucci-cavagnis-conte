CREATE DATABASE  IF NOT EXISTS `traveldreamdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `traveldreamdb`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: traveldreamdb
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Acquista`
--

DROP TABLE IF EXISTS `Acquista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Acquista` (
  `mail` varchar(255) NOT NULL,
  `idPacchetto` int(11) NOT NULL,
  PRIMARY KEY (`mail`,`idPacchetto`),
  KEY `FK_Acquista_idPacchetto` (`idPacchetto`),
  CONSTRAINT `FK_Acquista_mail` FOREIGN KEY (`mail`) REFERENCES `USERS` (`EMAIL`),
  CONSTRAINT `FK_Acquista_idPacchetto` FOREIGN KEY (`idPacchetto`) REFERENCES `Pacchetto` (`IDPACCHETTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Acquista`
--

LOCK TABLES `Acquista` WRITE;
/*!40000 ALTER TABLE `Acquista` DISABLE KEYS */;
/*!40000 ALTER TABLE `Acquista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Acquisti`
--

DROP TABLE IF EXISTS `Acquisti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Acquisti` (
  `IDACQUISTA` int(11) NOT NULL,
  `DATAACQUISTO` datetime DEFAULT NULL,
  `PACCHETTO_IDPACCHETTO` int(11) DEFAULT NULL,
  `USER_EMAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDACQUISTA`),
  UNIQUE KEY `IDACQUISTA` (`IDACQUISTA`),
  KEY `FK_Acquisti_USER_EMAIL` (`USER_EMAIL`),
  KEY `FK_Acquisti_PACCHETTO_IDPACCHETTO` (`PACCHETTO_IDPACCHETTO`),
  CONSTRAINT `FK_Acquisti_PACCHETTO_IDPACCHETTO` FOREIGN KEY (`PACCHETTO_IDPACCHETTO`) REFERENCES `Pacchetto` (`IDPACCHETTO`),
  CONSTRAINT `FK_Acquisti_USER_EMAIL` FOREIGN KEY (`USER_EMAIL`) REFERENCES `USERS` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Acquisti`
--

LOCK TABLES `Acquisti` WRITE;
/*!40000 ALTER TABLE `Acquisti` DISABLE KEYS */;
/*!40000 ALTER TABLE `Acquisti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Citta`
--

DROP TABLE IF EXISTS `Citta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Citta` (
  `IDCITTA` int(11) NOT NULL,
  `NOME` varchar(45) NOT NULL,
  PRIMARY KEY (`IDCITTA`),
  UNIQUE KEY `IDCITTA` (`IDCITTA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Citta`
--

LOCK TABLES `Citta` WRITE;
/*!40000 ALTER TABLE `Citta` DISABLE KEYS */;
INSERT INTO `Citta` VALUES (2,'roma'),(3,'milano'),(11,'firenze'),(12,'bologna');
/*!40000 ALTER TABLE `Citta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contiene`
--

DROP TABLE IF EXISTS `Contiene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Contiene` (
  `idGiftList` int(11) NOT NULL,
  `idPacchetto` int(11) NOT NULL,
  PRIMARY KEY (`idGiftList`,`idPacchetto`),
  KEY `FK_Contiene_idPacchetto` (`idPacchetto`),
  CONSTRAINT `FK_Contiene_idPacchetto` FOREIGN KEY (`idPacchetto`) REFERENCES `Pacchetto` (`IDPACCHETTO`),
  CONSTRAINT `FK_Contiene_idGiftList` FOREIGN KEY (`idGiftList`) REFERENCES `GiftList` (`IDGIFTLIST`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contiene`
--

LOCK TABLES `Contiene` WRITE;
/*!40000 ALTER TABLE `Contiene` DISABLE KEYS */;
/*!40000 ALTER TABLE `Contiene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Destinazione`
--

DROP TABLE IF EXISTS `Destinazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Destinazione` (
  `idCitta` int(11) NOT NULL,
  `idPacchetto` int(11) NOT NULL,
  PRIMARY KEY (`idCitta`,`idPacchetto`),
  KEY `FK_Destinazione_idPacchetto` (`idPacchetto`),
  CONSTRAINT `FK_Destinazione_idPacchetto` FOREIGN KEY (`idPacchetto`) REFERENCES `Pacchetto` (`IDPACCHETTO`),
  CONSTRAINT `FK_Destinazione_idCitta` FOREIGN KEY (`idCitta`) REFERENCES `Citta` (`IDCITTA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Destinazione`
--

LOCK TABLES `Destinazione` WRITE;
/*!40000 ALTER TABLE `Destinazione` DISABLE KEYS */;
INSERT INTO `Destinazione` VALUES (2,8),(3,8),(11,15),(12,15);
/*!40000 ALTER TABLE `Destinazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Escursione`
--

DROP TABLE IF EXISTS `Escursione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Escursione` (
  `IDESCURSIONE` int(11) NOT NULL,
  `ACQUISTATO` tinyint(4) NOT NULL,
  `CITTA` varchar(45) NOT NULL,
  `COSTO` double NOT NULL,
  `DATAFINE` datetime NOT NULL,
  `DATAINIZIO` datetime NOT NULL,
  `DESCRIZIONE` varchar(45) NOT NULL,
  `NOME` varchar(45) NOT NULL,
  PRIMARY KEY (`IDESCURSIONE`),
  UNIQUE KEY `IDESCURSIONE` (`IDESCURSIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Escursione`
--

LOCK TABLES `Escursione` WRITE;
/*!40000 ALTER TABLE `Escursione` DISABLE KEYS */;
INSERT INTO `Escursione` VALUES (7,0,'roma',25,'2014-02-12 23:00:00','2014-02-12 18:00:00','visita in serata al colosseo','Colosseo by night');
/*!40000 ALTER TABLE `Escursione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Escursione_EscursioniAcquistate`
--

DROP TABLE IF EXISTS `Escursione_EscursioniAcquistate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Escursione_EscursioniAcquistate` (
  `Escursione_IDESCURSIONE` int(11) NOT NULL,
  `giftLists_IDESCURSIONIACQUISTATE` int(11) NOT NULL,
  PRIMARY KEY (`Escursione_IDESCURSIONE`,`giftLists_IDESCURSIONIACQUISTATE`),
  KEY `scrsnscrsioniAcquistategftLstsDSCURSIONIACQUISTATE` (`giftLists_IDESCURSIONIACQUISTATE`),
  CONSTRAINT `scursioneEscursioniAcquistatescursioneIDESCURSIONE` FOREIGN KEY (`Escursione_IDESCURSIONE`) REFERENCES `Escursione` (`IDESCURSIONE`),
  CONSTRAINT `scrsnscrsioniAcquistategftLstsDSCURSIONIACQUISTATE` FOREIGN KEY (`giftLists_IDESCURSIONIACQUISTATE`) REFERENCES `EscursioniAcquistate` (`IDESCURSIONIACQUISTATE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Escursione_EscursioniAcquistate`
--

LOCK TABLES `Escursione_EscursioniAcquistate` WRITE;
/*!40000 ALTER TABLE `Escursione_EscursioniAcquistate` DISABLE KEYS */;
/*!40000 ALTER TABLE `Escursione_EscursioniAcquistate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EscursioniAcquistate`
--

DROP TABLE IF EXISTS `EscursioniAcquistate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EscursioniAcquistate` (
  `IDESCURSIONIACQUISTATE` int(11) NOT NULL,
  `DATAACQUISTO` datetime NOT NULL,
  `IDESCURSIONE` int(11) NOT NULL,
  `IDGIFTLIST` int(11) NOT NULL,
  `NOMEACQUIRENTE` varchar(45) NOT NULL,
  PRIMARY KEY (`IDESCURSIONIACQUISTATE`),
  UNIQUE KEY `IDESCURSIONIACQUISTATE` (`IDESCURSIONIACQUISTATE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EscursioniAcquistate`
--

LOCK TABLES `EscursioniAcquistate` WRITE;
/*!40000 ALTER TABLE `EscursioniAcquistate` DISABLE KEYS */;
/*!40000 ALTER TABLE `EscursioniAcquistate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EscursioniPacchetto`
--

DROP TABLE IF EXISTS `EscursioniPacchetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EscursioniPacchetto` (
  `idEscursione` int(11) NOT NULL,
  `idPacchetto` int(11) NOT NULL,
  PRIMARY KEY (`idEscursione`,`idPacchetto`),
  KEY `FK_EscursioniPacchetto_idPacchetto` (`idPacchetto`),
  CONSTRAINT `FK_EscursioniPacchetto_idEscursione` FOREIGN KEY (`idEscursione`) REFERENCES `Escursione` (`IDESCURSIONE`),
  CONSTRAINT `FK_EscursioniPacchetto_idPacchetto` FOREIGN KEY (`idPacchetto`) REFERENCES `Pacchetto` (`IDPACCHETTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EscursioniPacchetto`
--

LOCK TABLES `EscursioniPacchetto` WRITE;
/*!40000 ALTER TABLE `EscursioniPacchetto` DISABLE KEYS */;
INSERT INTO `EscursioniPacchetto` VALUES (7,8);
/*!40000 ALTER TABLE `EscursioniPacchetto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GiftList`
--

DROP TABLE IF EXISTS `GiftList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GiftList` (
  `IDGIFTLIST` int(11) NOT NULL,
  `MAILCLIENTE` varchar(45) NOT NULL,
  `NOME` varchar(45) NOT NULL,
  PRIMARY KEY (`IDGIFTLIST`),
  UNIQUE KEY `IDGIFTLIST` (`IDGIFTLIST`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GiftList`
--

LOCK TABLES `GiftList` WRITE;
/*!40000 ALTER TABLE `GiftList` DISABLE KEYS */;
/*!40000 ALTER TABLE `GiftList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GiftList_EscursioniAcquistate`
--

DROP TABLE IF EXISTS `GiftList_EscursioniAcquistate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GiftList_EscursioniAcquistate` (
  `GiftList_IDGIFTLIST` int(11) NOT NULL,
  `escursioni_IDESCURSIONIACQUISTATE` int(11) NOT NULL,
  PRIMARY KEY (`GiftList_IDGIFTLIST`,`escursioni_IDESCURSIONIACQUISTATE`),
  KEY `GftLstscrsioniAcquistatescrsnDESCURSIONIACQUISTATE` (`escursioni_IDESCURSIONIACQUISTATE`),
  CONSTRAINT `GiftList_EscursioniAcquistate_GiftList_IDGIFTLIST` FOREIGN KEY (`GiftList_IDGIFTLIST`) REFERENCES `GiftList` (`IDGIFTLIST`),
  CONSTRAINT `GftLstscrsioniAcquistatescrsnDESCURSIONIACQUISTATE` FOREIGN KEY (`escursioni_IDESCURSIONIACQUISTATE`) REFERENCES `EscursioniAcquistate` (`IDESCURSIONIACQUISTATE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GiftList_EscursioniAcquistate`
--

LOCK TABLES `GiftList_EscursioniAcquistate` WRITE;
/*!40000 ALTER TABLE `GiftList_EscursioniAcquistate` DISABLE KEYS */;
/*!40000 ALTER TABLE `GiftList_EscursioniAcquistate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GiftList_PernottamentiAcquistati`
--

DROP TABLE IF EXISTS `GiftList_PernottamentiAcquistati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GiftList_PernottamentiAcquistati` (
  `GiftList_IDGIFTLIST` int(11) NOT NULL,
  `pernottamenti_IDPERNOTTAMENTOACQUISTATO` int(11) NOT NULL,
  PRIMARY KEY (`GiftList_IDGIFTLIST`,`pernottamenti_IDPERNOTTAMENTOACQUISTATO`),
  KEY `GftLstPrnttmntcqistatiprnttmntDPRNTTMNTOACQUISTATO` (`pernottamenti_IDPERNOTTAMENTOACQUISTATO`),
  CONSTRAINT `GftLstPrnttmntcqistatiprnttmntDPRNTTMNTOACQUISTATO` FOREIGN KEY (`pernottamenti_IDPERNOTTAMENTOACQUISTATO`) REFERENCES `PernottamentiAcquistati` (`IDPERNOTTAMENTOACQUISTATO`),
  CONSTRAINT `GiftListPernottamentiAcquistatiGiftList_IDGIFTLIST` FOREIGN KEY (`GiftList_IDGIFTLIST`) REFERENCES `GiftList` (`IDGIFTLIST`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GiftList_PernottamentiAcquistati`
--

LOCK TABLES `GiftList_PernottamentiAcquistati` WRITE;
/*!40000 ALTER TABLE `GiftList_PernottamentiAcquistati` DISABLE KEYS */;
/*!40000 ALTER TABLE `GiftList_PernottamentiAcquistati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GiftList_VoliAcquistati`
--

DROP TABLE IF EXISTS `GiftList_VoliAcquistati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GiftList_VoliAcquistati` (
  `GiftList_IDGIFTLIST` int(11) NOT NULL,
  `voli_IDVOLOACQUISTATO` int(11) NOT NULL,
  PRIMARY KEY (`GiftList_IDGIFTLIST`,`voli_IDVOLOACQUISTATO`),
  KEY `FK_GiftList_VoliAcquistati_voli_IDVOLOACQUISTATO` (`voli_IDVOLOACQUISTATO`),
  CONSTRAINT `FK_GiftList_VoliAcquistati_voli_IDVOLOACQUISTATO` FOREIGN KEY (`voli_IDVOLOACQUISTATO`) REFERENCES `VoliAcquistati` (`IDVOLOACQUISTATO`),
  CONSTRAINT `FK_GiftList_VoliAcquistati_GiftList_IDGIFTLIST` FOREIGN KEY (`GiftList_IDGIFTLIST`) REFERENCES `GiftList` (`IDGIFTLIST`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GiftList_VoliAcquistati`
--

LOCK TABLES `GiftList_VoliAcquistati` WRITE;
/*!40000 ALTER TABLE `GiftList_VoliAcquistati` DISABLE KEYS */;
/*!40000 ALTER TABLE `GiftList_VoliAcquistati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hotel`
--

DROP TABLE IF EXISTS `Hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hotel` (
  `IDHOTEL` int(11) NOT NULL,
  `ACQUISTATO` tinyint(4) NOT NULL,
  `CITTA` varchar(45) NOT NULL,
  `CLASSE` int(11) NOT NULL,
  `COSTO` double NOT NULL,
  `DESCRIZIONE` varchar(45) NOT NULL,
  `INDIRIZZO` varchar(45) NOT NULL,
  `NOME` varchar(45) NOT NULL,
  PRIMARY KEY (`IDHOTEL`),
  UNIQUE KEY `IDHOTEL` (`IDHOTEL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hotel`
--

LOCK TABLES `Hotel` WRITE;
/*!40000 ALTER TABLE `Hotel` DISABLE KEYS */;
INSERT INTO `Hotel` VALUES (6,0,'roma',3,58,'Comodita\' & Cordialita\'','via appia 237','El Burino Hotel'),(14,0,'firenze',5,80,'Lussuria a due passi dal centro','via pinturicchio 75','Hotel Panbacecio');
/*!40000 ALTER TABLE `Hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pacchetto`
--

DROP TABLE IF EXISTS `Pacchetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pacchetto` (
  `IDPACCHETTO` int(11) NOT NULL,
  `COSTO` double NOT NULL,
  `DATAFINE` datetime NOT NULL,
  `DATAINIZIO` datetime NOT NULL,
  `DESCRIZIONE` varchar(45) NOT NULL,
  `DISPONIBILITAATTUALE` int(11) NOT NULL,
  `DISPONIBILITAMAX` int(11) NOT NULL,
  `MAIL` varchar(45) NOT NULL,
  `NOME` varchar(45) NOT NULL,
  PRIMARY KEY (`IDPACCHETTO`),
  UNIQUE KEY `IDPACCHETTO` (`IDPACCHETTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pacchetto`
--

LOCK TABLES `Pacchetto` WRITE;
/*!40000 ALTER TABLE `Pacchetto` DISABLE KEYS */;
INSERT INTO `Pacchetto` VALUES (8,558.3,'2014-02-17 00:00:00','2014-02-10 00:00:00','una settimana nella capitale',356,356,'employee@employee.it','Settimana a Roma'),(15,357,'2014-03-04 00:00:00','2014-03-01 00:00:00','Spendi 4 giorni a mangiare fiorentina!',159,159,'employee@employee.it','4 giorni a Firenze');
/*!40000 ALTER TABLE `Pacchetto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pacchetto_Pernottamento`
--

DROP TABLE IF EXISTS `Pacchetto_Pernottamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pacchetto_Pernottamento` (
  `Pacchetto_IDPACCHETTO` int(11) NOT NULL,
  `pernottiList_IDPERNOTTAMETTO` int(11) NOT NULL,
  PRIMARY KEY (`Pacchetto_IDPACCHETTO`,`pernottiList_IDPERNOTTAMETTO`),
  KEY `PacchettoPernottamentopernottiList_IDPERNOTTAMETTO` (`pernottiList_IDPERNOTTAMETTO`),
  CONSTRAINT `PacchettoPernottamentopernottiList_IDPERNOTTAMETTO` FOREIGN KEY (`pernottiList_IDPERNOTTAMETTO`) REFERENCES `Pernottamento` (`IDPERNOTTAMETTO`),
  CONSTRAINT `FK_Pacchetto_Pernottamento_Pacchetto_IDPACCHETTO` FOREIGN KEY (`Pacchetto_IDPACCHETTO`) REFERENCES `Pacchetto` (`IDPACCHETTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pacchetto_Pernottamento`
--

LOCK TABLES `Pacchetto_Pernottamento` WRITE;
/*!40000 ALTER TABLE `Pacchetto_Pernottamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pacchetto_Pernottamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PernottamentiAcquistati`
--

DROP TABLE IF EXISTS `PernottamentiAcquistati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PernottamentiAcquistati` (
  `IDPERNOTTAMENTOACQUISTATO` int(11) NOT NULL,
  `DATAACQUISTO` datetime NOT NULL,
  `IDGIFTLIST` int(11) NOT NULL,
  `IDPERNOTTAMENTO` int(11) NOT NULL,
  `NOMEACQUIRENTE` varchar(45) NOT NULL,
  PRIMARY KEY (`IDPERNOTTAMENTOACQUISTATO`),
  UNIQUE KEY `IDPERNOTTAMENTOACQUISTATO` (`IDPERNOTTAMENTOACQUISTATO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PernottamentiAcquistati`
--

LOCK TABLES `PernottamentiAcquistati` WRITE;
/*!40000 ALTER TABLE `PernottamentiAcquistati` DISABLE KEYS */;
/*!40000 ALTER TABLE `PernottamentiAcquistati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pernottamento`
--

DROP TABLE IF EXISTS `Pernottamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pernottamento` (
  `IDPERNOTTAMETTO` int(11) NOT NULL,
  `DATAFINE` datetime NOT NULL,
  `DATAINIZIO` datetime NOT NULL,
  `HOTEL_IDHOTEL` int(11) DEFAULT NULL,
  `PACCHETTO_IDPACCHETTO` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDPERNOTTAMETTO`),
  UNIQUE KEY `IDPERNOTTAMETTO` (`IDPERNOTTAMETTO`),
  KEY `FK_Pernottamento_HOTEL_IDHOTEL` (`HOTEL_IDHOTEL`),
  KEY `FK_Pernottamento_PACCHETTO_IDPACCHETTO` (`PACCHETTO_IDPACCHETTO`),
  CONSTRAINT `FK_Pernottamento_PACCHETTO_IDPACCHETTO` FOREIGN KEY (`PACCHETTO_IDPACCHETTO`) REFERENCES `Pacchetto` (`IDPACCHETTO`),
  CONSTRAINT `FK_Pernottamento_HOTEL_IDHOTEL` FOREIGN KEY (`HOTEL_IDHOTEL`) REFERENCES `Hotel` (`IDHOTEL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pernottamento`
--

LOCK TABLES `Pernottamento` WRITE;
/*!40000 ALTER TABLE `Pernottamento` DISABLE KEYS */;
INSERT INTO `Pernottamento` VALUES (9,'2014-02-17 00:00:00','2014-02-10 00:00:00',6,8),(16,'2014-03-04 00:00:00','2014-03-01 00:00:00',14,15);
/*!40000 ALTER TABLE `Pernottamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pernottamento_PernottamentiAcquistati`
--

DROP TABLE IF EXISTS `Pernottamento_PernottamentiAcquistati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pernottamento_PernottamentiAcquistati` (
  `Pernottamento_IDPERNOTTAMETTO` int(11) NOT NULL,
  `giftLists_IDPERNOTTAMENTOACQUISTATO` int(11) NOT NULL,
  PRIMARY KEY (`Pernottamento_IDPERNOTTAMETTO`,`giftLists_IDPERNOTTAMENTOACQUISTATO`),
  KEY `PrnttmntPrnttmntcquistatigftLstsDPRNTTMNTCQUISTATO` (`giftLists_IDPERNOTTAMENTOACQUISTATO`),
  CONSTRAINT `PrnttmntPrnttmntcquistatigftLstsDPRNTTMNTCQUISTATO` FOREIGN KEY (`giftLists_IDPERNOTTAMENTOACQUISTATO`) REFERENCES `PernottamentiAcquistati` (`IDPERNOTTAMENTOACQUISTATO`),
  CONSTRAINT `PrnttmntPrnttamentiAcquistatiPrnttmntDPRNOTTAMETTO` FOREIGN KEY (`Pernottamento_IDPERNOTTAMETTO`) REFERENCES `Pernottamento` (`IDPERNOTTAMETTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pernottamento_PernottamentiAcquistati`
--

LOCK TABLES `Pernottamento_PernottamentiAcquistati` WRITE;
/*!40000 ALTER TABLE `Pernottamento_PernottamentiAcquistati` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pernottamento_PernottamentiAcquistati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEQUENCE`
--

DROP TABLE IF EXISTS `SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SEQUENCE` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEQUENCE`
--

LOCK TABLES `SEQUENCE` WRITE;
/*!40000 ALTER TABLE `SEQUENCE` DISABLE KEYS */;
INSERT INTO `SEQUENCE` VALUES ('SEQ_GEN',50);
/*!40000 ALTER TABLE `SEQUENCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `EMAIL` varchar(255) NOT NULL,
  `dtype` varchar(20) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `REGISTEREDON` datetime DEFAULT NULL,
  `MATRICOLA` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES ('cliente@cliente.it','user','via Parrucchetti, Milano','Mario','Rossi','4983a0ab83ed86e0e7213c8783940193','2013-12-25 23:47:12',NULL),('employee@employee.it','impiegato','NULL','employee','employee','fa5473530e4d1a5a1e1eb53d2fedb10c','2013-12-25 23:47:12',123456);
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS_GROUPS`
--

DROP TABLE IF EXISTS `USERS_GROUPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS_GROUPS` (
  `email` varchar(255) DEFAULT NULL,
  `groupname` varchar(255) DEFAULT NULL,
  KEY `FK_USERS_GROUPS_email` (`email`),
  CONSTRAINT `FK_USERS_GROUPS_email` FOREIGN KEY (`email`) REFERENCES `USERS` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS_GROUPS`
--

LOCK TABLES `USERS_GROUPS` WRITE;
/*!40000 ALTER TABLE `USERS_GROUPS` DISABLE KEYS */;
INSERT INTO `USERS_GROUPS` VALUES ('cliente@cliente.it','USER'),('employee@employee.it','EMPLOYEE');
/*!40000 ALTER TABLE `USERS_GROUPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VoliAcquistati`
--

DROP TABLE IF EXISTS `VoliAcquistati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VoliAcquistati` (
  `IDVOLOACQUISTATO` int(11) NOT NULL,
  `DATAACQUISTO` datetime NOT NULL,
  `IDGIFTLIST` int(11) NOT NULL,
  `IDVOLO` int(11) NOT NULL,
  `NOMEACQUIRENTE` varchar(45) NOT NULL,
  PRIMARY KEY (`IDVOLOACQUISTATO`),
  UNIQUE KEY `IDVOLOACQUISTATO` (`IDVOLOACQUISTATO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VoliAcquistati`
--

LOCK TABLES `VoliAcquistati` WRITE;
/*!40000 ALTER TABLE `VoliAcquistati` DISABLE KEYS */;
/*!40000 ALTER TABLE `VoliAcquistati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VoliPacchetto`
--

DROP TABLE IF EXISTS `VoliPacchetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VoliPacchetto` (
  `idPacchetto` int(11) NOT NULL,
  `idVolo` int(11) NOT NULL,
  PRIMARY KEY (`idPacchetto`,`idVolo`),
  KEY `FK_VoliPacchetto_idVolo` (`idVolo`),
  CONSTRAINT `FK_VoliPacchetto_idPacchetto` FOREIGN KEY (`idPacchetto`) REFERENCES `Pacchetto` (`IDPACCHETTO`),
  CONSTRAINT `FK_VoliPacchetto_idVolo` FOREIGN KEY (`idVolo`) REFERENCES `Volo` (`IDVOLO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VoliPacchetto`
--

LOCK TABLES `VoliPacchetto` WRITE;
/*!40000 ALTER TABLE `VoliPacchetto` DISABLE KEYS */;
INSERT INTO `VoliPacchetto` VALUES (8,1),(8,4),(15,10),(15,13);
/*!40000 ALTER TABLE `VoliPacchetto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Volo`
--

DROP TABLE IF EXISTS `Volo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Volo` (
  `IDVOLO` int(11) NOT NULL,
  `ACQUISTATO` int(11) NOT NULL,
  `CITTAARRIVO` varchar(45) NOT NULL,
  `CITTAPARTENZA` varchar(45) NOT NULL,
  `COMPAGNIA` varchar(45) NOT NULL,
  `COSTO` double NOT NULL,
  `DATAFINE` datetime NOT NULL,
  `DATAINIZIO` datetime NOT NULL,
  PRIMARY KEY (`IDVOLO`),
  UNIQUE KEY `IDVOLO` (`IDVOLO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Volo`
--

LOCK TABLES `Volo` WRITE;
/*!40000 ALTER TABLE `Volo` DISABLE KEYS */;
INSERT INTO `Volo` VALUES (1,0,'roma','milano','Alitalia',74.3,'2014-02-10 05:00:00','2014-02-10 00:00:00'),(4,0,'milano','roma','Easyjet',53,'2014-02-17 05:00:00','2014-02-17 00:00:00'),(10,0,'firenze','bologna','Ryanair',48,'2014-03-01 09:00:00','2014-03-01 06:00:00'),(13,0,'bologna','firenze','Easyjet',69,'2014-03-04 11:00:00','2014-03-04 07:29:00');
/*!40000 ALTER TABLE `Volo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Volo_VoliAcquistati`
--

DROP TABLE IF EXISTS `Volo_VoliAcquistati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Volo_VoliAcquistati` (
  `Volo_IDVOLO` int(11) NOT NULL,
  `giftLists_IDVOLOACQUISTATO` int(11) NOT NULL,
  PRIMARY KEY (`Volo_IDVOLO`,`giftLists_IDVOLOACQUISTATO`),
  KEY `FK_Volo_VoliAcquistati_giftLists_IDVOLOACQUISTATO` (`giftLists_IDVOLOACQUISTATO`),
  CONSTRAINT `FK_Volo_VoliAcquistati_giftLists_IDVOLOACQUISTATO` FOREIGN KEY (`giftLists_IDVOLOACQUISTATO`) REFERENCES `VoliAcquistati` (`IDVOLOACQUISTATO`),
  CONSTRAINT `FK_Volo_VoliAcquistati_Volo_IDVOLO` FOREIGN KEY (`Volo_IDVOLO`) REFERENCES `Volo` (`IDVOLO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Volo_VoliAcquistati`
--

LOCK TABLES `Volo_VoliAcquistati` WRITE;
/*!40000 ALTER TABLE `Volo_VoliAcquistati` DISABLE KEYS */;
/*!40000 ALTER TABLE `Volo_VoliAcquistati` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-30 23:09:43
