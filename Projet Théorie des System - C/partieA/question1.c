#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>




// Fonctions calcul moyenne Arithmetique,moyenne Quadratique et somme Cubique


float moyenneArithmetique(float i, float somme) {
	return somme/i;
	
}

float moyenneQuadratique(float i,float sommeCarre) {
	return ( sqrt(sommeCarre/i) );
}

float sommeCubique(float i){
	return i*i*i;
	
}



int main(int argc,char** argv) {
	// On declare et on initialise les variables

	struct timeval tim;
	double temps,tvalBefore, tvalAfter;
	int j;
	float somme,sommeCarre;
	int N,taille;
	double MQ,MA,SC;
	
		
	somme=0.0; // va contenir la somme pour le calcul de la moyenne arithmetique
	sommeCarre=0.0;// va contenir la somme des carres pour le calcule de la moyenne quadratique
	SC=0.0;// va contenir la somme cubique
	j=0;
	
	FILE* filedata = fopen("/home/meriem/Documents/projet_TSE/sequentiel.dat", "w"); // fichier qui va contenir les donnees

	srand(time(NULL)); //on initialise l'aleatoire
	
	for(N=20; N<=200 ;N=N+20) { // N prend les valeurs 20,40,60,80,100,120,140,160,180 et 200
	
	taille=N;//taille du tampon prend les differentes valeurs de N
	
	//int tableau[taille];
	int*tableau= NULL; // declaration d'un tampon vide
	
	tableau=malloc(taille* sizeof(int)+1); // on initialise la taille du tampon
	
	// on remplit le tampon par des nombres compris entre 0 et 99
	for(j=0;j<taille;j++) {
		tableau[j]=rand()%100;
	
	
	
	gettimeofday(&tim, NULL);
	
	tvalBefore=tim.tv_sec+(tim.tv_usec/1000000.0); //on recupere le temps de début en secondes

	
	somme=somme+tableau[j]; // on calcule la somme des valeurs
	sommeCarre=sommeCarre+(tableau[j]*tableau[j]); // somme des carre 
	
	MA=moyenneArithmetique(j,somme); //  on recupere la moyenne arithmetique en utilisant la fonction correspondante
	MQ=moyenneQuadratique(j,sommeCarre); //  on recupere la moyenne quadratique en utilisant la fonction correspondante
	SC=SC+(sommeCubique(j));//  on recupere la somme cubique en utilisant la fonction correspondante

	gettimeofday(&tim, NULL);
	
	tvalAfter=tim.tv_sec+(tim.tv_usec/1000000.0);// on recupere le temps à la fin des differents calculs en secondes

	temps = (tvalAfter-tvalBefore)*1000000000;// le temps de calcul = tfinal - tdebut( on le converti en nanosecondes)
	


	printf("la taille du tableau est: %d\n",N);
	
	// on affiche les differents calculs , %.2f veut dire que l'on prend 2 chiffres apres la virgule
	printf("la moyenne Arithmétique est de : %.2f \n", MA); 

	printf("la moyenne quadratique est de : %.2f \n", MQ);

	printf("la sommecubique est de : %.2f \n", SC);

	// si le fichier existe, on ecrit dedans la taille du tampon et le temps de calcul correspondant
	}
	if(filedata!=NULL)
	{
	
	fprintf(filedata," %d %.2lf\n",N,temps);
	printf("Le temps d'execution est de : %.2lf nanosecondes \n",temps);

	}

	
}

// on ouvre une fenetre Gnuplot 
FILE* f;
f= popen("gnuplot", "w");	
fclose(filedata); // on ferme le fichier

fprintf(f, " plot \"/home/stan/Documents/projet_TSE/sequentiel.dat\" using 1:2 with linespoints \n");
fflush(f);
sleep(5) ; 
fclose(f); //on ferme la fenetre Gnuplot

}
