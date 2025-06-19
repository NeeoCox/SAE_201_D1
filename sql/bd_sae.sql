USE bd_sae;
-----------------------------------------------------
--                CREATION DES TABLES              --
-----------------------------------------------------
SET SQL_SAFE_UPDATES = 0;

DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS EstAffecteA;
DROP TABLE IF EXISTS EstDisponible;
DROP TABLE IF EXISTS Possede;
DROP TABLE IF EXISTS Necessite;
DROP TABLE IF EXISTS Besoin;
DROP TABLE IF EXISTS DPS;
DROP TABLE IF EXISTS Secouriste;
DROP TABLE IF EXISTS Journee;
DROP TABLE IF EXISTS Competence;
DROP TABLE IF EXISTS Sport;
DROP TABLE IF EXISTS Site;
DROP TABLE IF EXISTS users;



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

CREATE TABLE Secouriste (
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

CREATE TABLE Sport (
    code VARCHAR(10),
    nom VARCHAR(100),
    CONSTRAINT pk_Sport PRIMARY KEY (code)
);

CREATE TABLE Site (
    code VARCHAR(10),
    nom VARCHAR(100),
    longitude DECIMAL(9,6),
    latitude DECIMAL(9,6),
    CONSTRAINT pk_Site PRIMARY KEY (code)
);

CREATE TABLE DPS (
    id INT,
    horaire_depart INT NOT NULL,
    horaire_fin INT NOT NULL,
    concerneSport VARCHAR(10),
    aLieuDansSite VARCHAR(10),
    estProgrammeJour INT,
    estProgrammeMois INT,
    estProgrammeAnnee INT,
    
    CONSTRAINT pk_DPS PRIMARY KEY (id),
    CONSTRAINT uq_DPS_Horaire UNIQUE (horaire_depart, horaire_fin),

    CONSTRAINT fk_DPS_Sport FOREIGN KEY (concerneSport) REFERENCES Sport(code),
    CONSTRAINT fk_DPS_Site FOREIGN KEY (aLieuDansSite) REFERENCES Site(code),
    CONSTRAINT fk_DPS_Journee FOREIGN KEY (estProgrammeJour, estProgrammeMois, estProgrammeAnnee)
        REFERENCES Journee(jour, mois, annee)
);

CREATE TABLE Besoin (
    laCompetence VARCHAR(50) NOT NULL,
    leDPS INT,
    nombre INT NOT NULL DEFAULT 0,
    
	CONSTRAINT pk_Besoin PRIMARY KEY (laCompetence, leDPS),
	CONSTRAINT fk_Besoin_DPS FOREIGN KEY (leDPS) REFERENCES DPS(id),
	CONSTRAINT fk_Besoin_Competence FOREIGN KEY (laCompetence) REFERENCES Competence(intitule)
);

-- ALTER TABLE Besoin ADD nombre INT NOT NULL DEFAULT 0;

CREATE TABLE Necessite (
    intituleCompetence VARCHAR(50),
    intituleCompetenceNecessaire VARCHAR(50),
        
    CONSTRAINT pk_Necessite PRIMARY KEY (intituleCompetence, intituleCompetenceNecessaire),
    CONSTRAINT fk_Necessite_Competence FOREIGN KEY (intituleCompetence) REFERENCES Competence(intitule),
    CONSTRAINT fk_Necessite_CompetenceNecessaire FOREIGN KEY (intituleCompetenceNecessaire) REFERENCES Competence(intitule)
);



CREATE TABLE Possede (
    leSecouriste INT,
    laCompetence VARCHAR(50),
    
    CONSTRAINT pk_Possede PRIMARY KEY (leSecouriste, laCompetence),
    CONSTRAINT fk_Possede_Secouriste FOREIGN KEY (leSecouriste) REFERENCES Secouriste(id),
    CONSTRAINT fk_Possede_Competence FOREIGN KEY (laCompetence) REFERENCES Competence(intitule)
);


CREATE TABLE EstDisponible (
    leSecouriste INT,
    jourJournee INT,
    moisJournee INT,
    anneeJournee INT,
    
	CONSTRAINT pk_EstDisponible PRIMARY KEY (leSecouriste, jourJournee, moisJournee, anneeJournee),
	CONSTRAINT fk_EstDisponible_Secouriste FOREIGN KEY (leSecouriste) REFERENCES Secouriste(id),
    
    CONSTRAINT fk_EstDisponible_Journee 
        FOREIGN KEY (jourJournee, moisJournee, anneeJournee)
        REFERENCES Journee(jour, mois, annee)
);

CREATE TABLE EstAffecteA (
    leSecouriste INT,
    leDPS INT,
    laCompetence VARCHAR(50),
    
    CONSTRAINT pk_EstAffecteA PRIMARY KEY (leSecouriste, leDPS, laCompetence),
    
    CONSTRAINT fk_EstAffecteA_Secouriste FOREIGN KEY (leSecouriste) REFERENCES Secouriste(id),
    CONSTRAINT fk_EstAffecteA_DPS FOREIGN KEY (leDPS) REFERENCES DPS(id),
    CONSTRAINT fk_EstAffecteA_Competence FOREIGN KEY (laCompetence) REFERENCES Competence(intitule)
);


/*########################### Ininitalisation du graphe des compétences #############################*/



-- Insertion des Secouristes
INSERT INTO Secouriste (id, nom, prenom, dateNaissance, email, telephone, adresse) VALUES
(1, 'Dupont', 'Jean', '1985-04-12', 'jean.dupont@example.com', '0601020304', '10 rue de Paris'),
(2, 'Martin', 'Claire', '1990-08-25', 'claire.martin@example.com', '0605060708', '5 avenue Victor Hugo'),
(3, 'Durand', 'Pierre', '1982-01-30', 'pierre.durand@example.com', '0611223344', '12 boulevard Saint-Michel');

-- Insertion des Users avec username = nom des secouristes
INSERT INTO users (username, password_hash, role) VALUES
('Dupont', 'hashedpassword1', 'secouriste'),
('Martin', 'hashedpassword2', 'secouriste'),
('Durand', 'hashedpassword3', 'secouriste'),
('admin', 'admin', 'admin');

-- Exemple sessions
INSERT INTO sessions (user_id, session_took, expires_at) VALUES
(1, 'sessiontoken1', DATE_ADD(NOW(), INTERVAL 1 DAY)),
(2, 'sessiontoken2', DATE_ADD(NOW(), INTERVAL 1 DAY)),
(4, 'sessiontokenAdmin', DATE_ADD(NOW(), INTERVAL 1 DAY));

-- Insertion des journées
INSERT INTO Journee (jour, mois, annee) VALUES
(19, 6, 2025),
(20, 6, 2025),
(21, 6, 2025);

-- Insertion des compétences (tu as demandé de ne pas changer, je ne touche pas)
-- Supposons que Competence déjà inséré : PSE1, PSE2, SSA, VPSP, CE, CP, CO, PBC, PBF

-- Insertion des compétences
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

INSERT INTO Necessite (intituleCompetence, intituleCompetenceNecessaire) VALUES
('PSE1', 'PSE2'),
('PSE2', 'CE'),
('CE', 'CP'),
('CP', 'CO'),
('PSE1', 'SSA'),
('PSE2', 'VPSP'),
('PBC', 'PBF');

-- Insertion des sports
INSERT INTO Sport (code, nom) VALUES
('SP01', 'Football'),
('SP02', 'Basketball'),
('SP03', 'Natation');

-- Insertion des sites
INSERT INTO Site (code, nom, longitude, latitude) VALUES
('S01', 'Stade de France', 2.3600, 48.9245),
('S02', 'Parc des Princes', 2.2520, 48.8414);

-- Insertion des DPS (Dispositif de Premiers Secours)
INSERT INTO DPS (id, horaire_depart, horaire_fin, concerneSport, aLieuDansSite, estProgrammeJour, estProgrammeMois, estProgrammeAnnee) VALUES
(1, 800, 1200, 'SP01', 'S01', 19, 6, 2025),
(2, 1300, 1700, 'SP02', 'S02', 20, 6, 2025);

-- Insertion des besoins (Besoin)
-- Les compétences doivent exister, donc je mets des compétences valides selon Competence déjà inséré
INSERT INTO Besoin (laCompetence, leDPS, nombre) VALUES
('PSE1', 1, 3),
('PSE2', 1, 1),
('SSA', 2, 2);

-- Insertion des Possede (Compétences possédées par les secouristes)
INSERT INTO Possede (leSecouriste, laCompetence) VALUES
(1, 'PSE1'),
(1, 'SSA'),
(2, 'PSE2'),
(2, 'CE'),
(3, 'PSE1'),
(3, 'VPSP');

-- Insertion des disponibilités (EstDisponible)
INSERT INTO EstDisponible (leSecouriste, jourJournee, moisJournee, anneeJournee) VALUES
(1, 19, 6, 2025),
(1, 20, 6, 2025),
(2, 19, 6, 2025),
(3, 21, 6, 2025);

-- Insertion des affectations (EstAffecteA)
INSERT INTO EstAffecteA (leSecouriste, leDPS, laCompetence) VALUES
(1, 1, 'PSE1'),
(2, 1, 'PSE2'),
(3, 2, 'VPSP');


