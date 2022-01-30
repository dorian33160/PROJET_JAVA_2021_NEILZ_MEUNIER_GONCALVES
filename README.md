# Projet M4207C - Utilisation

## Prérequis :

- Avoir téléchargé l&#39;ensemble des fichiers du projet
- Avoir installé Java sur son ordinateur
- Avoir compilé tous les fichiers Java
Pour cela, utiliser la commande suivante :
```javac -cp .:./org.eclipse.paho.client.mqttv3-1.2.5.jar ./*.java```

## Mise en fonctionnement de la centrale

Le démarrage de la centrale s&#39;effectue en entrant la commande suivante dans un terminal, en prenant garde à bien être positionné dans le dossier principal du projet :

```java -cp .:./org.eclipse.paho.client.mqttv3-1.2.5.jar ./centrale```

Dès lors, la centrale est opérationnelle.

## Mise en fonctionnement d&#39;un capteur

Similairement au démarrage de la centrale, on peut démarrer un capteur avec la commande suivante :

```java -cp .:./org.eclipse.paho.client.mqttv3-1.2.5.jar ./capteur.java 1```

Cette commande lance un capteur sur le canal 1. En remplaçant le 1 par n&#39;importe quel nombre, on peut lancer un capteur sur le canal correspondant.

Une fois la commande exécutée, le capteur commence à envoyer des valeurs sur son canal

Il est possible de créer autant de capteur que voulu. Si plusieurs capteurs possèdent le même identifiant/canal, ils seront perçus comme un seul par la centrale.

## Mise en fonctionnement d&#39;un afficheur

Rappel : la centrale transmet les informations du capteur X vers le canal de l&#39;afficheur X, ou X est un nombre défini par l&#39;utilisateur.

La commande à utiliser est la suivante :

```java -cp .:./org.eclipse.paho.client.mqttv3-1.2.5.jar ./afficheur.java valeur 1```

L&#39;afficheur ainsi lancé affichera les données en temps réel du capteur 1.

On peut également utiliser la commande suivante :

```java -cp .:./org.eclipse.paho.client.mqttv3-1.2.5.jar ./afficheur.java historique 1```

L'afficheur ainsi lancé montrera les 5 dernières valeurs du capteur 1, datées et mises à jour toutes les 10 secondes.

Il est possible de créer autant d&#39;afficheurs que voulu. Si plusieurs afficheurs ont le même identifiant/canal, ils affichent exactement les mêmes valeurs. Si un afficheur est créé sur un canal ne disposant pas de capteur, aucune valeur n&#39;est affichée.

## Arrêt d'un capteur/afficheur

Les programmes des capteurs et afficheurs sont très légers et peuvent ainsi être démarrés et arrêtés sans contrainte.
Pour les arrêter, il suffit de fermer le terminal dans lequel ils s'executent ou de faire un CTRL+c dans ce dernier.

## Arrêt de la centrale/création de l'historique en CSV

La centrale constituant un programme plus lourd et devant effectuer des actions avant sa fermeture (export du CSV), son fonctionnement ne sera pas correct en cas de fermeture "brutale". Le seul moyen de l'arrêter proprement est d'exécuter la commande suivante :

```java -cp .:./org.eclipse.paho.client.mqttv3-1.2.5.jar ./stop.java```

Après cela, le fichier CSV pourra être trouvé dans le dossier du projet, et ouvert avec le logiciel de votre choix.
