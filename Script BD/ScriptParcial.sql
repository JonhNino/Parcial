-- Creación de las tablas

-- Tabla Persona
CREATE TABLE Persona (
    Id_Persona BIGINT NOT NULL PRIMARY KEY,
    Nombre_Persona VARCHAR(50) NOT NULL,
    Apellido_Persona VARCHAR(50) NOT NULL,
    Fecha_Nacimiento DATE NOT NULL
);

-- Tabla Patrocinador
CREATE TABLE Patrocinador (
    Id_Patrocinador BIGINT NOT NULL PRIMARY KEY,
    Nombre_Patrocinador VARCHAR(100) NOT NULL
);

-- Tabla Proyecto
CREATE TABLE Proyecto (
    Id_Proyecto BIGINT NOT NULL PRIMARY KEY,
    Fecha_Inicio DATE NOT NULL,
    Fecha_Fin DATE,
    Presupuesto DECIMAL(7,2) NOT NULL,
    Coordinador_Id BIGINT NOT NULL,
    Patrocinador_Id BIGINT NOT NULL,
    FOREIGN KEY (Coordinador_Id) REFERENCES Persona(Id_Persona),
    FOREIGN KEY (Patrocinador_Id) REFERENCES Patrocinador(Id_Patrocinador)
);

-- Tabla intermedia para la relación muchos a muchos entre Persona y Proyecto
CREATE TABLE Personas_Proyectos (
    Id_Persona BIGINT NOT NULL,
    Id_Proyecto BIGINT NOT NULL,
    PRIMARY KEY (Id_Persona, Id_Proyecto),
    FOREIGN KEY (Id_Persona) REFERENCES Persona(Id_Persona),
    FOREIGN KEY (Id_Proyecto) REFERENCES Proyecto(Id_Proyecto)
);

-- Creación de sequences para los IDs

-- Sequence para Id_Persona
CREATE SEQUENCE seq_id_persona START WITH 1 INCREMENT BY 1 MAXVALUE 100000;

-- Sequence para Id_Patrocinador
CREATE SEQUENCE seq_id_patrocinador START WITH 1 INCREMENT BY 1 MAXVALUE 100000;

-- Sequence para Id_Proyecto
CREATE SEQUENCE seq_id_proyecto START WITH 1 INCREMENT BY 1 MAXVALUE 100000;
