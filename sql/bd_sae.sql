USE bd_sae;
-----------------------------------------------------
--                CREATION DES TABLES              --
-----------------------------------------------------

DROP TABLES IF EXISTS sessions;
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



/*###################Création des table de base##########################*/
CREATE TABLE secouriste (
    id INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    dateNaissance DATE NOT NULL,
    email VARCHAR(50) NOT NULL,
    tel VARCHAR(20) NOT NULL,
    adresse VARCHAR(100) NOT NULL
);

CREATE TABLE journee (
    jour INT,
    mois INT,
    annee INT,
    PRIMARY KEY (jour, mois, annee)
);

CREATE TABLE competence (
    intitule VARCHAR(50) PRIMARY KEY
);

CREATE TABLE sport (
    code VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);

CREATE TABLE site (
    code VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    longitude FLOAT,
    latitude FLOAT
);

/*############################ Table dps relation vers site, sport et journee######################*/
CREATE TABLE dps (
    id INT PRIMARY KEY,
    horaire_depart INT,
    horaire_fin INT,
    jour INT,
    mois INT,
    annee INT,
    site_code VARCHAR(50),
    sport_code VARCHAR(50),
    FOREIGN KEY (jour, mois, annee) REFERENCES journee(jour, mois, annee),
    FOREIGN KEY (site_code) REFERENCES site(code),
    FOREIGN KEY (sport_code) REFERENCES sport(code)
);

/*###################### Table besoin relation à dps et competance####################*/
CREATE TABLE besoin (
    dps_id INT,
    competence_intitule VARCHAR(50),
    nombre INT NOT NULL,
    PRIMARY KEY (dps_id, competence_intitule),
    FOREIGN KEY (dps_id) REFERENCES dps(id),
    FOREIGN KEY (competence_intitule) REFERENCES competence(intitule)
);

/*###############Table associative########################*/

-- Compétences posséder par les secouriste
CREATE TABLE secouriste_competence (
    secouriste_id INT,
    competence_intitule VARCHAR(50),
    PRIMARY KEY (secouriste_id, competence_intitule),
    FOREIGN KEY (secouriste_id) REFERENCES secouriste(id),
    FOREIGN KEY (competence_intitule) REFERENCES competence(intitule)
);

-- Disponibiliter des secouriste pour une journée
CREATE TABLE secouriste_journee (
    secouriste_id INT,
    jour INT,
    mois INT,
    annee INT,
    PRIMARY KEY (secouriste_id, jour, mois, annee),
    FOREIGN KEY (secouriste_id) REFERENCES secouriste(id),
    FOREIGN KEY (jour, mois, annee) REFERENCES journee(jour, mois, annee)
);

-- Affectation des secouriste a un DPS
CREATE TABLE affectation (
    secouriste_id INT,
    dps_id INT,
    PRIMARY KEY (secouriste_id, dps_id),
    FOREIGN KEY (secouriste_id) REFERENCES secouriste(id),
    FOREIGN KEY (dps_id) REFERENCES dps(id)
);