# ACL_2020_InfoDrame

## Compilation et lancement :

Vous pouvez cloner ce projet, le compiler et l'executer en copiant 
et collant les 4 lignes suivantes dans votre terminal

```
git clone https://github.com/TabaryM/ACL_2020_InfoDrame.git
cd ACL_2020_InfoDrame
mvn package
java -jar target\ACL-2020-InfoDrame-0.0-SNAPSHOT.jar
```

## Membres du groupe : 
Corentin Roberge-Mentec

Jordan Scherring

Mathieu Tabary

Paul-Emile Watelot

# Fonctionalités prévues et découpage par sprint

## Sprint 1
### Objectifs
 - 1 Déplacement du personnage-joueur sur une grille-plateau
 - 2 Affichage d’un HUD contenant les infos suivantes :
 
    - 2.1 Score

    - 2.2 Nombres de vies du joueur
    
- 3 Création de menus
 
    - 3.1. Menu principal

    - 3.2. Menu de pause

- 4 Génération d’un labyrinthe par défaut
 
    - 4.1. Lecture d’un fichier

    - 4.2. Les personnages dans le jeu ne peuvent pas traverser les murs du labyrinthe

    - 4.3. Créations de cases spéciales :

        - 4.3.1. Pièce : Quand traversée par le personnage-joueur, augmente le score

        - 4.3.2. Grosse pièce : Comme pièce + permet de manger les fantômes


### Répartition des responsabilité
Mathieu et Paul-Emile : 1 + 4.1 + 4.2

Corentin + Jordan : 2 + 3. + 4.3

### Diagrammes
#### Diagramme de classe
Objectif d'implémentation : 
![ClassDiag](https://raw.githubusercontent.com/TabaryM/ACL_2020_InfoDrame/a05f8afcb46a40d756050e0346835bb8722fa0a2/conception/diagrammes/class/Objectif_Sprint1.svg?token=AKJ4FHAQDN6YKUPCR7HQDFC7UFPL4)

#### Diagrammes de séquence
Déroulement du jeu : 

![ClassDiag](https://raw.githubusercontent.com/TabaryM/ACL_2020_InfoDrame/a05f8afcb46a40d756050e0346835bb8722fa0a2/conception/diagrammes/sequence/MainGame.svg?token=AKJ4FHE47WFULCLEKLWNFBC7UFPRQ)


![ClassDiag](https://raw.githubusercontent.com/TabaryM/ACL_2020_InfoDrame/a05f8afcb46a40d756050e0346835bb8722fa0a2/conception/diagrammes/sequence/UserPlaying.svg?token=AKJ4FHERRZ5EMMW5MGW2PJ27UFPRS)


### Fonctionnalités implémentées une fois le Sprint 1 fini
Voir [BlacklogSprint1.md](https://github.com/TabaryM/ACL_2020_InfoDrame/blob/main/conception/BacklogSprint1.md)