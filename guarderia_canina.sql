DROP SCHEMA IF EXISTS `mydb`;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;

-- Tabla Propietario
CREATE TABLE IF NOT EXISTS `Propietario` (
  `cedula` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45),
  `telefono` VARCHAR(45),
  `correo` VARCHAR(45),
  PRIMARY KEY (`cedula`)
) ENGINE=InnoDB;

-- Tabla Cuidador
CREATE TABLE IF NOT EXISTS `cuidador` (
  `cedulaCuidador` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `edad` INT,
  `telefono` VARCHAR(45),
  `turno` VARCHAR(45),
  `especialidad` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45),
  PRIMARY KEY (`cedulaCuidador`)
) ENGINE=InnoDB;

-- Tabla Mascota (sin especie)
CREATE TABLE IF NOT EXISTS `mascota` (
  `idMascota` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `raza` VARCHAR(45) NOT NULL,
  `edad` VARCHAR(45) NOT NULL,
  `peso` VARCHAR(45) NOT NULL,
  `Propietario_cedula` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idMascota`),
  CONSTRAINT `fk_mascota_propietario`
    FOREIGN KEY (`Propietario_cedula`)
    REFERENCES `Propietario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla Reserva
CREATE TABLE IF NOT EXISTS `Reserva` (
  `idReserva` INT NOT NULL,
  `fechainicio` DATETIME NOT NULL,
  `fecharfin` DATETIME,
  `mascota_idMascota` INT NOT NULL,
  `cuidador_cedulaCuidador` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idReserva`),
  CONSTRAINT `fk_reserva_mascota`
    FOREIGN KEY (`mascota_idMascota`)
    REFERENCES `mascota` (`idMascota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_cuidador`
    FOREIGN KEY (`cuidador_cedulaCuidador`)
    REFERENCES `cuidador` (`cedulaCuidador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla Tipo de Servicio
CREATE TABLE IF NOT EXISTS `TipoDeServicio` (
  `idTipoServicio` INT NOT NULL,
  `tipoServicio` VARCHAR(45) NOT NULL,
  `costoUnidad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTipoServicio`)
) ENGINE=InnoDB;

-- Tabla Servicio
CREATE TABLE IF NOT EXISTS `servicio` (
  `idservicio` INT NOT NULL,
  `aforoMaximo` INT NOT NULL,
  `idTipoServicio` INT NOT NULL,
  `disponibles` INT,
  PRIMARY KEY (`idservicio`),
  CONSTRAINT `fk_servicio_tipo`
    FOREIGN KEY (`idTipoServicio`)
    REFERENCES `TipoDeServicio` (`idTipoServicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla Habitaculo
CREATE TABLE IF NOT EXISTS `Habitaculo` (
  `idHabitaculo` INT NOT NULL,
  `idservicio` INT NOT NULL,
  PRIMARY KEY (`idHabitaculo`),
  CONSTRAINT `fk_habitaculo_servicio`
    FOREIGN KEY (`idservicio`)
    REFERENCES `servicio` (`idservicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla Detalle de Reserva
CREATE TABLE IF NOT EXISTS `DetalleReserva` (
  `idReserva` INT NOT NULL,
  `idservicio` INT NOT NULL,
  `idHabitaculo` INT NOT NULL,
  CONSTRAINT `fk_detalle_reserva_reserva`
    FOREIGN KEY (`idReserva`)
    REFERENCES `Reserva` (`idReserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_reserva_servicio`
    FOREIGN KEY (`idservicio`)
    REFERENCES `servicio` (`idservicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_reserva_habitaculo`
    FOREIGN KEY (`idHabitaculo`)
    REFERENCES `Habitaculo` (`idHabitaculo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla Factura
CREATE TABLE IF NOT EXISTS `Factura` (
  `idFactura` INT NOT NULL,
  `fechaPago` DATETIME NOT NULL,
  `montoPagado` DOUBLE NOT NULL,
  `metodoPago` VARCHAR(45) NOT NULL,
  `idReserva` INT NOT NULL,
  PRIMARY KEY (`idFactura`),
  CONSTRAINT `fk_factura_reserva`
    FOREIGN KEY (`idReserva`)
    REFERENCES `Reserva` (`idReserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;
