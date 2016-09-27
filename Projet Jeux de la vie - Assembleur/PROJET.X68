*-----------------------------------------------------------
* Program    :
* Written by :
* Date       :
* Description:
*-----------------------------------------------------------
	ORG	$1000
	
	
	
	jsr init	
FIN:	JMP FINPRG



init:			*****************************Initialisation************************************
						
	MOVE.L	#TAB,A0
	MOVE.B	#255,D2		** Initialise un tableaux de 255 cases
	
	JSR	GRILLE		** Appel l'affichage
MAZ:	
	MOVE.B	#0,(A0)+	** Met toutes les cases a 0
	DBRA	D2,MAZ	
	MOVE.L	#TAB,A0	
	MOVE.L	#15,D1	
LINE_1:				** Met la 1ere ligne a 1
	
	MOVE.B	#1,(A0)+
	DBRA	D1,LINE_1	
	MOVE.L	#14,D2
AUTRE_L:			** Met 1 sur dans la 1ere case puis saute 14 case puis met 1 ,jusque la derniere ligne 
	CMP	#0,D2
	BEQ	DERN_L
	MOVE.B	#1,(A0)+	
	SUB.L	#1,D2
	MOVE.L	#0,D1
	
	
SAUT_C:		
	CMP	#14,D1					
				** Tout ca sert a mettre a 1 toutes les cases exterieures dans notre tableau aux dimensions plus petites donc tous les bords sont en vie 
	BEQ	DERN_C
	ADD.B	#1,D1
	MOVE.B	#0,(A0)+
	BRA 	SAUT_C
	
DERN_C:
	MOVE.B	#1,(A0)+
	
	BRA	AUTRE_L
	
	

DERN_L:				**	Met 1 sur la derniere ligne
	MOVE.L	#15,D1
	
LINE_1B:
	MOVE.B	#1,(A0)+
	DBRA	D1,LINE_1B
	
	
	
	
	***********************************************AJOUT_RANDOM ********************************
	
	
	

BOUCLE_RANDOM:					
	
		
	MOVE.L	#0,D1				
	MOVE.L	#TAB,A0
	ADD.L	#16,A0
	MOVE.W	#14,D2		**SE DEPLACE SUR LA LIGNE
	MOVE.W	#14,D3		**SE DEPLACE EN COLONNE
	


BOUCLE_L_RANDOM:
	JSR	TIME_RANDOM	** Renvoi un temps pour separer les actions aleatoires sinon trop rapide et donc pas d'aleatoire cas temps egaux
	ADD.L	#1,A0	
	JSR	RANDOM		** Effectue l'aleatoire
	JSR	AFFECTATION
	CMP.W	#0,D2
	BEQ	BOUCLE_C_RANDOM
	SUB.W	#1,D2	
	BRA	BOUCLE_L_RANDOM


BOUCLE_C_RANDOM:		
	SUB.W	#1,D3
	MOVE.W	#14,D2
	ADD.L	#1,A0	
	CMP.W	#0,D3	
	BEQ	BOUCLE_TEST
	BRA	BOUCLE_L_RANDOM
	
	
RANDOM:
	JSR	GET_TIME
	MOVE.L	#0,D4
	MOVE.B	D1,D4
	ADD.B	#20,D4
	MULU	#4,D4
	DIVU	#6,D4	
	LSR.L	#6,D4
	MOVE.B	D4,D1	
	RTS
	
AFFECTATION:			** Met 1 si l'aleatoire dit qu'il faut en mettre sinon rien ne se passe car les cellules sont deja a 0
	CMP.B	#0,D1
	BNE	ARRET
	MOVE.B	#1,(A0)
	RTS	
	
		
	
		
			*******************************************AFFICHAGE******************************

	
	




				***************BOUCLES PARCOURANT POUR AFFICHAGE**************

BOUCLE:		

	MOVE.L	#0,D1				
	MOVE.L	#TAB,A0
	ADD.L	#16,A0
	MOVE.W	#14,D2	******SE DEPLACE SUR LA LIGNE
	MOVE.W	#14,D3	******SE DEPLACE EN COLONNE
	
	
	
	

	


BOUCLE_L:
	
	ADD.L	#1,A0	
	JSR	AJOUT_VIE		** Colore la case en fonction du nombre "indique dans sa case" 
	CMP.W	#0,D2
	BEQ	BOUCLE_C
	SUB.W	#1,D2	
	BRA	BOUCLE_L


BOUCLE_C:
	SUB.W	#1,D3
	MOVE.W	#14,D2
	ADD.L	#1,A0	
	CMP.W	#0,D3	
	BEQ	BOUCLE_NEW_GENE		** Envoie a la creation de la generation suivante 	
	BRA	BOUCLE_L
	RTS
	
	
	

	
*************************************BOUCLE POUR TEST**************************



	

BOUCLE_TEST:
	
	JSR	TIME 			** Permet de separer les generations avec un temps defini
	MOVE.L	#0,D4	
	MOVE.L	#0,D1				
	MOVE.L	#TAB,A0
	ADD.L	#17,A0
	MOVE.L	#0,D2
	MOVE.L	#0,D3
	MOVE.B	#15,D2
	MOVE.B	#14,D3
	


BOUCLE_L_TEST:
	MOVE.B	#0,D4
	
	
	SUB.B	#1,D2
	CMP.B	#0,D2
	BEQ	BOUCLE_C_TEST
	JSR	CHECK			** "Regarde ce qui se passe autour de la cellule" 
	JSR	OPERATION
	ADD.L	#1,A0
	BRA	BOUCLE_L_TEST




BOUCLE_C_TEST:
	SUB.B	#1,D3
	MOVE.B	#15,D2
	ADD.L	#2,A0	
	CMP.B	#0,D3	
	BEQ	BOUCLE
	BRA	BOUCLE_L_TEST


	
		



					***************TEST DU NOMBRE DE VIE AUTOUR**************

CHECK:
	SUB.L	#17,A0 	******** POSITIONNE A0 EN HAUT A GAUCHE AVEC DESCENTE EN "S"
	MOVE.B 	(A0),D1
	JSR	VIE_MORT
	ADD.L	#1,A0		*HAUT MILIEU
	MOVE.B 	(A0),D1
	JSR	VIE_MORT
	ADD.L	#1,A0		*HAUT DROIT
	MOVE.B 	(A0),D1
	JSR	VIE_MORT
	ADD.L	#16,A0		*MILIEU  DROIT
	MOVE.B 	(A0),D1
	JSR	VIE_MORT
	SUB.L	#2,A0		*MILIEU GAUCHE
	MOVE.B 	(A0),D1
	JSR	VIE_MORT
	ADD.L	#16,A0		*BAS GAUCHE
	MOVE.B 	(A0),D1
	JSR	VIE_MORT
	ADD.L	#1,A0		*BAS MILIEU
	MOVE.B 	(A0),D1
	JSR	VIE_MORT
	ADD.L	#1,A0		*BAS GAUCHE
	MOVE.B 	(A0),D1
	JSR	VIE_MORT
	SUB.L	#17,A0		*REMET A0 POSITION INITIALE	
	MOVE.B	(A0),D1
	
	RTS
	

COMPTEUR:			** Met le nombre de cellules adjacentes dans D4
	ADD.B	#1,D4
	RTS

VIE_MORT:			** Si la cellule a deja un 1 ou un 2 dans sa case on appelle compteur pour ajouter 1 

	CMP.B	#1,D1
	BEQ	COMPTEUR
	CMP.B	#2,D1
	BEQ	COMPTEUR
	RTS





OPERATION:			** On fait des operations differentes si c'est une cellule vivante ou morte et on le differencie dans cette action
	
	CMP.B	#1,D1
	BEQ	VIE
	CMP.B	#0,D1
	BEQ	DIE

	

VIE:				** Si la cellule a plus de 3 ou moins que 2 cellules adjacentes elle meurt 			
	CMP	#3,D4
	BGT	MOURRANTE
	CMP	#2,D4
	BLT	MOURRANTE
	

	
	RTS



DIE:				** Si la cellule a 3  cellules adjacentes elle est mise en statut naissante
	CMP	#3,D4
	BEQ	NAISSANTE
	RTS

MOURRANTE:			** Met 2 dans les cellules mourantes 
	MOVE.B	#2,(A0)
	RTS
	
NAISSANTE:
	MOVE.B	#3,(A0)		** Met 3 dans les cellules naissantes
	RTS	
	



BOUCLE_NEW_GENE:		** Boucle qui va servir pour la generation suivante
				
	MOVE.L	#TAB,A0
	ADD.L	#17,A0
	MOVE.B	#15,D2
	MOVE.B	#14,D3
	


BOUCLE_L_NEW_GENE:
	MOVE.B	#0,D4	
	SUB.B	#1,D2
	CMP.B	#0,D2
	BEQ	BOUCLE_C_NEW_GENE
	JSR	GENESE		** Check l'etat de la cellule pour changer le chiffre si elle va naitre ou mourir 
	ADD.L	#1,A0
	BRA	BOUCLE_L_NEW_GENE




BOUCLE_C_NEW_GENE:
	SUB.B	#1,D3
	MOVE.B	#15,D2
	ADD.L	#2,A0	
	CMP.B	#0,D3
	BEQ	BOUCLE_TEST
	BRA	BOUCLE_L_NEW_GENE




GENESE:				** Si la cellule a 3 elle va naitre si elle a 2 elle va mourir
	MOVE.B	(A0),D1
	CMP.B	#3,D1
	BEQ	NAIT
	CMP.B	#2,D1
	BEQ	MEURT
	RTS



NAIT:				** Met 1  si elle nait
	MOVE.B	#1,(A0)
	RTS



MEURT:				** Met 0  si elle meurt
	MOVE.B	#0,(A0)
	RTS
	
	
	
	
	RTS	
	
	
	

TIME:				** Recupere le temps pour faire par la suite une boucle pour separer les affichages 
	
	JSR	GET_TIME
	MOVE.L	D1,D4
	ADD.L	#100,D4

	
	
	
CLOCK:				** Compare les temps entre D1 et D4 tant que d1>d4 on recommence	
		
	JSR	GET_TIME
	CMP.L	D1,D4
	BGT	CLOCK_DEMI	
	RTS
	
CLOCK_DEMI:	
	JSR	STATUS_TEST	** Test le statut cad si un bouton a ete utilise
	BRA	CLOCK
	
STATUS_TEST:
	JSR	TEST_ENFONCE	** Regarde quel bouton utilise si l'action a ete faite
	CMP.W	#1,STATUS
	BEQ	STATUS_1
	CMP.W	#2,STATUS
	BEQ	STATUS_2	
	CMP.W	#3,STATUS
	BEQ	STATUS_3
	RTS	
	
RAZ_STATUS:
	MOVE.W	#0,STATUS	** Remet le statut a 0
	RTS
	
STATUS_2:******CONTINUE*****
	JSR	RAZ_STATUS
	BRA	CLOCK	
	
	
STATUS_1:******PAS A PAS*****
	MOVE.W	#3,STATUS		
	RTS
					
		
STATUS_3:******PAUSE****	
	
	
	JSR	TEST_ENFONCE
	CMP.W	#1,STATUS
	BEQ	STATUS_1
	CMP.W	#2,STATUS
	BEQ	STATUS_TEST
	CMP.W	#3,STATUS	
	BEQ	STATUS_3
	RTS
	
	
TIME_RANDOM:					** separe le temps pour la boucle random
	JSR	GET_TIME
	MOVE.L	D1,D4
	ADD.L	#2,D4
CLOCK_RANDOM:	
	JSR	GET_TIME
	CMP.L	D1,D4
	BGT	CLOCK_RANDOM	
	RTS









*****************INITIALISATION DU FOND EN BLANC, BOUTON ET LEGENDE***********************
GRILLE:	
	MOVE.L	#$02840320,D1
	JSR	RESOLUTION
	MOVE.L	#$0099FFFF,D1
	JSR	SET_FILL_COLOR
	MOVE.L	#$0099FFFF,D1
	JSR	SET_PEN_COLOR
	MOVE.W	#0,D1 		*X1
	MOVE.W	#0,D2		*Y1
	MOVE.W	#$320,D3	*X2
	MOVE.W	#$320,D4	*Y2
	JSR	DRAW_FILL_RECT
	
BOUTON_PAS_A_PAS:
	MOVE.L	#$000000FF,D1
	JSR	SET_FILL_COLOR
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.W	#20,D1 		*X1
	MOVE.W	#700,D2		*Y1
	MOVE.W	#100,D3		*X2
	MOVE.W	#740,D4		*Y2
	JSR	DRAW_FILL_RECT
	MOVE.W	#25,D1 		*X1
	MOVE.W	#710,D2		*Y1
	MOVE.L	#B1,A1
	JSR	DRAW_STRING

	
BOUTON_CONTINUE:
	MOVE.L	#$000000FF,D1
	JSR	SET_FILL_COLOR
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.W	#120,D1 	*X1
	MOVE.W	#700,D2		*Y1
	MOVE.W	#200,D3		*X2
	MOVE.W	#740,D4		*Y2
	JSR	DRAW_FILL_RECT
	MOVE.W	#122,D1 	*X1
	MOVE.W	#710,D2		*Y1
	MOVE.L	#B2,A1
	JSR	DRAW_STRING
	
	
BOUTONS_STOP:
	MOVE.L	#$000000FF,D1
	JSR	SET_FILL_COLOR
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.W	#220,D1 	*X1
	MOVE.W	#700,D2		*Y1
	MOVE.W	#300,D3		*X2
	MOVE.W	#740,D4		*Y2
	JSR	DRAW_FILL_RECT	
	MOVE.W	#222,D1 	*X1
	MOVE.W	#710,D2		*Y1
	MOVE.L	#B3,A1
	JSR	DRAW_STRING
	

LEGENDE_VIE	

	MOVE.L	#$00006400,D1
	JSR	SET_FILL_COLOR
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.W	#340,D1 	*X1
	MOVE.W	#660,D2		*Y1
	MOVE.W	#390,D3		*X2
	MOVE.W	#700,D4		*Y2
	JSR	DRAW_FILL_ELLIPSE
	MOVE.W	#340,D1 	*X1
	MOVE.W	#710,D2		*Y1
	MOVE.L	#C1,A1
	JSR	DRAW_STRING
	
LEGENDE_MORTE	

	MOVE.L	#$0030FFFF,D1
	JSR	SET_FILL_COLOR
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.W	#410,D1 	*X1
	MOVE.W	#660,D2		*Y1
	MOVE.W	#460,D3		*X2
	MOVE.W	#700,D4		*Y2
	JSR	DRAW_FILL_ELLIPSE	
	MOVE.W	#417,D1 	*X1
	MOVE.W	#710,D2		*Y1
	MOVE.L	#C2,A1
	JSR	DRAW_STRING
	
LEGENDE_NAISSANTE	

	MOVE.L	#$008B8B00,D1
	JSR	SET_FILL_COLOR
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.W	#480,D1 	*X1
	MOVE.W	#660,D2		*Y1
	MOVE.W	#530,D3		*X2
	MOVE.W	#700,D4		*Y2
	JSR	DRAW_FILL_ELLIPSE	
	MOVE.W	#490,D1 	*X1
	MOVE.W	#710,D2		*Y1
	MOVE.L	#C3,A1
	JSR	DRAW_STRING	

LEGENDE_MOURANTE	

	MOVE.L	#$00008CFF,D1
	JSR	SET_FILL_COLOR
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.W	#550,D1 	*X1
	MOVE.W	#660,D2		*Y1
	MOVE.W	#600,D3		*X2
	MOVE.W	#700,D4		*Y2
	JSR	DRAW_FILL_ELLIPSE	
	MOVE.W	#555,D1 	*X1
	MOVE.W	#710,D2		*Y1
	MOVE.L	#C4,A1
	JSR	DRAW_STRING	
**************************1ERE LIGNE ET INITIALISATION DU QUADRILLAGE EN NOIR**********************	
	
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR	
	MOVE	#644,D1						**POUR REDUIRE BORD ENLEVER 80 
	MOVE	#644,D3
	MOVE	#0,D2
	MOVE	#644,D4
	JSR	DRAW_LINE
	
**************************QUADRILLE EN NOIR DE DROITE A GAUCHE****************************
	
QUADRILLAGE_C:		
	CMP.W	#0,D1
	BEQ	INTERMED
	SUB.W	#46,D1
	SUB.W	#46,D3
	JSR	DRAW_LINE
	BRA 	QUADRILLAGE_C 
	
*************************INITIALISATION DES CONSTANTES POUR LE QUADRILLAGE DE LIGNE****************
	
INTERMED:
	MOVE	#0,D1
	MOVE	#644,D3
	MOVE	#644,D2
	MOVE	#644,D4	
	JSR	DRAW_LINE
	
**************************QUADRILLE EN NOIR DE BAS EN HAUT****************************
	
QUADRILLAGE_L:
	CMP.W	#0,D4
	BEQ	ARRET
	SUB.W	#46,D2
	SUB.W	#46,D4
	JSR	DRAW_LINE
	BRA	QUADRILLAGE_L

	
*************************************AJOUT DES CELLULES DE VIE(couleur)****************************
AJOUT_VIE:
	MOVE.B	(A0),D1
	
	CMP.B	#1,D1
	BNE	RAZ_POINT
	MOVE.W	D2,SAVE_D2
	MOVE.W	D3,SAVE_D3
	SUB.W	D2,CELLULE_X
	SUB.W	D3,CELLULE_Y
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.L	#$00006400,D1	***********VERT EST VIVANTE*************
	JSR	SET_FILL_COLOR
	
	MOVE.W	CELLULE_X,D1
	MULU.W	#46,D1	
	MOVE.W	CELLULE_Y,D2	
	MULU.W	#46,D2
	MOVE.W	D1,D3
	ADD.W	#46,D3
	MOVE.W	D2,D4
	ADD.W	#46,D4
	JSR	DRAW_FILL_ELLIPSE
	MOVE.W	SAVE_D2,D2
	MOVE.W	SAVE_D3,D3
	MOVE.W	#14,CELLULE_X
	MOVE.W	#14,CELLULE_Y
	RTS


RAZ_POINT:
	CMP.B	#0,D1
	BNE	COLOR_NAIT
	MOVE.W	D2,SAVE_D2
	MOVE.W	D3,SAVE_D3
	SUB.W	D2,CELLULE_X
	SUB.W	D3,CELLULE_Y
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.L	#$0099FFFF,D1	***********EST MORT*************
	JSR	SET_FILL_COLOR
	
	MOVE.W	CELLULE_X,D1
	MULU.W	#46,D1	
	MOVE.W	CELLULE_Y,D2	
	MULU.W	#46,D2
	MOVE.W	D1,D3
	ADD.W	#46,D3
	MOVE.W	D2,D4
	ADD.W	#46,D4
	JSR	DRAW_FILL_ELLIPSE
	MOVE.W	SAVE_D2,D2
	MOVE.W	SAVE_D3,D3
	MOVE.W	#14,CELLULE_X
	MOVE.W	#14,CELLULE_Y
	RTS
	
COLOR_NAIT:
	CMP.B	#3,D1
	BNE	COLOR_MORT
	MOVE.W	D2,SAVE_D2
	MOVE.W	D3,SAVE_D3
	SUB.W	D2,CELLULE_X
	SUB.W	D3,CELLULE_Y
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.L	#$008B8B00,D1	***********BLEU VIENT DE NAITRE*************
	JSR	SET_FILL_COLOR
	
	MOVE.W	CELLULE_X,D1
	MULU.W	#46,D1	
	MOVE.W	CELLULE_Y,D2	
	MULU.W	#46,D2
	MOVE.W	D1,D3
	ADD.W	#46,D3
	MOVE.W	D2,D4
	ADD.W	#46,D4
	JSR	DRAW_FILL_ELLIPSE
	MOVE.W	SAVE_D2,D2
	MOVE.W	SAVE_D3,D3
	MOVE.W	#14,CELLULE_X
	MOVE.W	#14,CELLULE_Y
	RTS
	
COLOR_MORT:
	MOVE.W	D2,SAVE_D2
	MOVE.W	D3,SAVE_D3
	SUB.W	D2,CELLULE_X
	SUB.W	D3,CELLULE_Y
	MOVE.L	#$00000000,D1
	JSR	SET_PEN_COLOR
	MOVE.L	#$00008CFF,D1	***********ORANGE SE MEURT*************
	JSR	SET_FILL_COLOR
	
	MOVE.W	CELLULE_X,D1
	MULU.W	#46,D1	
	MOVE.W	CELLULE_Y,D2	
	MULU.W	#46,D2
	MOVE.W	D1,D3
	ADD.W	#46,D3
	MOVE.W	D2,D4
	ADD.W	#46,D4
	JSR	DRAW_FILL_ELLIPSE
	MOVE.W	SAVE_D2,D2
	MOVE.W	SAVE_D3,D3
	MOVE.W	#14,CELLULE_X
	MOVE.W	#14,CELLULE_Y
	RTS
**********************************SOURIS************************************	
	
TEST_ENFONCE:
	Move.l	#0,D1
	JSR	GET_MOUSE
	AND.B	#%00000001,D0
	JSR	RECUP_POS_SOURIS
	CMP.B	#1,d0
	BEQ	TEST_X_DEBUT_PAS
	rts

RECUP_POS_SOURIS:  ************DONNE LA POSITION DE LA SOURIS************
	move.w	d1,d6 *x
	swap	d1
	move.w	d1,d7 *y

	RTS



TEST_X_DEBUT_PAS:	*********POUR BOUTON  pas a pas*****	
	
	cmp.w	#20,d6
	bge	TEST_X_FIN_PAS
	RTS
	
TEST_X_FIN_PAS:

	cmp.w	#100,d6
	ble	TEST_Y_DEBUT_PAS
	BRA	TEST_X_DEBUT_CONTINUE

TEST_Y_DEBUT_PAS:	
	cmp.w	#700,d7
	bge	TEST_Y_FIN_PAS
	RTS
	
TEST_Y_FIN_PAS:

	cmp.w	#740,d7
	ble	RELACHE_PAS
	RTS

TEST_X_DEBUT_CONTINUE:
	cmp.w	#120,d6
	bge	TEST_X_FIN_CONTINUE
	RTS

TEST_X_FIN_CONTINUE:
	cmp.w	#200,d6
	ble	TEST_Y_DEBUT_CONTINUE
	BRA	TEST_X_DEBUT_STOP
	
TEST_Y_DEBUT_CONTINUE:	
	cmp.w	#700,d7
	bge	TEST_Y_FIN_CONTINUE
	RTS
	
TEST_Y_FIN_CONTINUE:	
	cmp.w	#740,d7
	ble	RELACHE_CONTINUE
	RTS
TEST_X_DEBUT_STOP:	
	cmp.w	#220,d6
	bge	TEST_X_FIN_STOP
	RTS
	
TEST_X_FIN_STOP:	
	cmp.w	#300,d6
	ble	TEST_Y_DEBUT_STOP
	RTS
	
TEST_Y_DEBUT_STOP:	
	cmp.w	#700,d7
	bge	TEST_Y_FIN_STOP
	RTS
	
TEST_Y_FIN_STOP:	
	cmp.w	#740,d7
	ble	RELACHE_STOP
	RTS
	
                         **********************************RELACHE***********************
RELACHE_PAS:
	Move.l	#0,D1
	JSR	GET_MOUSE
	AND.B	#%00000001,D0
	JSR	RECUP_POS_SOURIS
	CMP.B	#0,d0
	BEQ	TEST_X_RELACHE_DEBUT_PAS
	rts


TEST_X_RELACHE_DEBUT_PAS:		
	
	cmp.w	#20,d6
	bge	TEST_X_RELACHE_FIN_PAS
	RTS
	
TEST_X_RELACHE_FIN_PAS:

	cmp.w	#100,d6
	ble	TEST_Y_RELACHE_DEBUT_PAS
	RTS

TEST_Y_RELACHE_DEBUT_PAS:	
	cmp.w	#700,d7
	bge	TEST_Y_RELACHE_FIN_PAS
	RTS
	
TEST_Y_RELACHE_FIN_PAS:

	cmp.w	#740,d7
	ble	STATUS_PAS
	RTS


RELACHE_CONTINUE:
	Move.l	#0,D1
	JSR	GET_MOUSE
	AND.B	#%00000001,D0
	JSR	RECUP_POS_SOURIS
	CMP.B	#0,d0
	BEQ	TEST_X_RELACHE_DEBUT_CONTINUE
	rts


TEST_X_RELACHE_DEBUT_CONTINUE:
	
	cmp.w	#120,d6
	bge	TEST_X_RELACHE_FIN_CONTINUE
	RTS
	
TEST_X_RELACHE_FIN_CONTINUE:

	cmp.w	#200,d6
	ble	TEST_Y_RELACHE_DEBUT_CONTINUE
	RTS

TEST_Y_RELACHE_DEBUT_CONTINUE:	
	cmp.w	#700,d7
	bge	TEST_Y_RELACHE_FIN_CONTINUE
	RTS
	
TEST_Y_RELACHE_FIN_CONTINUE:

	cmp.w	#740,d7
	ble	STATUS_CONTINUE
	RTS


RELACHE_STOP:
	Move.l	#0,D1
	JSR	GET_MOUSE
	AND.B	#%00000001,D0
	JSR	RECUP_POS_SOURIS
	CMP.B	#0,d0
	BEQ	TEST_X_RELACHE_DEBUT_STOP
	rts


TEST_X_RELACHE_DEBUT_STOP:		
	
	cmp.w	#220,d6
	bge	TEST_X_RELACHE_FIN_STOP
	RTS
	
TEST_X_RELACHE_FIN_STOP:

	cmp.w	#300,d6
	ble	TEST_Y_RELACHE_DEBUT_STOP
	RTS

TEST_Y_RELACHE_DEBUT_STOP:	
	cmp.w	#700,d7
	bge	TEST_Y_RELACHE_FIN_STOP
	RTS
	
TEST_Y_RELACHE_FIN_STOP:

	cmp.w	#740,d7
	ble	STATUS_STOP
	RTS


STATUS_PAS:
 		*******   STATUS: 1=PAS////2=CONTINUE////3=STOP    ******	

	MOVE.W	#1,STATUS
	RTS
	
STATUS_CONTINUE:
	

	MOVE.W	#2,STATUS
	RTS
	
STATUS_STOP:

	MOVE.W	#3,STATUS
	RTS
















ARRET:
	RTS

	rts	
	
	
	
	
	
	










	
		
	org	$2000

CELLULE_X:	DC.W	14
CELLULE_Y:	DC.W	14
SAVE_D2:	DC.W	0
SAVE_D3:	DC.W	0
C1:	DC.W	'VIVANTE',0
C2:	DC.W	'MORTE',0	
C3:	DC.W	'NAIT',0
C4:	DC.W	'MEURT',0
B1:	DC.W	'PAS A PAS',0
B2:	DC.W	' NON STOP',0	
B3:	DC.W	'   STOP',0
	
STATUS:	DC.W	0
TAB:	DS.B	256







	INCLUDE 'Bibgraph.X68'	
	INCLUDE 'Biblio.X68'
	INCLUDE 'Bibperiph.X68'
	END	$1000	



























*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~8~
