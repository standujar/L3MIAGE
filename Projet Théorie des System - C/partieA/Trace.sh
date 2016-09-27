#! /bin/bash

echo "Lancement du programme sequentiel"

./q1 


echo "Veuillez appuyer sur une touche pour lancer le programme multithread "
read l #la commande read permet de stocker l'appui du clavier 
./q2


echo "Création des deux graphes"
gnuplot <<EOF
set term png
set title "Temps d'excécution" font ",15"
set xlabel "N (taille du tampon) "
set ylabel "Temps en nanos"
set output "Final.png"
plot '/home/stan/Documents/projet_TSE/sequentiel.dat' with linespoints,'/home/stan/Documents/projet_TSE/parallele.dat' with linespoints
EOF
