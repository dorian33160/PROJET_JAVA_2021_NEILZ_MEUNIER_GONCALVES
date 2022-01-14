BEGIN TRANSACTION;

DROP TABLE IF EXISTS mesures;

CREATE TABLE mesures
    (idmes INTEGER PRIMARY KEY, -- alias du rowID
    idcap TEXT NOT NULL,
    dateheure TEXT NOT NULL,
    valeur FLOAT NOT NULL);

INSERT INTO mesures VALUES(1, '001', "2002-09-14 12:12:12.000", 15.1); -- syntaxe à respecter
INSERT INTO mesures VALUES(2, '002', "2002-09-15 13:10:12.030", 85.0);
INSERT INTO mesures VALUES(3, '001', "2021-08-05 05:57:10.000", 7.5);
INSERT INTO mesures VALUES(4, '003', "2021-12-25 00:00:00.000", 1013.25);
INSERT INTO mesures VALUES(5, '002', "2020-07-14 12:00:00.000", 32.0);

DROP TABLE IF EXISTS capteurs;

CREATE TABLE capteurs
    (idcap TEXT PRIMARY KEY,
    donnee TEXT NOT NULL);

INSERT INTO capteurs VALUES("001", "temperature"); -- syntaxe à respecter
INSERT INTO capteurs VALUES("002", "humidite");
INSERT INTO capteurs VALUES("003", "pression");

DROP TABLE IF EXISTS types;

CREATE TABLE types
    (donnee TEXT NOT NULL,
    unite TEXT NOT NULL);

INSERT INTO types VALUES("temperature", "°C");
INSERT INTO types VALUES("humidite", "%");
INSERT INTO types VALUES("pression", "hPa");

DROP TABLE IF EXISTS afficheurs;

CREATE TABLE afficheurs
    (idaff TEXT NOT NULL);

INSERT INTO afficheurs VALUES("01");
INSERT INTO afficheurs VALUES("02");

END TRANSACTION;