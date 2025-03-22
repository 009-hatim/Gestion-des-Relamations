# Gestion des Réclamations

## Contexte
La gestion des réclamations est un enjeu majeur pour les institutions académiques. Un suivi efficace des réclamations permet d'améliorer la communication entre les étudiants et l'administration, garantissant ainsi une meilleure prise en charge des demandes et une résolution rapide des problèmes. Un système manuel ou mal structuré peut entraîner des retards, un manque de transparence et une frustration des étudiants.

---

## Objectif
L'objectif de ce projet est de concevoir une application qui facilite la gestion des réclamations en permettant un suivi précis et efficace. L'application doit :

- *Permettre l'enregistrement et le suivi des réclamations des étudiants.*
- *Assurer un traitement clair et structuré des demandes.*
- *Offrir des outils de filtrage et de recherche pour une gestion optimisée.*
- *Fournir une visualisation graphique des réclamations traitées et non traitées.*

---

## Fonctionnalités Principales
###  *Gestion des Réclamations*
- Enregistrement des réclamations par les étudiants.

###  *Traitement des Réclamations*
- Mise à jour du statut et ajout de commentaires sur chaque réclamation.

###  *Filtrage des Réclamations*
- Filtrage par statut (traitée ou non traitée).

###  *Recherche de Réclamations*
- Recherche des réclamations par étudiant.

###  *Visualisation des Données*
- Graphique en *Pie Chart* pour analyser la répartition des réclamations traitées et non traitées.

```sql
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
CREATE TABLE User (
    login VARCHAR(50) PRIMARY KEY,
    password CHAR(32) NOT NULL
);
```
## *Diagrammes UML*
### Diagramme de Classe
![reclamation](https://github.com/user-attachments/assets/cf8da53b-1eea-4c2d-8090-d44ea7126498)

###  *Vidéo Démonstrative*
https://github.com/user-attachments/assets/c4509f34-b153-4678-9e5b-3c049f3e7649
