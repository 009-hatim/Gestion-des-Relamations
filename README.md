# Gestion des Réclamations

## Contexte
La gestion des réclamations est un enjeu majeur pour les institutions académiques. Un suivi efficace des réclamations permet d'améliorer la communication entre les étudiants et l'administration, garantissant ainsi une meilleure prise en charge des demandes et une résolution rapide des problèmes. Un système manuel ou mal structuré peut entraîner des retards, un manque de transparence et une frustration des étudiants.

---
## Problématique
Les institutions académiques font face à des défis majeurs dans la gestion des réclamations des étudiants. Un système inefficace ou manuel peut entraîner des délais de traitement excessifs, un manque de transparence et une insatisfaction croissante des étudiants. En l'absence d'un outil structuré, l'administration peut rencontrer des difficultés à suivre l'évolution des réclamations, ce qui peut provoquer :
- *Une perte de temps due à un suivi désorganisé.*
- *Un manque de transparence dans le traitement des réclamations, entraînant une frustration des étudiants.*
- *Une difficulté à identifier les tendances et à améliorer les services en fonction des plaintes récurrentes.*

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

---
## Diagrammes UML
### *Diagramme de Classe*
![reclamation](https://github.com/user-attachments/assets/cf8da53b-1eea-4c2d-8090-d44ea7126498)
 
### *Diagramme de Cas d'Utilisation*
![1](https://github.com/user-attachments/assets/221883cc-14e9-434a-a7c7-f0f466eebc50)

---
## Requêtes sql pour la Base de Données
Tables
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
    question_securite VARCHAR(255),
    reponse_securite VARCHAR(255)
);
```
---
## Technologies Utilisées:
- **NetBeans 8.0.2** : IDE pour la programmation Java.
- **MySQL** : SGBD pour la gestion des réclamations et étudiants.
- **MySQL Connector/J** : Pilote JDBC pour l'interaction Java-MySQL.
- **Swing** : Bibliothèque pour les interfaces graphiques.
- **JCalendar** : Composant pour la gestion des dates.
- **JFreeChart** : Bibliothèque pour les graphiques (Pie Chart).
- **phpMyAdmin** : Interface web pour l'administration MySQL.
- **MagicDraw** : Outil de modélisation UML.
- **JDBC** : API pour la connexion à la base de données MySQL.
---
## Architecture du Projet
![Presentation Layer](https://github.com/user-attachments/assets/78ef851d-2bdc-4d32-ab79-63bc572ab1ac)
---
# Reclamations
## Structure du projet

```
Reclamations/
│
├── beans/                         # Modèles de données (JavaBeans)
│   ├── EStatut.java
│   ├── Etudiant.java
│   ├── Reclamation.java
│   ├── TraitementReclamation.java
│   ├── User.java
│
├── connexion/                     # Connexion à la base de données
│   ├── Connexion.java
│
├── dao/                           # Interfaces DAO
│   ├── IDao.java
│   ├── IUserDao.java
│
├── gui/                           # Interface utilisateur (Swing)
│   ├── EtudiantByStatut.java
│   ├── EtudiantForm.java
│   ├── Graph.java
│   ├── MDIApplication.java
│   ├── Main.java
│   ├── ReclamationForm.java
│   ├── SearchEtudiant.java
│   ├── TraitementReclamationForm.java
│
├── image/                         # Ressources images
│
├── lib/                           # Bibliothèques externes
│   ├── jcalendar-1.4.jar
│   ├── jcommon-1.0.23.jar
│   ├── jfreechart-1.0.19.jar
│
├── reclamations/                  # Gestion des réclamations
│
├── services/                      # Couche service (logique métier)
│   ├── EtudiantService.java
│   ├── ReclamationService.java
│   ├── TraitementReclamationService.java
│   ├── UserService.java
│
├── test/                          # Tests unitaires
│   ├── Test.java
│
├── utils/                         # Classes utilitaires
│   ├── SecurityUtil.java
│
└── README.md /                     # Documentation du projet
```




## Vidéo Démonstrative
https://github.com/user-attachments/assets/2339ef99-7780-4f0b-a208-501741471ac9
