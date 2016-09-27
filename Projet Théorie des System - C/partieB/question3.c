#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <math.h>
#include <pthread.h>
#include <semaphore.h>

//déclaration du semaphore
sem_t semaphore;

typedef struct{
	int num_processus; //numéro du processus
	double *valeur_aleatoire; //tampon source
	double *final; //tampon resultat
	pthread_mutex_t * mutex;
}arg;


//fonction Simulation qui calcule la moyenne quadratique
void *moyenneQuadratique(void *valeur) {
	
	//accès aux champs de la structure
	arg *argument = valeur;
	int j = argument->num_processus;
	double *data = argument->valeur_aleatoire;
	int i=0;
	double sommeCarre=0;
	double moyenneQuadratique=0;

	//opération P sur un sémaphore, decrementation du semaphore
	sem_wait(&semaphore);
	printf("Entrée du processus : %d \n",i);

	//Le processus entre dans sa section critique
	
	for(i=0;i<j+1;i++){
		
		sommeCarre=sommeCarre + (data[i]*data[i]);
		moyenneQuadratique=( sqrt(sommeCarre/(double) (j+1) ));
	}
	
	//opération V sur un sémaphore
	sem_post(&semaphore);
	

	pthread_mutex_lock(argument->mutex); //verrouillage pour l'écriture (thread bloqué)
    	(argument->final[i]) = moyenneQuadratique; //accede à la variable res pour stocker les résultats de la moyenne quadratique
	pthread_mutex_unlock(argument->mutex); //déverrouillage de l'écriture pour acces aux autres processus d'écrire

	//Processus sortis
	printf("Sortie du processus : %d \n", i);
	
}



int main(int argc, char*argv[])
{
	int i=0;//Compteur simple
	int taille=0;//initialisation taille du tableau
	
	struct timeval time;
	double temps,tvalBefore, tvalAfter;

	/*//Demande à l'utilisateur de taper la taille de tableau
	printf("entrez une valeur : ");
	scanf("%d",&n);*/
	
	
	double *valeur_aleatoire = NULL;
	//création du tableau valeur_alea
	for(taille=20;taille<=200;taille=taille+20)
	{
	valeur_aleatoire = malloc(taille * sizeof(double));
	}

	//création du tableau resultat
	double *resultat = NULL;
	resultat = malloc(taille * sizeof(double));

	//Creation du tableau des threads (MQUAD)
	pthread_t *thread = NULL;
	thread = malloc(taille * sizeof(pthread_t));

	//tableau : arguments (sert à garder les valeurs), pour savoir quelle valeur utiliser
	arg *argument;
	argument = malloc(taille * sizeof(arg));

	//remplissage du tableau valeur_alea
    	for(i=0;i<taille;i++)
	{
		valeur_aleatoire[i]=rand()%100;
		printf("%f\n", valeur_aleatoire[i]);
	}

	gettimeofday(&time, NULL);
	tvalBefore=time.tv_sec+(time.tv_usec/1000000.0);
	//création du sémaphore
	sem_init(&semaphore, 0, 5);
	//0 : semaphore est partagé entre les threads
	//5 : le nombre de processus pour accéder au tampon (alpha)
	
	pthread_mutex_t mutex;
	pthread_mutex_init(&mutex,NULL);
	
    	//Lancement des threads
	for(i=0;i<taille;i++)
	{
		argument[i].num_processus = i;
		argument[i].valeur_aleatoire = valeur_aleatoire;
		argument[i].final = resultat; //On passe l'adresse du tampon résultat pour qu'il soit le même pour chaque processus
		argument[i].mutex = &mutex; //On passe l'adresse du mutex pour qu'il soit le même pour chaque processus
		//création thread
		pthread_create(&thread[i], NULL, moyenneQuadratique, (void*)&argument[i]);
	}

	i=0;
	for(i=0;i<taille;i++)
	{
		pthread_join(thread[i], NULL);
	}

	//affichage des résultats
	printf("Résultats: \n");
	for(i=0;i<taille;i++)
	{
		printf("%f \n",resultat[i]);
	}

	//destruction du sémaphore
	sem_destroy(&semaphore);

	gettimeofday(&time, NULL);
	tvalAfter=time.tv_sec+(time.tv_usec/1000000.0);

	temps = (tvalAfter-tvalBefore)*1000000000;


	printf("temps d'execution (nanosecondes) : %f\n", temps);

	/*//Libération mémoire
	free(valeur_aleatoire);
    	free(argument);
	free(resultat);
	free(thread);*/
	
	return 0;
}
