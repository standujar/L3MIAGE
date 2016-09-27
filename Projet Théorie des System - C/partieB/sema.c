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
	int num_p; //numéro du processus
	double *valeur_alea; //tampon source
	double *res; //tampon resultat
	pthread_mutex_t * mutex;
}arg;

//fonction qui calcule la moyenne quadratique
void *moyenneQuadratique(void *valeur)
{
	//accès aux champs de la structure
	arg *argument = valeur;
	int j = (*argument).num_p;
	double *data = (*argument).valeur_alea;
	int i;
	double moyenne=0;
	double sommeCarre=0;	
	
	
	

	//opération P sur un sémaphore, decrementation du semaphore : P-1
	sem_wait(&semaphore);
	printf("Entrée du processus : %d \n",j);

	for(i=0;i<j+1;i++)
	{ 
		
		sommeCarre=sommeCarre + (data[i]*data[i]);
	}
	moyenne=( sqrt(sommeCarre/(double) (j+1) ));
	
	//opération V sur un sémaphore
	sem_post(&semaphore);
	

	pthread_mutex_lock((*argument).mutex); //verrouillage pour l'écriture (thread bloqué)
    	((*argument).res[i]) = moyenne; //accede à la variable res pour stocker les résultats de la moyenne quadratique
	pthread_mutex_unlock((*argument).mutex); //déverrouillage de l'écriture pour acces aux autres processus d'écrire

	//Processus qui sont sortis
	printf("Sortie du processus : %d \n", j);
}

int main(int argc, char*argv[])
{                                               
	int i=0;//Compteur simple
	int N=0;//initialisation taille du tableau
	
	struct timeval time;
	double temps,tvalBefore, tvalAfter;
	
	
	//Demande à l'utilisateur de taper la taille de tableau
	printf("entrez une valeur : ");
	scanf("%d",&N);

	//création du tableau valeur_alea
	double *valeur_alea = NULL;
	valeur_alea = malloc(N * sizeof(double)+1);

	//création du tableau resultat
	double *resultat = NULL;
	resultat = malloc(N * sizeof(double)+1);

	//Creation du tableau des threads (MQUAD)
	pthread_t *thread = NULL;
	thread = malloc(N * sizeof(pthread_t));

	//tableau : arguments (sert à garder les valeurs), pour savoir quelle valeur utiliser
	arg *argument;
	argument = malloc(N * sizeof(arg));

	//remplissage du tableau valeur_alea
    	for(i=0;i<N;i++)
	{
		valeur_alea[i]=rand()/10000.0;
		printf("%f\n", valeur_alea[i]);
	}

	gettimeofday(&time, NULL);
	tvalBefore=time.tv_sec+(time.tv_usec/1000000.0);

	//création du sémaphore
	sem_init(&semaphore, 0, 1);
	//0 : semaphore est partagé entre les threads
	//3 : le nombre de processus pour accéder au tampon (alpha)
	
	pthread_mutex_t mutex;
	pthread_mutex_init(&mutex,NULL);
	
    	//Lancement des threads
	for(i=0;i<N;i++)
	{
		argument[i].num_p = i;
		argument[i].valeur_alea = valeur_alea;
		argument[i].res = resultat; //On passe l'adresse du tampon résultat pour qu'il soit le même pour chaque processus
		argument[i].mutex = &mutex; //On passe l'adresse du mutex pour qu'il soit le même pour chaque processus
		//création thread
		pthread_create(&thread[i], NULL, moyenneQuadratique, (void*)&argument[i]);
	}

	i=0;
	for(i=0;i<N;i++)
	{
		pthread_join(thread[i], NULL);
	}

	//affichage des résultats
	printf("Résultats: \n");
	for(i=0;i<N;i++)
	{
		printf("%f \n",resultat[i]);
	}

	//destruction du sémaphore
	sem_destroy(&semaphore);

	gettimeofday(&time, NULL);
	tvalAfter=time.tv_sec+(time.tv_usec/1000000.0);

	temps = (tvalAfter-tvalBefore)*1000000000;
	//calcul et affichage du temps d'execution en ms
	
	printf("temps d'execution (nanos) : %f\n", temps);

	/*//Libération mémoire
free(valeur_alea);
free(resultat);
free(thread);
free(argument);*/

	return 0;
}
