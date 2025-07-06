CREATE DATABASE IF NOT EXISTS guarderia_canina;
USE guarderia_canina;

-- Tabla de propietarios
CREATE TABLE Propietario (
  cedula VARCHAR(20) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  correo VARCHAR(45),
  telefono VARCHAR(20),
  PRIMARY KEY (cedula)
);

-- Tabla de cuidadores
CREATE TABLE Cuidador (
  cedulaCuidador VARCHAR(20) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  telefono VARCHAR(20),
  edad INT,
  turno VARCHAR(20),
  especialidad VARCHAR(45),
  cargo VARCHAR(45),
  PRIMARY KEY (cedulaCuidador)
);

-- Tabla de mascotas
CREATE TABLE Mascota (
  idMascota INT NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  raza VARCHAR(45),
  edad VARCHAR(10),
  peso VARCHAR(10),
  Propietario_cedula VARCHAR(20),
  PRIMARY KEY (idMascota),
  FOREIGN KEY (Propietario_cedula) REFERENCES Propietario(cedula)
);

-- Tabla de tipo de servicio
CREATE TABLE Tipo_Servicio (
  idTipoServicio INT NOT NULL AUTO_INCREMENT,
  tipoServicio VARCHAR(45) NOT NULL,
  costoUnidad DOUBLE,
  PRIMARY KEY (idTipoServicio)
);

-- Tabla de servicio
CREATE TABLE Servicio (
    idservicio INT NOT NULL AUTO_INCREMENT,
    aforoMaximo INT NOT NULL,
    disponibles INT,
    idTipoServicio INT,
    PRIMARY KEY (idservicio),
    FOREIGN KEY (idTipoServicio)
        REFERENCES Tipo_Servicio (idTipoServicio)
);

-- Tabla de habitáculos
CREATE TABLE Habitaculo (
  idHabitaculo INT NOT NULL AUTO_INCREMENT,
  idservicio INT NOT NULL,
  PRIMARY KEY (idHabitaculo),
  FOREIGN KEY (idservicio) REFERENCES Servicio(idservicio)
);

-- Tabla de reserva
CREATE TABLE Reserva (
  idReserva INT NOT NULL,
  fechainicio DATETIME NOT NULL,
  fecharfin DATETIME,
  idMascota INT NOT NULL,
  cedulaCuidador VARCHAR(20) NOT NULL,
  PRIMARY KEY (idReserva),
  FOREIGN KEY (idMascota) REFERENCES Mascota(idMascota),
  FOREIGN KEY (cedulaCuidador) REFERENCES Cuidador(cedulaCuidador)
);

-- Detalle de reserva (registro de qué servicio y habitáculo se usó en una reserva)
CREATE TABLE DetalleReserva (
  idReserva INT NOT NULL,
  idservicio INT NOT NULL,
  idHabitaculo INT NOT NULL,
  FOREIGN KEY (idReserva) REFERENCES Reserva(idReserva),
  FOREIGN KEY (idservicio) REFERENCES Servicio(idservicio),
  FOREIGN KEY (idHabitaculo) REFERENCES Habitaculo(idHabitaculo)
);

-- Factura
CREATE TABLE Factura (
  idFactura INT NOT NULL AUTO_INCREMENT,
  fechaEmision DATETIME NOT NULL,
  valorFacturado DOUBLE,
  metodoPago VARCHAR(45),
  idReserva INT NOT NULL,
  PRIMARY KEY (idFactura),
  FOREIGN KEY (idReserva) REFERENCES Reserva(idReserva)
);

-- Recaudo (pago realizado)
CREATE TABLE Recaudo (
  idRecaudo INT NOT NULL AUTO_INCREMENT,
  idFactura INT NOT NULL,
  montoPagado DOUBLE NOT NULL,
  fechaPago DATETIME NOT NULL,
  PRIMARY KEY (idRecaudo),
  FOREIGN KEY (idFactura) REFERENCES Factura(idFactura)
);