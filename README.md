# 📌 Gestion des Réclamations  

## 📌 Contexte  
Dans les institutions académiques, la gestion des réclamations des étudiants est un enjeu crucial. Un système efficace garantit une meilleure communication entre les étudiants et l’administration, permettant ainsi un traitement rapide et transparent des demandes. À l’inverse, un processus manuel ou mal structuré entraîne des retards, un manque de transparence et une frustration des étudiants.  

---

## 📌 Problématique  
Les établissements d'enseignement rencontrent plusieurs défis liés à la gestion des réclamations des étudiants. Un système inefficace peut engendrer :  
- ⏳ **Des délais de traitement excessifs**, causés par un suivi désorganisé.  
- ❌ **Un manque de transparence**, entraînant une frustration croissante des étudiants.  
- 📉 **Des difficultés d’analyse des tendances**, limitant l’amélioration des services en fonction des plaintes récurrentes.  

---

## 📌 Objectif  
Ce projet vise à concevoir une application facilitant la gestion des réclamations en offrant un suivi structuré et efficace. L’application doit :  
✅ **Permettre l’enregistrement et le suivi des réclamations des étudiants.**  
✅ **Assurer un traitement clair et organisé des demandes.**  
✅ **Offrir des outils de recherche et de filtrage pour une gestion optimisée.**  
✅ **Fournir une visualisation graphique de la répartition des réclamations traitées et non traitées.**  

---

## 📌 Fonctionnalités  

### 📍 **Gestion des Réclamations**  
✔️ Enregistrement des réclamations par les étudiants.  

### 📍 **Traitement des Réclamations**  
✔️ Mise à jour du statut et ajout de commentaires sur chaque réclamation.  

### 📍 **Filtrage des Réclamations**  
✔️ Filtrage par statut (traitée ou non traitée).  

### 📍 **Recherche des Réclamations**  
✔️ Recherche des réclamations par étudiant.  

---

## 📌 Diagrammes UML  
### 🎯 **Diagramme de Classe**  
![ges-recl](https://github.com/user-attachments/assets/f5ae2ed5-ba1c-438f-bd96-f599365923b5)


### 🎯 **Diagramme de Cas d’Utilisation**  
![1](https://github.com/user-attachments/assets/221883cc-14e9-434a-a7c7-f0f466eebc50)  

---

## 📌 Base de Données  

### 🗄️ **Modélisation des Tables**  
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
    FOREIGN KEY (etudiant_id) REFERENCES Etudiant(id) ON DELETE CASCADE
);

CREATE TABLE User (
    login VARCHAR(50) PRIMARY KEY,
    password CHAR(32) NOT NULL,
    question_securite VARCHAR(255),
    reponse_securite VARCHAR(255)
);
```
---

## 📌 Architecture du Projet  
![Architecture](https://github.com/user-attachments/assets/78ef851d-2bdc-4d32-ab79-63bc572ab1ac)  

---

## 📌 Structure du Projet  

```bash
Reclamations/
│
├── beans/                         # Modèles de données (JavaBeans)
│   ├── EStatut.java
│   ├── Etudiant.java
│   ├── Reclamation.java
│   ├── TraitementReclamation.java
│   ├── User.java
│
├── connexion/                     # Gestion de la connexion à la BDD
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
└── README.md                      # Documentation du projet
```

## 📌Vidéo Démonstrative

https://github.com/user-attachments/assets/5d8ea38c-abca-4760-ad39-1ccbb00ee571



