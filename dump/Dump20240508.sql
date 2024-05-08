CREATE DATABASE  IF NOT EXISTS `react_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `react_app`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: react_app
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS categoria;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE categoria (
  ID_CA int NOT NULL AUTO_INCREMENT,
  Nome_Categoria enum('FrontEnd','BackEnd','FullStack','Cybersecurity') DEFAULT NULL,
  PRIMARY KEY (ID_CA)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES categoria WRITE;
/*!40000 ALTER TABLE categoria DISABLE KEYS */;
INSERT INTO categoria VALUES (1,'FrontEnd'),(2,'BackEnd'),(3,'FrontEnd');
/*!40000 ALTER TABLE categoria ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corso`
--

DROP TABLE IF EXISTS corso;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE corso (
  ID_C int NOT NULL AUTO_INCREMENT,
  Nome_Corso varchar(255) DEFAULT NULL,
  Descrizione_breve varchar(255) DEFAULT NULL,
  Descrizione_completa text,
  Durata int DEFAULT NULL,
  FK_CA int DEFAULT NULL,
  PRIMARY KEY (ID_C),
  KEY corso_ibfk_1_idx (FK_CA),
  CONSTRAINT corso_ibfk_1 FOREIGN KEY (FK_CA) REFERENCES categoria (ID_CA)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corso`
--

LOCK TABLES corso WRITE;
/*!40000 ALTER TABLE corso DISABLE KEYS */;
INSERT INTO corso VALUES (21,'Ingegneria Informatica','Corso di laurea in Ingegneria Informatica','Il corso si propone di formare professionisti in grado di progettare, sviluppare e gestire sistemi informatici complessi.',3000,1),(22,'Scienze dell\'Informazione','Corso di laurea in Scienze dell\'Informazione','Il corso offre una formazione approfondita nelle scienze dell\'informazione, con un focus su algoritmi, programmazione e architetture informatiche.',1800,1),(23,'Intelligenza Artificiale','Corso di laurea in Intelligenza Artificiale','Il corso si concentra sullo studio delle tecniche di intelligenza artificiale, incluse reti neurali, apprendimento automatico e robotica.',2400,1),(24,'Sicurezza Informatica','Corso di laurea in Sicurezza Informatica','Il corso prepara gli studenti ad affrontare le sfide della sicurezza informatica, con focus su crittografia, protezione dei dati e analisi forense.',2400,1),(25,'Informatica Applicata','Corso di laurea in Informatica Applicata','Il corso fornisce una formazione pratica nell\'applicazione delle tecnologie informatiche in settori come l\'ingegneria del software, l\'analisi dei dati e la simulazione.',1800,1),(26,'Giochi e Multimedia','Corso di laurea in Giochi e Multimedia','Il corso si concentra sulla progettazione e lo sviluppo di giochi e applicazioni multimediali, con un approccio interdisciplinare che include arte, design e programmazione.',2400,1),(27,'Tecnologie Web','Corso di laurea in Tecnologie Web','Il corso offre una formazione approfondita nello sviluppo di applicazioni web, comprese le tecnologie frontend e backend, i database e la sicurezza informatica.',1800,1),(28,'Cloud Computing','Corso di laurea in Cloud Computing','Il corso si concentra sullo studio delle tecnologie di cloud computing, incluse architetture, servizi, sicurezza e gestione delle risorse.',2400,1),(29,'Big Data','Corso di laurea in Big Data','Il corso fornisce competenze avanzate nella gestione e nell\'analisi di grandi quantità di dati, con focus su architetture distribuite, algoritmi di data mining e intelligenza aziendale.',2400,1),(30,'Programmazione Avanzata','Corso di laurea in Programmazione Avanzata','Il corso si concentra sullo sviluppo di competenze avanzate di programmazione, incluse metodologie, paradigmi e strumenti di sviluppo software.',1800,1);
/*!40000 ALTER TABLE corso ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruolo`
--

DROP TABLE IF EXISTS ruolo;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ruolo (
  ID_G int NOT NULL AUTO_INCREMENT,
  TIPOLOGIA enum('Admin','Utente','Docente') DEFAULT NULL,
  PRIMARY KEY (ID_G)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruolo`
--

LOCK TABLES ruolo WRITE;
/*!40000 ALTER TABLE ruolo DISABLE KEYS */;
INSERT INTO ruolo VALUES (1,'Admin'),(2,'Docente');
/*!40000 ALTER TABLE ruolo ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS utente;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE utente (
  ID_U int NOT NULL AUTO_INCREMENT,
  Nome varchar(255) DEFAULT NULL,
  Cognome varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID_U),
  UNIQUE KEY email_UNIQUE (email)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES utente WRITE;
/*!40000 ALTER TABLE utente DISABLE KEYS */;
INSERT INTO utente VALUES (7,'Simone','De Meis','Simone@icloud.com','b83cb68a006bf684628aaae5775e3794fe4d9ee57bb3d5c9173772b5cd4a4a94'),(8,'Lorenzo','Taverna','lorenzotaverna@icloud.com','b83cb68a006bf684628aaae5775e3794fe4d9ee57bb3d5c9173772b5cd4a4a94'),(9,NULL,NULL,NULL,'b8d9cafe7dcac9d27331d7ffa4ad2e8cd84364c8ec428440a362947164bc0610'),(11,'Francesca','Rossi','francesca.rossi@gmail.com','0e503b9b0316619a04506e808a0f3dc26136dced64b05887e75a85c0078188e7'),(24,'Umberto','Cangelosi','umberto.cangelosi@gmail.com','c1ba0c6f1a21c7e09f22efedffbce5706334a17da6e629e8b37b60dea31302ee'),(55,'Filippo','Rotolo','filippo.rotolo@gmail.com','9718a2dd29836d49f074323cfa8110fbe3be3dcecf6a7694521894b645c6df76'),(56,'Mario','Rossi','mario.rossi@gmail.com','0e503b9b0316619a04506e808a0f3dc26136dced64b05887e75a85c0078188e7'),(57,'Mario','Durante','mario.durante@gmail.com','71cbc8bdfbb537f46109f5887daaf9995b6795327c4f5c8a7b24112540d4da42'),(58,'Filippo','Lo Meo','filippo.lomeo@gmail.com','9f75227da851c8a8ab30a01161ee7da443bc0da6341997d4a3cb5eda1af1b96c');
/*!40000 ALTER TABLE utente ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente_ruolo`
--

DROP TABLE IF EXISTS utente_ruolo;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE utente_ruolo (
  FK_R int NOT NULL,
  FK_U int NOT NULL,
  PRIMARY KEY (FK_R,FK_U),
  KEY FK_U (FK_U),
  CONSTRAINT utente_ruolo_ibfk_1 FOREIGN KEY (FK_R) REFERENCES ruolo (ID_G) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT utente_ruolo_ibfk_2 FOREIGN KEY (FK_U) REFERENCES utente (ID_U) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente_ruolo`
--

LOCK TABLES utente_ruolo WRITE;
/*!40000 ALTER TABLE utente_ruolo DISABLE KEYS */;
INSERT INTO utente_ruolo VALUES (1,7),(1,8),(1,56);
/*!40000 ALTER TABLE utente_ruolo ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti_corsi`
--

DROP TABLE IF EXISTS utenti_corsi;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE utenti_corsi (
  FK_UC int NOT NULL,
  FK_CU int NOT NULL,
  PRIMARY KEY (FK_UC,FK_CU),
  KEY utenti_corsi_ibfk_2_idx (FK_CU),
  CONSTRAINT utenti_corsi_ibfk_1 FOREIGN KEY (FK_UC) REFERENCES utente (ID_U) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT utenti_corsi_ibfk_2 FOREIGN KEY (FK_CU) REFERENCES corso (ID_C)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti_corsi`
--

LOCK TABLES utenti_corsi WRITE;
/*!40000 ALTER TABLE utenti_corsi DISABLE KEYS */;
/*!40000 ALTER TABLE utenti_corsi ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-08 23:12:28
