USE bd_sae;
-----------------------------------------------------
--                CREATION DES TABLES              --
-----------------------------------------------------

DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS affectation;
DROP TABLE IF EXISTS secouriste_journee;
DROP TABLE IF EXISTS secouriste_competence;
DROP TABLE IF EXISTS Besoin;
DROP TABLE IF EXISTS Necessite;
DROP TABLE IF EXISTS DPS;
DROP TABLE IF EXISTS Journee;
DROP TABLE IF EXISTS Site;
DROP TABLE IF EXISTS Sport;
DROP TABLE IF EXISTS Competence;
DROP TABLE IF EXISTS Secoursite;


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
    
	CONSTRAINT pk_Besoin PRIMARY KEY (laCompetence, leDPS),
	CONSTRAINT fk_Besoin_DPS FOREIGN KEY (leDPS) REFERENCES DPS(id),
	CONSTRAINT fk_Besoin_Competence FOREIGN KEY (laCompetence) REFERENCES Competence(intitule)
);

CREATE TABLE Necessite (
    laCompetence VARCHAR(50),
    laCompetenceNecessaire VARCHAR(50),
        
    CONSTRAINT pk_Necessite PRIMARY KEY (laCompetence, laCompetenceNecessaire),
    CONSTRAINT fk_Necessite_Competence FOREIGN KEY (laCompetence) REFERENCES Competence(intitule),
    CONSTRAINT fk_Necessite_CompetenceNecessaire FOREIGN KEY (laCompetenceNecessaire) REFERENCES Competence(intitule)
);

CREATE TABLE Possede (
    leSecouriste INT,
    laCompetence VARCHAR(50),
    
    CONSTRAINT pk_Possede PRIMARY KEY (leSecouriste, laCompetence),
    CONSTRAINT fk_Possede_Secouriste FOREIGN KEY (leSecouriste) REFERENCES Secoursite(id),
    CONSTRAINT fk_Possede_Competence FOREIGN KEY (laCompetence) REFERENCES Competence(intitule)
);

CREATE TABLE EstDisponible (
    leSecouriste INT,
    jourJournee INT,
    moisJournee INT,
    anneeJournee INT,
    
	CONSTRAINT pk_EstDisponible PRIMARY KEY (leSecouriste, jourJournee, moisJournee, anneeJournee),
	CONSTRAINT fk_EstDisponible_Secouriste FOREIGN KEY (leSecouriste) REFERENCES Secoursite(id),
    
    CONSTRAINT fk_EstDisponible_Journee 
        FOREIGN KEY (jourJournee, moisJournee, anneeJournee)
        REFERENCES Journee(jour, mois, annee)
);

CREATE TABLE EstAffecteA (
    leSecouriste INT,
    leDPS INT,
    laCompetence VARCHAR(50),
    
    CONSTRAINT pk_EstAffecteA PRIMARY KEY (leSecouriste, leDPS, laCompetence),
    
    CONSTRAINT fk_EstAffecteA_Secouriste FOREIGN KEY (leSecouriste) REFERENCES Secoursite(id),
    CONSTRAINT fk_EstAffecteA_DPS FOREIGN KEY (leDPS) REFERENCES DPS(id),
    CONSTRAINT fk_EstAffecteA_Competence FOREIGN KEY (laCompetence) REFERENCES Competence(intitule)
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
('PSE1', 'PSE2'),
('PSE2', 'CE'),
('CE', 'CP'),
('CP', 'CO'),
('PSE1', 'SSA'),
('PSE2', 'VPSP'),
('PBC', 'PBF');

INSERT INTO Secoursite (id, nom, prenom, dateNaissance, email, telephone, adresse)
VALUES
(1, 'Dupont', 'Jean', '1990-05-12', 'jean.dupont@example.com', '0601020304', '12 rue de Paris, Lyon'),
(2, 'Martin', 'Claire', '1985-11-23', 'claire.martin@example.com', '0605060708', '8 avenue Victor Hugo, Marseille'),
(3, 'Nguyen', 'Linh', '1993-07-01', 'linh.nguyen@example.com', '0708091011', '5 boulevard Haussmann, Paris'),
(4, 'Moreau', 'Julien', '1992-03-17', 'julien.moreau@example.com', '0611223344', '15 chemin des Oliviers, Toulouse'),
(5, 'Lemoine', 'Sophie', '1988-09-30', 'sophie.lemoine@example.com', '0622334455', '33 rue des Lilas, Nantes');



