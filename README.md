CREATE TABLE Etudiant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Reclamation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    objet VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE TraitementReclamation (
    statut ENUM('TRAITEE', 'NON_TRAITEE') NOT NULL,
    commentaire TEXT NOT NULL,
    reclamation_id INT NOT NULL,
    etudiant_id INT NOT NULL,
    PRIMARY KEY (reclamation_id, etudiant_id),
    FOREIGN KEY (reclamation_id) REFERENCES Reclamation(id) ON DELETE CASCADE,
    FOREIGN KEY (etudiant_id) REFERENCES Etudiant(id) ON DELETE CASCADE
);
