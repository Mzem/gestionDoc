# Fonctionnement de l'application
### Compilation

	mvn package
	
### Exécution

	java -jar target/gestionDoc-1.0.jar

Cette application propose une interface en ligne de commandes pour la gestion de documents personnels. Les documents sont stockés dans une base de données (Derby) avec leurs informations descriptives. L'application propose aussi une organisation en hiérarchie virtuelle grace à un gestionnaire de répertoires.

La liste des commandes disponibles dans l'application est la suivante :
- **help <nom commande>** : affiche l'aide générale ou d'une commande spécifique.
- **add <chemin fichier>** : ajoute le fichier ou tous les fichiers du répertoire dont le chemin est indiqué par <chemin fichier> à la base de données.
- **remove <nom fichier>** : supprime le fichier nommé <nom fichier> de la base de données.
- **show -extension=<ext> -auteur=<auteur> -date=<yyyy/MM/dd> -type=<type> -rep** : affiche les fichiers contenus dans la base de données. Possibilité d'affiner la recherche avec les options en utilisant la syntaxe *-critere=valeur*. L'option *-rep* n'affiche que les fichiers du répertoire actuel.
- **mkdir <nom répertoire>** : crée un répertoire dont le nom est <nom répertoire> et dont le répertoire parent est le répertoire actuel (par défaut INBOX).
- **rmdir <nom répertoire>** : supprime le répertoire dont le nom est <nom répertoire> s'il est bien un sous-répertoire du répertoire actuel et supprime les liens avec les fichiers qu'il contient.
- **ls** : affiche les fichiers et les répertoires contenus dans le répertoire actuel.
- **cd <nom répertoire>** : accède au sous-répertoire dont le nom est <nom répertoire> si il est bien un sous-répertoire du répertoire courant. <nom répertoire> peut valoir \"..\" ou \"INBOX\".
- **addf <nom fichier>** : ajoute le fichier nommé <nom fichier> au répertoire actuel.
- **rmf <nom fichier>** : supprime le fichier nommé <nom fichier> du répertoire actuel.
- **exit** : quitte l'application.

# Conception de l'application
L'application est divisée en 3 packges principaux :
- **donnees** : objets représentant un Fichier, un Repertoire et le lien RepertoireFichier.
- **database** : initialisation et connection à la BD et interaction entre les objets (données) et la base de données.
- **commandes** : gère les interactions avec l'utilisateur en proposant une multitude de commandes.

La schéma de la base de données de l'application contient 3 relations dont deux corréspondant à des entités et une corréspondant à une association :
- Fichier (nom, nomPropore, extension, auteur, taille, dateAjout, type)
- Repertoire (nom, auteur, dateAjout)
- RepertoireFichier (nomRepertoire, nomFichier)

