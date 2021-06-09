INSERT INTO Usuario VALUES (1, "admin", "jajaja");

INSERT INTO Titulacion VALUES (1, 6, "Informatica");
INSERT INTO Alumno VALUES (1, "77777777W", "email@1", "email@2", 22222222, "Alumno Nombre Completo", 33333333);
INSERT INTO Expediente VALUES (100, 0, 7.5, 1, 1);
INSERT INTO Matricula VALUES ("20/21", 0, TIMESTAMP("2020-09-22"), NULL, 1, 200, "tarde", 100);
INSERT INTO Matricula VALUES ("19/20", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("18/19", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("17/18", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("16/17", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("15/16", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("14/15", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("13/14", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("12/13", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("11/12", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("10/11", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("09/10", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("09/09", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("07/08", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("06/07", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("05/06", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("04/05", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("03/04", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("02/03", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("01/02", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("00/01", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("99/00", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("98/99", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("97/98", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);
INSERT INTO Matricula VALUES ("96/97", 0, TIMESTAMP("2019-08-30"), NULL, 1, 201, "tarde", 100);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/ALUMNO_DATA_TABLE.csv'
INTO TABLE Alumno
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(id, dni, emailinstitucional, emailPersonal, movil, nombreCompleto, telefono);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/ASIGNATURA_DATA_TABLE.csv'
INTO TABLE Asignatura
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(referencia, caracter, codigo, creditos, cuatrimestre, curso, duracion, idioma, nombre, ofertada, plazas, titulacion_codigo);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/ASIGNATURAS_MATRICULA_DATA_TABLE.csv'
INTO TABLE AsignaturasPorMatriculas
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(asignatura_referencia, matricula_cursoAcademico, matricula_expediente_numExpediente, grupo_id);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/CENTRO_DATA_TABLE.csv'
INTO TABLE Centro
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(id, direccion, nombre, tlfConsejeria);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/ALUMNO_EXT_DATA_TABLE.csv'
INTO TABLE DatosAlumnado
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(dni, apellido1, apellido2, cpNotificacion, creditosCF, creditosFB, creditosOP, creditosPE, creditosSuperados, creditosTF, direccionNotificacion, emailInstitucional, emailPersonal, fechaMatricula, gruposAsignados, localidadNotificacion, movil, nombre, notaMedia, numArchivo, numExpediente, provinciaNotificacion, telefono, turnoPreferente);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/EXPEDIENTES_DATA_TABLE.csv'
INTO TABLE Expediente
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(numExpediente, activo, notaMediaProvisional, alumno_id, titulacion_codigo);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/GRUPO_DATA_TABLE.csv'
INTO TABLE Grupo
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(id, asignar, curso, ingles, letra, plazas, turno, visible, titulacion_codigo);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/GRUPO_GRUPO_DATA_TABLE.csv'
INTO TABLE Grupo_Grupo
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(relacionadoCon_id, relacionados_id);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/GRUPOS_POR_ASIGNATURA_DATA_TABLE.csv'
INTO TABLE GruposPorAsignatura
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(cursoAcademico, ofertada, asignatura_referencia, grupo_id);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/MATRICULA_DATA_TABLE.csv'
INTO TABLE Matricula
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(cursoAcademico, estado, fechaMatricula, listadoAsignaturas, nuevoIngreso, numArchivo, turnoPreferente,expedient_numExpediente);

LOAD DATA INFILE '/home/alumno/Escritorio/TEIBOLS/TITULACION_DATA_TABLE.csv'
INTO TABLE Titulacion
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(codigo, creditos, nombre);







