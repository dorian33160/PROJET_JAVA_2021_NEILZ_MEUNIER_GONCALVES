# Projet M4207C - Utilisation

## Prérequis :

- Avoir téléchargé l&#39;ensemble des fichiers du projet
- Avoir installé Java sur son ordinateur

## Mise en fonctionnement de la centrale

Le démarrage de la centrale s&#39;effectue en entrant la commande suivante dans un terminal, en prenant garde à bien être positionné dans le dossier principal du projet :

```java -cp .\org.eclipse.paho.client.mqttv3-1.2.5.jar ./centrale```

Dès lors, la centrale est opérationnelle.

## Mise en fonctionnement d&#39;un capteur

Similairement au démarrage de la centrale, on peut démarrer un capteur avec la commande suivante :

```java -cp .\org.eclipse.paho.client.mqttv3-1.2.5.jar .\capteur.java 1```

Cette commande lance un capteur sur le canal 1. En remplaçant le 1 par n&#39;importe quel nombre, on peut lancer un capteur sur le canal correspondant.

Une fois la commande exécutée, le capteur commence à envoyer des valeurs sur son canal

Il est possible de créer autant de capteur que voulu. Si plusieurs capteurs possèdent le même identifiant/canal, ils seront perçus comme un seul par la centrale.

## Mise en fonctionnement d&#39;un afficheur

Rappel : la centrale transmet les informations du capteur X vers le canal de l&#39;afficheur X, ou X est un nombre défini par l&#39;utilisateur.

La commande à utiliser est la suivante :

```java -cp .\org.eclipse.paho.client.mqttv3-1.2.5.jar .\afficheur.java valeur 1```

L&#39;afficheur lancé ainsi affichera les données en temps réel du capteur 1.

On peut également utiliser la commande suivante :

```java -cp .\org.eclipse.paho.client.mqttv3-1.2.5.jar .\afficheur.java historique 1```

L'afficheur ainsi lancé montrera les 5 dernières valeurs du capteur 1, datées et mises à jour toutes les 10 secondes.

Il est possible de créer autant d&#39;afficheurs que voulu. Si plusieurs afficheurs ont le même identifiant/canal, ils affichent exactement les mêmes valeurs. Si un afficheur est créé sur un canal ne disposant pas de capteur, aucune valeur n&#39;est affichée.
