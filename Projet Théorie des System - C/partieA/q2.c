#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <math.h>
#include <sys/time.h>
#include <unistd.h>
#include <pthread.h>

int j=1; // On initialise un entier pour l'etablissement des differentes fonctions


void *moyenneArithmetique(void *data) { //Fonction qui calcule la moyenne arithmetique
	
	int i;
	int*tab=data;
	double moyenne=0;
	double somme=0;	
	
	for(i=0;i<j+1;i++){
		
		somme=somme+tab[i];
		moyenne=(somme/(double)(j+1));
	}
	
	
}

void *moyenneQuadratique(void *data) { // Fonction qui calcule la moyenne quadratique
	
	int i;
	int*tab=data;
	double moyenne=0;
	double sommeCarre=0;	
	
	for(i=0;i<j+1;i++){
		
		sommeCarre=sommeCarre + (tab[i]*tab[i]);
		moyenne=( sqrt(sommeCarre/(double) (j+1) ));
	}
	
	
	
}

void *sommeCubique(void *data){ // Fonction qui calcule la somme cubique
	
	
	int i;
	double sommeCubique=0;
	int*tab=data;
	//sommeCubique=0;	
	
	for(i=0;i<j+1;i++){
		
		sommeCubique=sommeCubique+((tab[i]*tab[i]*tab[i]));
	}
	
	
}


int main(int argc,char** argv) {
	
	struct timeval tim; // structure qui va servire a recuperer le temps 
	double temps,tvalBefore, tvalAfter;
	int i,taille;
	
	pthread_t thread1,thread2,thread3; // declaration des 3threads pour 
	
	i=1;

	FILE* file2 = NULL; // creation d'un fichier vide
	file2 = fopen("/home/meriem/Documents/projet_TSE/parallele.dat", "w"); // dossier de creation du fichier que l'on appelle parallele.dat
	
	srand(time(NULL)); //on initialise l'aleatoire
	
	int*data= NULL; // initialisation du tableau data qui va contenir les nombres aleatoires
	
	for(taille=20; taille<=200 ;taille=taille+20) // tampon prend les valeurs 20,40 jusqu'a 200 avec un pas de 20
	{ 
		
		data= malloc(taille * sizeof(double)+1); 
		
		for(i=1;i<taille+1;i++) 
		{
			data[i]=  (rand()%100);//on genere un tableau de taille N de nombres aleatoires compris entre 0 et 99
							
			
		}

		gettimeofday(&tim, NULL);
		
		tvalBefore=tim.tv_sec+(tim.tv_usec/1000000.0); //on recupere le temps de début en secondes

		pthread_create(&thread1, NULL, moyenneArithmetique, (void*)data);//creation du thread qui calcule la moyenne arithmetique
		pthread_create(&thread2, NULL, moyenneQuadratique, (void*)data);//thread qui calcule la moyenne quad
		pthread_create(&thread3, NULL, sommeCubique, (void*)data);// thread qui calcule la sommeCubique
	
		pthread_join(thread1,NULL); //on attend la fin du thread 1
		pthread_join(thread2,NULL);// on attend la fin du thread 2
		pthread_join(thread3,NULL);// on attend la fin du thread 3
		
		gettimeofday(&tim, NULL);
		
		tvalAfter=tim.tv_sec+(tim.tv_usec/1000000.0); // on recupere le temps à la fin des differents calculs en secondes

		temps = (tvalAfter-tvalBefore)*1000000000; // le temps de calcul = tfinal - tdebut( on le converti en nanosecondes)
		
		
		
		if (file2 != NULL)
    		{ // on ecrit la taille et le temps de calcul pour chaque tampon dans le fichier 
        		fprintf(file2," %d %.2lf\n",taille,temps);
			printf("Le temps d'execution est de : %.2lf nanosecondes \n",temps);// affichage du temps
			
    		}

		printf("La taille du tableau est : %d\n",taille); 

	}

	fclose(file2); // on ferme le fichier 
	free(data);// on libere la memoire
	
}
