USE TENNIS;

CREATE TABLE JOUEUR(
                       ID BIGINT NOT NULL AUTO_INCREMENT,
                       NOM VARCHAR(20) NOT NULL,
                       PRENOM VARCHAR(20),
                       SEXE CHAR(1) NOT NULL,/*H, F*/
                       PRIMARY KEY(ID)
) ENGINE=InnoDB;


CREATE TABLE TOURNOI(
                        ID BIGINT NOT NULL AUTO_INCREMENT,
                        NOM VARCHAR(20) NOT NULL,
                        CODE VARCHAR(2) NOT NULL,
                        PRIMARY KEY(ID)
) ENGINE=InnoDB;


CREATE TABLE EPREUVE(
                        ID BIGINT NOT NULL AUTO_INCREMENT,
                        ANNEE SMALLINT NOT NULL,
                        TYPE_EPREUVE CHAR(1) NOT NULL,/*H, F*/
                        ID_TOURNOI BIGINT NOT NULL,
                        PRIMARY KEY(ID),
                        FOREIGN KEY(ID_TOURNOI) REFERENCES TOURNOI(ID)
) ENGINE=InnoDB;


CREATE TABLE MATCH_TENNIS(
                             ID BIGINT NOT NULL AUTO_INCREMENT,
                             ID_EPREUVE BIGINT NOT NULL,
                             ID_VAINQUEUR BIGINT NOT NULL,
                             ID_FINALISTE BIGINT NOT NULL,
                             PRIMARY KEY(ID),
                             FOREIGN KEY(ID_VAINQUEUR) REFERENCES JOUEUR(ID),
                             FOREIGN KEY(ID_FINALISTE) REFERENCES JOUEUR(ID),
                             FOREIGN KEY(ID_EPREUVE) REFERENCES EPREUVE(ID)
) ENGINE=InnoDB;

CREATE TABLE SCORE_VAINQUEUR(
                                ID BIGINT NOT NULL AUTO_INCREMENT,
                                ID_MATCH BIGINT NOT NULL,
                                SET_1 TINYINT NULL,/*normalement NOT NULL sauf si abandon*/
                                SET_2 TINYINT NULL,/*normalement NOT NULL sauf si abandon*/
                                SET_3 TINYINT NULL,
                                SET_4 TINYINT NULL,
                                SET_5 TINYINT NULL,
                                PRIMARY KEY(ID),
                                FOREIGN KEY(ID_MATCH) REFERENCES MATCH_TENNIS(ID)
) ENGINE=InnoDB;