USE bd_sae;
-----------------------------------------------------
--                CREATION DES TABLES              --
-----------------------------------------------------

DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS affectation;
DROP TABLE IF EXISTS secouriste_journee;
DROP TABLE IF EXISTS secouriste_competence;
DROP TABLE IF EXISTS besoin;
DROP TABLE IF EXISTS dps;
DROP TABLE IF EXISTS journee;
DROP TABLE IF EXISTS site;
DROP TABLE IF EXISTS sport;
DROP TABLE IF EXISTS competence;
DROP TABLE IF EXISTS secouriste;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    role ENUM('admin', 'secouriste') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE sessions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    session_took VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);



/*###################Création des tables de base##########################*/

CREATE TABLE Secoursite (
    id INT,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    dateNaissance VARCHAR(10),
    email VARCHAR(100),
    telephone VARCHAR(20),
    adresse VARCHAR(100),
    CONSTRAINT pk_Secouriste PRIMARY KEY (id)
);

CREATE TABLE Journee (
    jour INT,
    mois INT,
    annee INT,
    CONSTRAINT pk_Journee PRIMARY KEY (jour, mois, annee)
);

CREATE TABLE Competence (
    intitule VARCHAR(50),
    CONSTRAINT pk_Competence PRIMARY KEY (intitule)
);

CREATE TABLE DPS (
    id INT,
    horaire_depart int NOT NULL,
    horaire_fin int NOT NULL,
    concerneSport VARCHAR(10)
        CONSTRAINT fk_DPS_Sport REFERENCES Sport(code),
    aLieuDansSite VARCHAR(10)
        CONSTRAINT fk_DPS_Site REFERENCES Site(code),
    estProgrammeJour INT,
    estProgrammeMois INT,
    estProgrammeAnnee INT,
    CONSTRAINT pk_DPS PRIMARY KEY (id),
    CONSTRAINT uq_DPS_Horaire UNIQUE (horaire_depart, horaire_fin),
    CONSTRAINT fk_DPS_Journee FOREIGN KEY (estProgrammeJour, estProgrammeMois, estProgrammeAnnee) REFERENCES Journee(jour, mois, annee)
);

CREATE TABLE Site (
    code VARCHAR(10),
    nom VARCHAR(100),
    longitude DECIMAL(9,6),
    latitude DECIMAL(9,6),
    CONSTRAINT pk_Site PRIMARY KEY (code)
);

CREATE TABLE Sport (
    code VARCHAR(10),
    nom VARCHAR(100),
    CONSTRAINT pk_Sport PRIMARY KEY (code)
);



CREATE TABLE Besoin (
    laCompetence VARCHAR(50) NOT NULL
        CONSTRAINT fk_Besoin_Competence REFERENCES Competence(intitule),
    leDPS INT
        CONSTRAINT fk_Besoin_DPS REFERENCES DPS(id),
    CONSTRAINT pk_Besoin PRIMARY KEY (laCompetence, leDPS)
);

CREATE TABLE Necessite (
    laCompetence VARCHAR(50)
        CONSTRAINT fk_Necessite_Competence REFERENCES Competence(intitule),
    laCompetenceNecessaire VARCHAR(50)
        CONSTRAINT fk_Necessite_CompetenceNecessaire REFERENCES Competence(intitule),
    CONSTRAINT pk_Necessite PRIMARY KEY (laCompetence, laCompetenceNecessaire)
);

CREATE TABLE Possede (
    leSecouriste INT
        CONSTRAINT fk_Possede_Secouriste REFERENCES Secoursite(id),
    laCompetence VARCHAR(50)
        CONSTRAINT fk_Possede_Competence REFERENCES Competence(intitule),
    CONSTRAINT pk_Possede PRIMARY KEY (leSecouriste, laCompetence)
);

CREATE TABLE EstDisponible (
    leSecouriste INT
        CONSTRAINT fk_EstDisponible_Secouriste REFERENCES Secoursite(id),
    laJournee INT
        CONSTRAINT fk_EstDisponible_Journee REFERENCES Journee(jour, mois, annee),
    CONSTRAINT pk_EstDisponible PRIMARY KEY (leSecouriste, laJournee)
);

CREATE TABLE EstAffecteA (
    leSecouriste INT
        CONSTRAINT fk_EstAffecteA_Secouriste REFERENCES Secoursite(id),
    leDPS INT
        CONSTRAINT fk_EstAffecteA_DPS REFERENCES DPS(id),
    laCompetence VARCHAR(50)
        CONSTRAINT fk_EstAffecteA_Competence REFERENCES Competence(intitule),
    CONSTRAINT pk_EstAffecteA PRIMARY KEY (leSecouriste, leDPS, laCompetence)
);



/*########################### Ininitalisation du graphe des compétences #############################*/

INSERT INTO Competence (intitule) VALUES
('PSE1'),
('PSE2'),
('SSA'),
('VPSP'),
('CE'),
('CP'),
('CO'),
('PBC'),
('PBF');

INSERT INTO Necessite (laCompetence, laCompetenceNecessaire) VALUES
('PSE2', 'PSE1'),
('CE', 'PSE2'),
('CP', 'CE'),
('CO', 'CP'),
('SSA', 'PSE2'),
('VPSP', 'PSE2'),
('PBF', 'PBC');