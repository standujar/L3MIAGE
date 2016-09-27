#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <math.h>
#include <pthread.h>
#include <semaphore.h>

sem_t semLecture;
sem_t semEcriture;

//Tampon d'entrée et sortie
int *source;
double *resultat;
int alpha;

//Variable globale permettant de compter les lectures 
int cptLecture=0;

int num_p=0; //depend de l'entier alpha inisialise par l'utilisateur qui correspond au nombre max de processus en lecture

// Fonction appelée par le thread
void * moyenneQuadratique(void * n)
{
	int j=*((int*)n);
	// On doit maintenant attendre  qu'un semaphore lecture soit disponible : P(Lecture)
	
	sem_wait(&semLecture);
	
	cptLecture++; // Des qu'un processus est en lecture on incremente le compteur
	num_p++; // correspond au numero du processus en lecture
	
	
	printf(" Processus: %d",num_p);
	
	double sommeCarre=0.0;
	int i=0;
	for(i=0;i<=j;i++)
	{
		sommeCarre=sommeCarre+(double)(source[i]*source[i]);	
	}
	
	//Des qu'un processus a termine de lire on decremente le compteur des lectures 	
		
	cptLecture--;
	
	
	// Calcul de la moyenne quadratique
	double MQ =sqrt(sommeCarre/(double)(i)); 
	
	// Un processus entre dans le tampon resultat pour ecrire sa moyenne donc P(Ecriture)
	sem_wait(&semEcriture);
	printf(" écrit la moyenne %.2lf dans la case %d du tampon resultat \n ",MQ,j);
	
	resultat[j]=MQ; // le processus ecrit sa moyenne dans le tampon source
	
	
	// On fait V(Ecriture) une fois qu'il a fini	
	sem_post(&semEcriture);
	
	if(cptLecture==0&&num_p==alpha)
	{
		num_p=0; // On le réinitialise, c'etait pour savoir si on était a la fin
		int x=0;
		for(x=0;x<alpha;x++)
		{
			sem_post(&semLecture);
			
		}

	printf(" les %d tickets sont disponibles pour la lecture \n",alpha);
	}
	

}
		

int main(int argc, const char * argv[]){ 
	
	// on affiche un message d'erreur sur le terminal si l'utilisateur ne rentre pas les donnees necessaires
	if (argv[1]==NULL)
	{
		printf("Veuillez donner la taille du tampon source \n");
		exit(0);
        }
        if (argv[2]==NULL)
	{
		printf("Combien voulez-vous de processus en lecture simultanement?(argument 2) \n");
		exit(0);
        }
	

	struct timeval tim;
	double temps,tvalBefore, tvalAfter;
	int i=0;
	
	//nombre d'elements dans le tampon source
	int n=atoi(argv[1]);
	
	//nombre alpha de processus ayant le droit de lire simultanement
	alpha=atoi(argv[2]);
	
	printf("La taille du tampon est: %d  et alpha= %d \n ",n,alpha); 
	
	// Nous créons un tableau de nombres aléatoire s
	source =(int*) malloc(n*sizeof(int));
	
	
	srand(time(NULL));
	

	gettimeofday(&tim, NULL);
	
	tvalBefore=tim.tv_sec+(tim.tv_usec/1000000.0); //on recupere le temps de début en secondes
	
	// Initialisation du tableau avec des chiffres allant de 0 a 20 et affichage
	printf("tampon : ");
	for(i=0;i<n;i++)
	{
		source[i]= rand()%100; // on genere des nombres aleatoires entre 0 et 99
		printf(" %d ",source[i]);
	}
	printf("\n");
	
	
	// Et maintenant un autre tableau de même taille pour l'ecriture 
	resultat = malloc(n*sizeof(double));
	for(i=0;i<n;i++)
	{
		resultat[i]=0;
	}
	
	
	// Initialisation du semaphore Lecture avec le nombre entre par l'utilisateur
	sem_init(&semLecture,0, alpha); 
	
	//On initialise semEcriture à 1 pour que le 1er processus puisse ecrire
	sem_init(&semEcriture,0, 1); 
	
	// on declare le thread
	pthread_t thread; 
	for(i=0;i<n;i++)
	{
		pthread_create(&thread,NULL,moyenneQuadratique,(void*)&i);
		pthread_join(thread,NULL);//attente de la fin des thread
		
	}
	
	
	// On affiche les résultats del'écriture
	for (i =0; i< n; i++){ 
		printf ("  %.2lf ",resultat[i]); 
	} 
	printf("\n");

	gettimeofday(&tim, NULL);
	
	tvalAfter=tim.tv_sec+(tim.tv_usec/1000000.0);// on recupere le temps à la fin des differents calculs en secondes

	temps = (tvalAfter-tvalBefore)*1000000000;// le temps de calcul = tfinal - tdebut( on le converti en nanosecondes)
	
	printf("\nLe temps d'execution pour %d valeurs est  : %.2lf nanosecondes\n",n,temps);


	free(source);
	free(resultat);
	

}


