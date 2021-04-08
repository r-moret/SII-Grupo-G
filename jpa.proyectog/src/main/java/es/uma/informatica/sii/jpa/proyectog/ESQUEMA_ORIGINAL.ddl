-- Generado por Oracle SQL Developer Data Modeler 20.4.1.406.0906
--   en:        2021-04-08 22:01:29 CEST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE alumno (
    id                  NUMBER NOT NULL,
    dni                 VARCHAR2(20) NOT NULL,
    nombrecompleto      VARCHAR2(50) NOT NULL,
    emailinstitucional  VARCHAR2(50) NOT NULL,
    emailpersonal       VARCHAR2(50),
    telefono            NUMBER,
    movil               NUMBER
);

ALTER TABLE alumno ADD CONSTRAINT alumno_pk PRIMARY KEY ( id );

ALTER TABLE alumno ADD CONSTRAINT alumno_dni_un UNIQUE ( dni );

CREATE TABLE asignatura (
    referencia         NUMBER NOT NULL,
    codigo             NUMBER NOT NULL,
    creditos           NUMBER NOT NULL,
    ofertada           CHAR(1) NOT NULL,
    nombre             VARCHAR2(50) NOT NULL,
    curso              NUMBER,
    caracter           VARCHAR2(50),
    duración           NUMBER,
    cuatrimestre       VARCHAR2(50),
    idiomaimpartición  VARCHAR2(20),
    titulacion_codigo  NUMBER NOT NULL
);

ALTER TABLE asignatura ADD CONSTRAINT asignatura_pk PRIMARY KEY ( referencia );

CREATE TABLE asignaturaspormatriculas (
    asignatura_referencia               NUMBER NOT NULL,
    matricula_cursoacademico            VARCHAR2(20) NOT NULL, 
--  ERROR: Column name length exceeds maximum allowed length(30) 
    matricula_expediente_numexpediente  NUMBER NOT NULL,
    grupo_id                            VARCHAR2(20)
);

ALTER TABLE asignaturaspormatriculas
    ADD CONSTRAINT asignaturaspormatriculas_pk PRIMARY KEY ( asignatura_referencia,
                                                             matricula_cursoacademico,
                                                             matricula_expediente_numexpediente );

CREATE TABLE centro (
    id              NUMBER NOT NULL,
    nombre          VARCHAR2(50) NOT NULL,
    direccion       VARCHAR2(100) NOT NULL,
    tlfconserjería  NUMBER
);

ALTER TABLE centro ADD CONSTRAINT centro_pk PRIMARY KEY ( id );

ALTER TABLE centro ADD CONSTRAINT centro_nombre_un UNIQUE ( nombre );

CREATE TABLE "Centro-Titulación" (
    centro_id          NUMBER NOT NULL,
    titulacion_codigo  NUMBER NOT NULL
);

ALTER TABLE "Centro-Titulación" ADD CONSTRAINT "Centro-Titulación_PK" PRIMARY KEY ( centro_id,
                                                                                    titulacion_codigo );

CREATE TABLE clase (
    dia                    VARCHAR2(20) NOT NULL,
    horainicio             DATE NOT NULL,
    horafin                DATE,
    asignatura_referencia  NUMBER NOT NULL,
    grupo_id               VARCHAR2(20) NOT NULL
);

ALTER TABLE clase
    ADD CONSTRAINT clase_pk PRIMARY KEY ( dia,
                                          horainicio,
                                          grupo_id );

CREATE TABLE datosalumnado (
    dni                    VARCHAR2(20) NOT NULL,
    nombre                 VARCHAR2(50),
    apellido1              VARCHAR2(50),
    apellido2              VARCHAR2(50),
    numexpediente          NUMBER,
    emailinstitucional     VARCHAR2(50),
    emailpersonal          VARCHAR2(50),
    telefono               NUMBER,
    movil                  NUMBER,
    direccionnotificacion  VARCHAR2(100),
    localidadnotificacion  VARCHAR2(50),
    provincianotificacion  VARCHAR2(50),
    cpnotificacion         NUMBER,
    fechamatricula         DATE,
    turnopreferente        VARCHAR2(50),
    gruposasignados        VARCHAR2(50),
    notamedia              NUMBER,
    creditossuperados      NUMBER,
    creditosfb             NUMBER,
    creditosob             NUMBER,
    creditosop             NUMBER,
    creditoscf             NUMBER,
    creditospe             NUMBER,
    creditostf             NUMBER
);

ALTER TABLE datosalumnado ADD CONSTRAINT datosalumnado_pk PRIMARY KEY ( dni );

CREATE TABLE encuesta (
    fechaenvio                DATE NOT NULL,
    expediente_numexpediente  NUMBER NOT NULL
);

ALTER TABLE encuesta ADD CONSTRAINT encuesta_pk PRIMARY KEY ( fechaenvio );

CREATE TABLE "Encuesta-Grupos_por_Asignatura" (
    encuesta_fechaenvio                         DATE NOT NULL, 
--  ERROR: Column name length exceeds maximum allowed length(30) 
    gruposporasignaturas_cursoacademico         VARCHAR2(20) NOT NULL, 
--  ERROR: Column name length exceeds maximum allowed length(30) 
    gruposporasignaturas_asignatura_referencia  NUMBER NOT NULL,
    gruposporasignaturas_id                     VARCHAR2(20) NOT NULL
);

--  ERROR: PK name length exceeds maximum allowed length(30) 
ALTER TABLE "Encuesta-Grupos_por_Asignatura"
    ADD CONSTRAINT "Encuesta-Grupos_por_Asignatura_PK" PRIMARY KEY ( encuesta_fechaenvio,
                                                                     gruposporasignaturas_cursoacademico,
                                                                     gruposporasignaturas_asignatura_referencia,
                                                                     gruposporasignaturas_id );

CREATE TABLE expediente (
    numexpediente         NUMBER NOT NULL,
    activo                CHAR(1),
    notamediaprovisional  NUMBER,
    alumno_id             NUMBER NOT NULL,
    titulacion_codigo     NUMBER NOT NULL
);

ALTER TABLE expediente ADD CONSTRAINT expediente_pk PRIMARY KEY ( numexpediente );

CREATE TABLE grupo (
    id                 VARCHAR2(20) NOT NULL,
    curso              NUMBER NOT NULL,
    letra              VARCHAR2(10) NOT NULL,
    turno              VARCHAR2(20) NOT NULL,
    ingles             CHAR(1) NOT NULL,
    visible            CHAR(1),
    asignar            CHAR(1),
    plazas             NUMBER,
    titulacion_codigo  NUMBER NOT NULL,
    grupo_id           VARCHAR2(20)
);

ALTER TABLE grupo ADD CONSTRAINT grupo_pk PRIMARY KEY ( id );

ALTER TABLE grupo ADD CONSTRAINT grupo_curso_un UNIQUE ( curso );

ALTER TABLE grupo ADD CONSTRAINT grupo_letra_un UNIQUE ( letra );

CREATE TABLE gruposporasignaturas (
    cursoacademico         VARCHAR2(20) NOT NULL,
    oferta                 CHAR(1),
    asignatura_referencia  NUMBER NOT NULL,
    grupo_id               VARCHAR2(20) NOT NULL
);

ALTER TABLE gruposporasignaturas
    ADD CONSTRAINT gruposporasignaturas_pk PRIMARY KEY ( cursoacademico,
                                                         asignatura_referencia,
                                                         grupo_id );

CREATE TABLE matricula (
    cursoacademico            VARCHAR2(20) NOT NULL,
    estado                    CHAR(1) NOT NULL,
    numarchivo                NUMBER NOT NULL,
    turnopreferente           VARCHAR2(20) NOT NULL,
    fechamatrícula            DATE NOT NULL,
    nuevoingreso              CHAR(1),
    listadoasignaturas        VARCHAR2(500),
    expediente_numexpediente  NUMBER NOT NULL
);

ALTER TABLE matricula ADD CONSTRAINT matricula_pk PRIMARY KEY ( cursoacademico,
                                                                expediente_numexpediente );

CREATE TABLE optativa (
    referencia  NUMBER NOT NULL,
    plazas      NUMBER NOT NULL,
    mencion     VARCHAR2(50)
);

ALTER TABLE optativa ADD CONSTRAINT optativa_pk PRIMARY KEY ( referencia );

CREATE TABLE titulacion (
    codigo    NUMBER NOT NULL,
    nombre    VARCHAR2(50) NOT NULL,
    creditos  NUMBER NOT NULL
);

ALTER TABLE titulacion ADD CONSTRAINT titulacion_pk PRIMARY KEY ( codigo );

ALTER TABLE asignatura
    ADD CONSTRAINT asignatura_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE asignaturaspormatriculas
    ADD CONSTRAINT asignaturaspormatriculas_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE asignaturaspormatriculas
    ADD CONSTRAINT asignaturaspormatriculas_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE asignaturaspormatriculas
    ADD CONSTRAINT asignaturaspormatriculas_matricula_fk FOREIGN KEY ( matricula_cursoacademico,
                                                                       matricula_expediente_numexpediente )
        REFERENCES matricula ( cursoacademico,
                               expediente_numexpediente );

ALTER TABLE "Centro-Titulación"
    ADD CONSTRAINT "Centro-Titulación_CENTRO_FK" FOREIGN KEY ( centro_id )
        REFERENCES centro ( id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE "Centro-Titulación"
    ADD CONSTRAINT "Centro-Titulación_TITULACION_FK" FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo );

ALTER TABLE clase
    ADD CONSTRAINT clase_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE clase
    ADD CONSTRAINT clase_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE encuesta
    ADD CONSTRAINT encuesta_expediente_fk FOREIGN KEY ( expediente_numexpediente )
        REFERENCES expediente ( numexpediente );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE "Encuesta-Grupos_por_Asignatura"
    ADD CONSTRAINT "Encuesta-Grupos_por_Asignatura_ENCUESTA_FK" FOREIGN KEY ( encuesta_fechaenvio )
        REFERENCES encuesta ( fechaenvio );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE "Encuesta-Grupos_por_Asignatura"
    ADD CONSTRAINT "Encuesta-Grupos_por_Asignatura_GRUPOSPORASIGNATURAS_FK" FOREIGN KEY ( gruposporasignaturas_cursoacademico,
                                                                                          gruposporasignaturas_asignatura_referencia,
                                                                                          gruposporasignaturas_id )
        REFERENCES gruposporasignaturas ( cursoacademico,
                                          asignatura_referencia,
                                          grupo_id );

ALTER TABLE expediente
    ADD CONSTRAINT expediente_alumno_fk FOREIGN KEY ( alumno_id )
        REFERENCES alumno ( id );

ALTER TABLE expediente
    ADD CONSTRAINT expediente_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE gruposporasignaturas
    ADD CONSTRAINT gruposporasignaturas_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE gruposporasignaturas
    ADD CONSTRAINT gruposporasignaturas_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE matricula
    ADD CONSTRAINT matricula_expediente_fk FOREIGN KEY ( expediente_numexpediente )
        REFERENCES expediente ( numexpediente );

ALTER TABLE optativa
    ADD CONSTRAINT optativa_asignatura_fk FOREIGN KEY ( referencia )
        REFERENCES asignatura ( referencia );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            15
-- CREATE INDEX                             0
-- ALTER TABLE                             38
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                  11
-- WARNINGS                                 0
