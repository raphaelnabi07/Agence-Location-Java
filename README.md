# AutoLoc Pro — Gestion d'une agence de location de véhicules

Application **console en Java** qui gère une agence de location de véhicules multi-catégories (voitures, motos, utilitaires, véhicules de luxe avec chauffeur) pour des clients particuliers ou des entreprises.

Le projet est réalisé dans le cadre d'un module de **programmation orientée objet** (Java 1, Efrei, 2026-2027). Il illustre l'encapsulation, l'héritage, le polymorphisme, les classes abstraites et les interfaces sur un cas concret.

---

## Sommaire

1. [Fonctionnalités](#fonctionnalités)
2. [Technologies](#technologies)
3. [Architecture du projet](#architecture-du-projet)
4. [Modèle objet](#modèle-objet)
5. [Règles métier](#règles-métier)
6. [Notions de POO illustrées](#notions-de-poo-illustrées)
7. [Installation et lancement](#installation-et-lancement)
8. [Utilisation](#utilisation)
9. [Tests](#tests)
10. [Documentation Javadoc](#documentation-javadoc)
11. [Choix de conception](#choix-de-conception)
12. [Difficultés rencontrées](#difficultés-rencontrées)
13. [Répartition des tâches](#répartition-des-tâches)
14. [Auteurs](#auteurs)

---

## Fonctionnalités

- Gérer le **catalogue des véhicules** : ajout, recherche par immatriculation, affichage de la flotte, des véhicules disponibles et d'un catalogue regroupé par catégorie.
- Gérer les **clients** (particuliers et entreprises) avec détection des doublons sur le numéro de permis.
- Créer des **contrats de location** reliant un client, un véhicule et une période, avec refus propre si le véhicule n'est pas disponible.
- **Calculer le tarif** selon la catégorie du véhicule, la durée, le chauffeur éventuel et la remise du client.
- Suivre l'**état des véhicules** : disponible, loué, en maintenance.
- Planifier et effectuer l'**entretien** selon le kilométrage.
- Gérer l'**assurance** de chaque véhicule (prime et franchise).
- Produire des **statistiques** : chiffre d'affaires, taux d'occupation, véhicules à réviser, comptage par catégorie.
- Consulter l'**historique** des contrats d'un client.
- **Menu interactif** robuste : toute saisie invalide est redemandée sans faire planter le programme.

## Technologies

| Élément | Détail |
|---|---|
| Langage | Java 17 ou supérieur |
| IDE | IntelliJ IDEA |
| Interface | Console (`Scanner`) |
| Dépendances externes | Aucune |
| Documentation | Javadoc |
| Versionnement | Git / GitHub |

## Architecture du projet

```
.
├── src/
│   ├── vehicules/
│   │   ├── Vehicule.java          classe abstraite, racine de la hiérarchie
│   │   ├── Voiture.java
│   │   ├── Moto.java
│   │   ├── Utilitaire.java
│   │   ├── VehiculeLuxe.java      hérite de Voiture
│   │   └── EtatVehicule.java      énumération (DISPONIBLE, LOUE, EN_MAINTENANCE)
│   ├── interfaces/
│   │   ├── Assurable.java
│   │   ├── Maintenable.java
│   │   └── Reservable.java
│   ├── clients/
│   │   ├── Client.java
│   │   ├── Particulier.java       hérite de Client
│   │   └── Entreprise.java        hérite de Client
│   ├── location/
│   │   ├── ContratLocation.java
│   │   └── AgenceLocation.java    classe de gestion centrale
│   ├── app/
│   │   └── MenuPrincipal.java     point d'entrée (méthode main)
│   └── test/
│       ├── TestVehicules.java
│       ├── TestClients.java
│       └── TestAgence.java
├── docs/                          Javadoc générée (ouvrir docs/index.html)
└── README.md
```

| Package | Rôle |
|---|---|
| `vehicules` | Modèle des véhicules et de leur état |
| `interfaces` | Comportements transversaux (assurance, entretien, réservation) |
| `clients` | Modèle des clients et de leurs remises |
| `location` | Contrats et logique métier de l'agence |
| `app` | Interface utilisateur console |
| `test` | Programmes de test par domaine |

## Modèle objet

### Hiérarchie des véhicules

```
                 «abstract» Vehicule
           (implements Assurable, Reservable)
                 /        |         \
            Voiture      Moto     Utilitaire
       (Maintenable) (Maintenable) (Maintenable)
               |
         VehiculeLuxe
```

### Hiérarchie des clients

```
          Client
         /      \
 Particulier   Entreprise
```

### Relations principales

| Relation | Type | Signification |
|---|---|---|
| `Voiture`, `Moto`, `Utilitaire` → `Vehicule` | Héritage | Chaque catégorie est un véhicule |
| `VehiculeLuxe` → `Voiture` | Héritage (2 niveaux) | Spécialisation haut de gamme |
| `Particulier`, `Entreprise` → `Client` | Héritage | Deux profils de client |
| `Vehicule` implémente `Assurable`, `Reservable` | Interface | Tout véhicule peut être assuré et réservé |
| `Voiture`, `Moto`, `Utilitaire` implémentent `Maintenable` | Interface | Les catégories concrètes nécessitent un entretien |
| `ContratLocation` → `Client`, `Vehicule` | Association | Relie un client à un véhicule |
| `AgenceLocation` → `Vehicule[]`, `Client[]`, `ContratLocation[]` | Agrégation | Gère les collections métier |
| `Vehicule` → `EtatVehicule` | Association | Cycle de vie du véhicule |

### Les trois interfaces

| Interface | Méthodes | Constante |
|---|---|---|
| `Assurable` | `calculerPrimeAssurance()`, `estAssure()`, `souscrireAssurance(double)` | `TAUX_BASE_ASSURANCE = 0.03` |
| `Maintenable` | `planifierEntretien(int)`, `necessiteEntretien()`, `effectuerEntretien()` | `KM_ENTRE_ENTRETIENS = 10000` |
| `Reservable` | `estDisponiblePourReservation()`, `reserver(Client, int)`, `libererReservation()` | — |

## Règles métier

### Tarif de location (avant remise)

| Catégorie | Règle |
|---|---|
| Voiture | tarif × jours, **-10 %** si la durée est de 7 jours ou plus |
| Moto | tarif × jours (pas de remise) |
| Utilitaire | tarif × jours **+ 20 EUR** de frais fixes |
| Véhicule de luxe | tarif Voiture (remise de 10 % incluse dès 7 jours) **× 1,5**, puis **+ 80 EUR × jours** si chauffeur inclus |

### Remise client

Le montant d'un contrat est `tarif de location × (1 - remise du client)`.

| Client | Remise |
|---|---|
| Particulier | 5 % |
| Entreprise | 10 % |

### Assurance, entretien et réservation

- **Assurance** : la prime journalière vaut `0,03 × tarif journalier` ; la souscription mémorise la franchise.
- **Entretien** : un véhicule nécessite un entretien lorsque `kilométrage ≥ kmProchainEntretien` (10 000 km au départ). L'entretien passe le véhicule en maintenance, le journalise, fixe le prochain seuil à `kilométrage + 10 000` puis le remet disponible.
- **Réservation** : seul un véhicule `DISPONIBLE` peut être loué. Un véhicule `LOUE` ou `EN_MAINTENANCE` est refusé avec un message clair. Un véhicule loué ne peut pas être entretenu.
- **Égalité** : deux véhicules sont égaux s'ils ont la même immatriculation, deux clients s'ils ont le même numéro de permis, deux contrats s'ils ont même client, même véhicule et même date de début.

## Notions de POO illustrées

| Notion | Où la voir dans le code |
|---|---|
| Constructeurs (défaut, initialisation, copie) | Toutes les classes du modèle |
| Encapsulation, getters et setters validés | `Vehicule`, `Client` |
| Attributs et méthodes `static` | Compteurs de véhicules et de clients, constante `DEVISE` |
| Redéfinition (`toString`, `equals`, `hashCode`) | `Vehicule`, `Client`, `ContratLocation` |
| Héritage et `super(...)` | `Voiture`, `Moto`, `Utilitaire`, `VehiculeLuxe`, `Particulier`, `Entreprise` |
| Polymorphisme | `calculerTarifLocation()`, `calculerRemise()`, `toString()` sur des tableaux de `Vehicule` et `Client` |
| `instanceof` et downcast | `AgenceLocation`, `TestVehicules` |
| Classe et méthodes abstraites | `Vehicule` |
| Interfaces | `Assurable`, `Maintenable`, `Reservable` |
| Énumération | `EtatVehicule` |

## Installation et lancement

### Prérequis

- JDK 17 ou supérieur
- IntelliJ IDEA (recommandé) ou un terminal

### Avec IntelliJ IDEA

1. Cloner le dépôt : `git clone https://github.com/raphaelnabi07/Agence-Location-Java.git`
2. `File > Open` et sélectionner le dossier du projet.
3. Clic droit sur `src` puis `Mark Directory as > Sources Root`.
4. Vérifier que le JDK du projet est en version 17 ou supérieure.
5. Lancer `app.MenuPrincipal`.

### En ligne de commande

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.MenuPrincipal
```

Sous Windows, si les accents s'affichent mal, passer la console en UTF-8 avec `chcp 65001`.

## Utilisation

Au démarrage, un jeu de données de démonstration est chargé : 2 clients (un particulier, une entreprise) et 5 véhicules (un par catégorie, dont une Clio ayant dépassé le seuil d'entretien).

```
========== AUTOLOC PRO ==========
1. Ajouter un véhicule
2. Ajouter un client
3. Louer un véhicule
4. Retourner un véhicule
5. Afficher la flotte complète
6. Afficher les véhicules disponibles
7. Afficher les véhicules à entretenir
8. Effectuer l'entretien d'un véhicule
9. Afficher les statistiques de l'agence
10. Afficher le catalogue par catégorie
0. Quitter
```

## Tests

Les programmes du package `test` s'exécutent indépendamment :

| Classe | Contenu |
|---|---|
| `TestVehicules` | Constructeurs, validations, `equals`/`hashCode`, flotte polymorphe, tarifs, assurance, maintenance, réservation |
| `TestClients` | Constructeurs, copie, âge, `equals`, remises polymorphes |
| `TestAgence` | Scénario complet : ajout, location, refus, entretien, retour, statistiques, historique |

```bash
java -cp out test.TestVehicules
java -cp out test.TestClients
java -cp out test.TestAgence
```

Le scénario de recette couvre : affichage des données de démonstration, ajout d'un véhicule et d'un client, location, disparition du véhicule loué des disponibles, entretien, retour, statistiques, catalogue, refus de location d'un véhicule déjà loué, saisie invalide et sortie propre.

## Documentation Javadoc

La documentation des classes et interfaces du modèle est générée dans le dossier `docs/`. Ouvrir `docs/index.html` dans un navigateur.

Pour la régénérer dans IntelliJ : `Tools > Generate JavaDoc`.

## Choix de conception

- **Tableaux à capacité fixe** dans `AgenceLocation` (version de base), avec des compteurs d'éléments. Le remplacement par des `ArrayList` est une évolution possible.
- **Traces de constructeurs** : elles sont actives dans les tests pour visualiser l'ordre d'appel des constructeurs, et désactivées dans le menu pour garder l'affichage lisible (`Vehicule.setTraces(false)`, `Client.setTraces(false)`).
- **Validations sans exceptions** dans les setters : une valeur invalide est ignorée et un message d'erreur est affiché.
- **Séparation menu et logique** : `MenuPrincipal` gère uniquement la saisie et l'affichage, chaque option appelle une méthode dédiée de `AgenceLocation`.
- **Saisies sécurisées** : lecture des entiers avec `hasNextInt()`, des décimaux acceptant la virgule, boucle de redemande en cas d'erreur.
- **Constructeur de copie protégé** dans `Vehicule` (classe abstraite) et vérification de nullité dans tous les constructeurs de copie.

## Difficultés rencontrées

## Répartition des tâches

## Auteurs

| Membre | GitHub |
|---|---|
| Raphaël Nabi | [@raphaelnabi07](https://github.com/raphaelnabi07) |
| Bimen Ishak | [@bimen-ishak](https://github.com/bimen-ishak) |
| Alexandre LAFAGE--LANDRY | [@Alexandre34420](https://github.com/Alexandre34420) |

Dépôt : [Agence-Location-Java](https://github.com/raphaelnabi07/Agence-Location-Java)

Module Java 1 — Programmation orientée objet, enseignant : Mohamed Hamidi — Efrei, 2026-2027.
