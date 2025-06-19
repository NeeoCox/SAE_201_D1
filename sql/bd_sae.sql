CREATE DATABASE bd_sae;

USE bd_sae;
-----------------------------------------------------
--                CREATION DES TABLES              --
-----------------------------------------------------
SET SQL_SAFE_UPDATES = 0;
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
    
	CONSTRAINT pk_Besoin PRIMARY KEY (laCompetence, leDPS),
	CONSTRAINT fk_Besoin_DPS FOREIGN KEY (leDPS) REFERENCES DPS(id),
	CONSTRAINT fk_Besoin_Competence FOREIGN KEY (laCompetence) REFERENCES Competence(intitule)
);

CREATE TABLE Necessite (
    intituleCompetence VARCHAR(50),
    intituleCompetenceNecessaire VARCHAR(50),
        
    CONSTRAINT pk_Necessite PRIMARY KEY (intituleCompetence, intituleCompetenceNecessaire),
    CONSTRAINT fk_Necessite_Competence FOREIGN KEY (intituleCompetence) REFERENCES Competence(intitule),
    CONSTRAINT fk_Necessite_CompetenceNecessaire FOREIGN KEY (intituleCompetenceNecessaire) REFERENCES Competence(intitule)
);

DROP TABLE IF EXISTS Possede;

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

INSERT INTO Secouriste (id, nom, prenom, dateNaissance, email, telephone, adresse)
VALUES
(1, 'Dupont', 'Jean', '1990-05-12', 'jean.dupont@example.com', '0601020304', '12 rue de Paris, Lyon'),
(2, 'Martin', 'Claire', '1985-11-23', 'claire.martin@example.com', '0605060708', '8 avenue Victor Hugo, Marseille'),
(3, 'Nguyen', 'Linh', '1993-07-01', 'linh.nguyen@example.com', '0708091011', '5 boulevard Haussmann, Paris'),
(4, 'Moreau', 'Julien', '1992-03-17', 'julien.moreau@example.com', '0611223344', '15 chemin des Oliviers, Toulouse'),
(5, 'Lemoine', 'Sophie', '1988-09-30', 'sophie.lemoine@example.com', '0622334455', '33 rue des Lilas, Nantes'),
(6, 'Bernard', 'Lucie', '1991-01-20', 'lucie.bernard@example.com', '0633445566', '10 rue de la République, Nice'),
(7, 'Garcia', 'Thomas', '1995-08-14', 'thomas.garcia@example.com', '0644556677', '2 rue Lafayette, Bordeaux'),
(8, 'Roux', 'Camille', '1987-06-08', 'camille.roux@example.com', '0655667788', '48 avenue Jean Jaurès, Strasbourg'),
(9, 'Fournier', 'Alexandre', '1990-12-05', 'alex.fournier@example.com', '0666778899', '17 rue Nationale, Lille'),
(10, 'Faure', 'Julie', '1994-04-25', 'julie.faure@example.com', '0677889900', '3 impasse des Peupliers, Rennes'),
(11, 'Lopez', 'Hugo', '1992-10-19', 'hugo.lopez@example.com', '0688990011', '29 place Bellecour, Lyon'),
(12, 'Girard', 'Manon', '1986-03-09', 'manon.girard@example.com', '0699001122', '64 allée des Marronniers, Montpellier'),
(13, 'Andre', 'Nicolas', '1991-07-13', 'nicolas.andre@example.com', '0600112233', '11 rue de Bretagne, Dijon'),
(14, 'Mercier', 'Chloé', '1993-11-02', 'chloe.mercier@example.com', '0601223344', '20 chemin du Château, Clermont-Ferrand'),
(15, 'Blanc', 'Lucas', '1989-02-26', 'lucas.blanc@example.com', '0602334455', '35 avenue Foch, Grenoble'),
(16, 'Guerin', 'Emma', '1996-06-15', 'emma.guerin@example.com', '0603445566', '9 rue du Vercors, Reims'),
(17, 'Perrin', 'Matthieu', '1985-01-05', 'matthieu.perrin@example.com', '0604556677', '7 rue du Moulin, Le Havre'),
(18, 'Dupuis', 'Laura', '1992-09-18', 'laura.dupuis@example.com', '0605667788', '13 avenue de l’Europe, Saint-Étienne'),
(19, 'Meyer', 'Antoine', '1990-10-30', 'antoine.meyer@example.com', '0606778899', '4 rue des Acacias, Metz'),
(20, 'Leroy', 'Sarah', '1988-12-12', 'sarah.leroy@example.com', '0607889900', '23 boulevard Carnot, Amiens');

INSERT INTO Possede (leSecouriste, laCompetence)
VALUES
(1, 'PSE1'),
(1, 'SSA'),
(2, 'PSE2'),
(2, 'CE'),
(3, 'PSE1'),
(3, 'VPSP'),
(4, 'CE'),
(4, 'CP'),
(5, 'CO'),
(6, 'PBC'),
(7, 'PBF'),
(8, 'PSE1'),
(9, 'CP'),
(10, 'CO'),
(11, 'PSE2'),
(12, 'VPSP'),
(13, 'SSA'),
(14, 'PSE1'),
(15, 'PSE2'),
(16, 'CE'),
(17, 'CP'),
(18, 'CO'),
(19, 'PBC'),
(20, 'PBF');

-- Insertion des sports
INSERT INTO Sport (code, nom) VALUES 
('ATHL', 'Athlétisme'),
('NATA', 'Natation'),
('FOOT', 'Football'),
('BASK', 'Basketball'),
('CYCL', 'Cyclisme');

-- Insertion des sites
INSERT INTO Site (code, nom, longitude, latitude) VALUES
('ST1', 'Stade Olympique', 2.352222, 48.856614),
('ST2', 'Piscine Municipale', 2.295, 48.8738),
('ST3', 'Parc des Sports', 2.33, 48.85),
('ST4', 'Gymnase Central', 2.35, 48.86),
('ST5', 'Vélodrome', 2.34, 48.87);

-- Insertion des journées
INSERT INTO Journee (jour, mois, annee) VALUES
(15, 6, 2025),
(16, 6, 2025),
(17, 6, 2025),
(18, 6, 2025),
(19, 6, 2025);

-- Insertion des compétences
INSERT INTO Competence (intitule) VALUES
('Secouriste'),
('Infirmier'),
('Médecin'),
('Chef de poste'),
('Ambulancier');

-- Insertion des DPS
INSERT INTO DPS (id, horaire_depart, horaire_fin, concerneSport, aLieuDansSite, estProgrammeJour, estProgrammeMois, estProgrammeAnnee) VALUES
(1, 900, 1200, 'ATHL', 'ST1', 15, 6, 2025),
(2, 1300, 1600, 'NATA', 'ST2', 16, 6, 2025),
(3, 1000, 1300, 'FOOT', 'ST3', 17, 6, 2025),
(4, 1400, 1700, 'BASK', 'ST4', 18, 6, 2025),
(5, 900, 1100, 'CYCL', 'ST5', 19, 6, 2025);

-- Insertion des besoins de compétences pour chaque DPS
INSERT INTO Besoin (laCompetence, leDPS) VALUES
('Secouriste', 1),
('Infirmier', 1),
('Chef de poste', 1),

('Secouriste', 2),
('Médecin', 2),

('Ambulancier', 3),
('Chef de poste', 3),

('Secouriste', 4),
('Infirmier', 4),

('Médecin', 5),
('Ambulancier', 5);

SHOW TABLES;

SELECT * FROM Necessite;

DESCRIBE Necessite;
USE bd_sae;


DELETE FROM users;



-- Pour Jean Dupont (Secouriste id = 1)
INSERT INTO users (id, username, password_hash, role)
VALUES (1, 'Dupont', '$2a$10$exampleHashedPassword1', 'secouriste');

-- Pour Claire Martin (Secouriste id = 2)
INSERT INTO users (id, username, password_hash, role)
VALUES (2, 'Martin', '$2a$10$exampleHashedPassword2', 'secouriste');

-- Pour Linh Nguyen (Secouriste id = 3)
INSERT INTO users (id, username, password_hash, role)
VALUES (3, 'Nguyen', '$2a$10$exampleHashedPassword3', 'secouriste');

-- Pour Julien Moreau (Secouriste id = 4)
INSERT INTO users (id, username, password_hash, role)
VALUES (4, 'Moreau', '$2a$10$exampleHashedPassword4', 'secouriste');

-- Pour Sophie Lemoine (Secouriste id = 5)
INSERT INTO users (id, username, password_hash, role)
VALUES (5, 'Lemoine', '$2a$10$exampleHashedPassword5', 'secouriste');

INSERT INTO sessions (user_id, session_took, expires_at) VALUES
(1, 'sessiontoken123abc', NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR)),
(2, 'sessiontoken456def', NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR));



