DROP SCHEMA IF EXISTS leki ;

CREATE SCHEMA  leki ;
use  leki ;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS  leki ;
DROP TABLE IF EXISTS  pacjent ;
DROP TABLE IF EXISTS  skladniki ;
DROP TABLE IF EXISTS  efekt ;
DROP TABLE IF EXISTS kategoria ;
DROP TABLE IF EXISTS postac ;
DROP TABLE IF EXISTS specjalnosc ;
DROP TABLE IF EXISTS  leki_skladniki ;
DROP TABLE IF EXISTS  leki_efekt ;
DROP TABLE IF EXISTS  leki_specjalnosc ;
DROP TABLE IF EXISTS  leki_kategoria ;
DROP TABLE IF EXISTS  leki_postac ;
DROP TABLE IF EXISTS  pacjent_leki ;
DROP TABLE IF EXISTS  lekarz ;
DROP TABLE IF EXISTS  notatki ;
DROP TABLE IF EXISTS  pacjent_kod ;

CREATE TABLE  leki  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   nazwa  varchar(128) NOT NULL UNIQUE,
   informacje  text DEFAULT NULL,
   dawkowanie  varchar(45) DEFAULT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  pacjent  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   nazwa_uzytkownika  varchar(45) DEFAULT NULL,
   imie  varchar(45) DEFAULT NULL,
   nazwisko varchar(45) DEFAULT NULL,
   plec varchar(45) DEFAULT NULL,
   haslo  varchar(45) DEFAULT NULL,
   email  varchar(45) DEFAULT NULL,
   wiek  int(11) DEFAULT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  skladniki  (
  id int NOT NULL AUTO_INCREMENT,
  nazwa varchar(128) NOT NULL UNIQUE,
  PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  efekt  (
  id int NOT NULL AUTO_INCREMENT,
  nazwa varchar(128) NOT NULL UNIQUE,
  PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE specjalnosc(
id int NOT NULL AUTO_INCREMENT,
nazwa varchar(128) NOT NULL UNIQUE,
PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE kategoria(
id int NOT NULL AUTO_INCREMENT,
nazwa varchar(128) NOT NULL UNIQUE,
PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE postac(
id int NOT NULL AUTO_INCREMENT,
nazwa varchar(128) NOT NULL UNIQUE,
PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  leki_skladniki(
  leki_id int,
  skladniki_id int,
  PRIMARY KEY ( leki_id, skladniki_id ),
  FOREIGN KEY (leki_id) REFERENCES leki(id) ON DELETE CASCADE ON UPDATE NO ACTION,
  FOREIGN KEY (skladniki_id) REFERENCES skladniki(id) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  leki_efekt(
  leki_id int,
  efekt_id int,
  PRIMARY KEY ( leki_id, efekt_id ),
  FOREIGN KEY (leki_id) REFERENCES leki(id) ON DELETE CASCADE ON UPDATE NO ACTION,
  FOREIGN KEY (efekt_id) REFERENCES efekt(id) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  leki_specjalnosc(
  leki_id int,
  specjalnosc_id int,
  PRIMARY KEY ( leki_id, specjalnosc_id ),
  FOREIGN KEY (leki_id) REFERENCES leki(id) ON DELETE CASCADE ON UPDATE NO ACTION,
  FOREIGN KEY (specjalnosc_id) REFERENCES specjalnosc (id) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  leki_kategoria(
  leki_id int,
  kategoria_id int,
  PRIMARY KEY ( leki_id, kategoria_id ),
  FOREIGN KEY (leki_id) REFERENCES leki(id) ON DELETE CASCADE ON UPDATE NO ACTION,
  FOREIGN KEY (kategoria_id) REFERENCES kategoria (id) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  leki_postac(
  leki_id int,
  postac_id int,
  PRIMARY KEY (leki_id, postac_id ),
  FOREIGN KEY (leki_id) REFERENCES leki(id) ON DELETE CASCADE ON UPDATE NO ACTION,
  FOREIGN KEY (postac_id) REFERENCES postac(id) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE  pacjent_leki(
  leki_id int,
  pacjent_id int,
  PRIMARY KEY ( leki_id, pacjent_id ),
  FOREIGN KEY (leki_id) REFERENCES leki(id) ON DELETE CASCADE ON UPDATE NO ACTION,
  FOREIGN KEY (pacjent_id) REFERENCES pacjent (id) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  lekarz  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   nazwa_uzytkownika varchar(45) DEFAULT NULL,
   imie  varchar(45) DEFAULT NULL,
   nazwisko varchar(45) DEFAULT NULL,
   haslo  varchar(45) DEFAULT NULL,
   email  varchar(45) DEFAULT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  notatki  (
   id  int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
   data_stworzenia datetime DEFAULT CURRENT_TIMESTAMP,
   tytul TEXT NOT NULL,
   zawartosc MEDIUMTEXT NOT NULL,
   pacjent_id int(11) NOT NULL,
   autor_id int(11) NOT NULL,
   FOREIGN KEY(pacjent_id) REFERENCES pacjent(id) ON DELETE CASCADE ON UPDATE NO ACTION,
   FOREIGN KEY(autor_id) REFERENCES lekarz(id) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE  pacjent_kod  (
   pacjent_id int(11) NOT NULL,
   pacjent_kod int(11) UNIQUE NOT NULL,
   data_stworzenia DATETIME DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY(pacjent_id,pacjent_kod),
   FOREIGN KEY(pacjent_id) REFERENCES pacjent(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;