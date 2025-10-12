-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `cuidador`
--

DROP TABLE IF EXISTS `cuidador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuidador` (
  `cedulaCuidador` varchar(20) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `edad` int DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `turno` varchar(45) DEFAULT NULL,
  `especialidad` varchar(45) NOT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cedulaCuidador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detallereserva`
--

DROP TABLE IF EXISTS `detallereserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallereserva` (
  `idReserva` int NOT NULL,
  `idservicio` int NOT NULL,
  `idHabitaculo` int NOT NULL,
  KEY `fk_detalle_reserva_reserva` (`idReserva`),
  KEY `fk_detalle_reserva_servicio` (`idservicio`),
  KEY `fk_detalle_reserva_habitaculo` (`idHabitaculo`),
  CONSTRAINT `fk_detalle_reserva_habitaculo` FOREIGN KEY (`idHabitaculo`) REFERENCES `habitaculo` (`idHabitaculo`),
  CONSTRAINT `fk_detalle_reserva_reserva` FOREIGN KEY (`idReserva`) REFERENCES `reserva` (`idReserva`),
  CONSTRAINT `fk_detalle_reserva_servicio` FOREIGN KEY (`idservicio`) REFERENCES `servicio` (`idservicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `idFactura` int NOT NULL,
  `fechaPago` datetime NOT NULL,
  `montoPagado` double NOT NULL,
  `metodoPago` varchar(45) NOT NULL,
  `idReserva` int NOT NULL,
  PRIMARY KEY (`idFactura`),
  KEY `fk_factura_reserva` (`idReserva`),
  CONSTRAINT `fk_factura_reserva` FOREIGN KEY (`idReserva`) REFERENCES `reserva` (`idReserva`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `habitaculo`
--

DROP TABLE IF EXISTS `habitaculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitaculo` (
  `idHabitaculo` int NOT NULL,
  `idservicio` int NOT NULL,
  PRIMARY KEY (`idHabitaculo`),
  KEY `fk_habitaculo_servicio` (`idservicio`),
  CONSTRAINT `fk_habitaculo_servicio` FOREIGN KEY (`idservicio`) REFERENCES `servicio` (`idservicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mascota`
--

DROP TABLE IF EXISTS `mascota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mascota` (
  `idMascota` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `raza` varchar(45) NOT NULL,
  `edad` varchar(45) NOT NULL,
  `peso` varchar(45) NOT NULL,
  `Propietario_cedula` varchar(20) NOT NULL,
  PRIMARY KEY (`idMascota`),
  KEY `fk_mascota_propietario` (`Propietario_cedula`),
  CONSTRAINT `fk_mascota_propietario` FOREIGN KEY (`Propietario_cedula`) REFERENCES `propietario` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `propietario`
--

DROP TABLE IF EXISTS `propietario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `propietario` (
  `cedula` varchar(20) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `idReserva` int NOT NULL,
  `fechainicio` datetime NOT NULL,
  `fecharfin` datetime DEFAULT NULL,
  `mascota_idMascota` int NOT NULL,
  `cuidador_cedulaCuidador` varchar(20) NOT NULL,
  PRIMARY KEY (`idReserva`),
  KEY `fk_reserva_mascota` (`mascota_idMascota`),
  KEY `fk_reserva_cuidador` (`cuidador_cedulaCuidador`),
  CONSTRAINT `fk_reserva_cuidador` FOREIGN KEY (`cuidador_cedulaCuidador`) REFERENCES `cuidador` (`cedulaCuidador`),
  CONSTRAINT `fk_reserva_mascota` FOREIGN KEY (`mascota_idMascota`) REFERENCES `mascota` (`idMascota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio` (
  `idservicio` int NOT NULL,
  `aforoMaximo` int NOT NULL,
  `idTipoServicio` int NOT NULL,
  `disponibles` int DEFAULT NULL,
  PRIMARY KEY (`idservicio`),
  KEY `fk_servicio_tipo` (`idTipoServicio`),
  CONSTRAINT `fk_servicio_tipo` FOREIGN KEY (`idTipoServicio`) REFERENCES `tipodeservicio` (`idTipoServicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servicios_solicitados`
--

DROP TABLE IF EXISTS `servicios_solicitados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios_solicitados` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_mascota` int NOT NULL,
  `id_servicio` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_mascota` (`id_mascota`),
  KEY `id_servicio` (`id_servicio`),
  CONSTRAINT `servicios_solicitados_ibfk_1` FOREIGN KEY (`id_mascota`) REFERENCES `mascota` (`idMascota`),
  CONSTRAINT `servicios_solicitados_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`idservicio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipodeservicio`
--

DROP TABLE IF EXISTS `tipodeservicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipodeservicio` (
  `idTipoServicio` int NOT NULL,
  `tipoServicio` varchar(45) NOT NULL,
  `costoUnidad` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoServicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-07  2:17:40
