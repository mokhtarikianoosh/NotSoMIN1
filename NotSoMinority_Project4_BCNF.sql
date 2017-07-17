-- MySQL dump 10.13  Distrib 5.7.15, for osx10.11 (x86_64)
--
-- Host: localhost    Database: Project4_1
-- ------------------------------------------------------
-- Server version	5.7.15

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
-- Table structure for table `Course1`
--

DROP TABLE IF EXISTS `Course1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course1` (
  `CNUM` int(11) NOT NULL,
  `CDESC` varchar(80) NOT NULL,
  `CDEPT` varchar(20) NOT NULL,
  PRIMARY KEY (`CNUM`),
  KEY `CDEPT` (`CDEPT`),
  CONSTRAINT `course1_ibfk_1` FOREIGN KEY (`CDEPT`) REFERENCES `Department` (`DEPTCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course1`
--

LOCK TABLES `Course1` WRITE;
/*!40000 ALTER TABLE `Course1` DISABLE KEYS */;
/*!40000 ALTER TABLE `Course1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course2`
--

DROP TABLE IF EXISTS `Course2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course2` (
  `CNUM` int(11) NOT NULL,
  `CNAME` varchar(20) NOT NULL,
  `CREDIT` int(11) NOT NULL,
  `LEVEL` int(11) NOT NULL,
  PRIMARY KEY (`CNUM`,`CNAME`),
  CONSTRAINT `course2_ibfk_1` FOREIGN KEY (`CNUM`) REFERENCES `Course1` (`CNUM`),
  CONSTRAINT `course2_ibfk_2` FOREIGN KEY (`CNUM`) REFERENCES `Course1` (`CNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course2`
--

LOCK TABLES `Course2` WRITE;
/*!40000 ALTER TABLE `Course2` DISABLE KEYS */;
/*!40000 ALTER TABLE `Course2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Department` (
  `DEPTNAME` varchar(20) NOT NULL,
  `DEPTCODE` varchar(20) NOT NULL,
  `DEPTOFFICE` int(11) NOT NULL,
  `DEPTPHONE` int(11) NOT NULL,
  `DEPTCOLLEGE` varchar(20) NOT NULL,
  PRIMARY KEY (`DEPTNAME`),
  UNIQUE KEY `DepartmentCode` (`DEPTCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Grade`
--

DROP TABLE IF EXISTS `Grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Grade` (
  `SSN` int(11) NOT NULL,
  `SECTION` varchar(20) NOT NULL,
  `GRADE` varchar(1) NOT NULL,
  PRIMARY KEY (`SSN`,`SECTION`,`GRADE`),
  CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`SSN`) REFERENCES `Student1` (`SSSN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Grade`
--

LOCK TABLES `Grade` WRITE;
/*!40000 ALTER TABLE `Grade` DISABLE KEYS */;
/*!40000 ALTER TABLE `Grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Section`
--

DROP TABLE IF EXISTS `Section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Section` (
  `INSTRUCTORNAME` varchar(20) NOT NULL,
  `SEMESTER` varchar(10) NOT NULL,
  `YEAR` int(11) NOT NULL,
  `SECCOURSE` varchar(20) NOT NULL,
  `SECNUM` int(11) NOT NULL,
  PRIMARY KEY (`SECCOURSE`,`SECNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Section`
--

LOCK TABLES `Section` WRITE;
/*!40000 ALTER TABLE `Section` DISABLE KEYS */;
/*!40000 ALTER TABLE `Section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student1`
--

DROP TABLE IF EXISTS `Student1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student1` (
  `SSSN` int(11) NOT NULL,
  `SNAME` varchar(80) NOT NULL,
  `SNUM` int(11) NOT NULL,
  `SPADDR` varchar(80) NOT NULL,
  `SPPHONE` int(11) NOT NULL,
  `BDATE` date NOT NULL,
  `SEX` varchar(1) NOT NULL,
  PRIMARY KEY (`SSSN`),
  UNIQUE KEY `SNUM` (`SNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student1`
--

LOCK TABLES `Student1` WRITE;
/*!40000 ALTER TABLE `Student1` DISABLE KEYS */;
/*!40000 ALTER TABLE `Student1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student2`
--

DROP TABLE IF EXISTS `Student2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student2` (
  `SNUM` int(11) NOT NULL,
  `SCADDR` varchar(80) NOT NULL,
  `SCPHONE` int(11) NOT NULL,
  `PROG` varchar(20) NOT NULL,
  `CLASS` varchar(20) NOT NULL,
  PRIMARY KEY (`SNUM`),
  UNIQUE KEY `PROG` (`PROG`),
  CONSTRAINT `student2_ibfk_1` FOREIGN KEY (`SNUM`) REFERENCES `Student1` (`SNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student2`
--

LOCK TABLES `Student2` WRITE;
/*!40000 ALTER TABLE `Student2` DISABLE KEYS */;
/*!40000 ALTER TABLE `Student2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student3`
--

DROP TABLE IF EXISTS `Student3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student3` (
  `PROG` varchar(20) NOT NULL,
  `MAJORDEPTCODE` varchar(20) NOT NULL,
  `MINORDEPTCODE` varchar(20) NOT NULL,
  PRIMARY KEY (`PROG`),
  CONSTRAINT `student3_ibfk_1` FOREIGN KEY (`PROG`) REFERENCES `Student2` (`PROG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student3`
--

LOCK TABLES `Student3` WRITE;
/*!40000 ALTER TABLE `Student3` DISABLE KEYS */;
/*!40000 ALTER TABLE `Student3` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-16 20:33:24
