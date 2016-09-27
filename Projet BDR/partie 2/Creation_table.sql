CREATE TABLE Personnel ( 
	id_per 		INT ,
	nom			CHAR(30),
	prenom		CHAR(30),
	numeroSS	INT ,
	datenai		DATE ,
	PRIMARY KEY 	(id_per)
); 

CREATE TABLE Vehicule ( 
	id_veh			INT  ,
	PRIMARY KEY		(id_veh)
);

CREATE TABLE Bus ( 
	id_bus		INT ,
	plaque		CHAR(10),
	typemoteur	CHAR(30),
	typebus		INT,
	id_veh		INT ,
	PRIMARY KEY	(id_bus),
	FOREIGN KEY	(id_veh) REFERENCES Vehicule (id_veh)
);

CREATE TABLE Chauffeur ( 
	Datepermis	DATE,		
	id_per 		INT ,
	id_veh		INT ,
	id_bus		INT ,
	FOREIGN KEY	(id_per) 	REFERENCES Personnel (id_per) ,
	FOREIGN KEY	(id_veh)	REFERENCES Vehicule (id_veh),
	FOREIGN KEY	(id_bus)	REFERENCES Bus (id_bus)
);

CREATE TABLE Technicien (
	id_tec		INT, 
	id_per 		INT  ,
	id_veh		INT  ,
	PRIMARY KEY		(id_tec),
	FOREIGN KEY	(id_per)  REFERENCES Personnel (id_per) ,
	FOREIGN KEY	(id_veh)	REFERENCES Vehicule (id_veh)
);

CREATE TABLE Administrateur ( 
	id_per 		INT ,
	FOREIGN KEY	(id_per)  REFERENCES Personnel (id_per) 
);

CREATE TABLE Centre_reparation ( 
	id_cen 			INT,
	id_tec			INT,
	id_bus			INT ,
	piece	 		CHAR(30),
	PRIMARY KEY		(id_cen),
	FOREIGN KEY		(id_tec)	REFERENCES Technicien (id_tec),
	FOREIGN KEY		(id_bus)	REFERENCES Bus (id_bus)
);

CREATE TABLE Metro ( 
	id_met		INT,
	id_veh		INT,
	PRIMARY KEY (id_met),	
	FOREIGN KEY	(id_veh) REFERENCES Vehicule (id_veh)
);

CREATE TABLE Ligne (
	id_lig		INT  ,
	arret		CHAR(30),
	terminus	CHAR(30),
	temps		INT,
	id_bus		INT,
	PRIMARY KEY 	(id_lig),
	FOREIGN KEY	(id_bus)  REFERENCES Bus (id_bus)
);

CREATE TABLE Depot (
	id_dep 		INT  ,
 	adresse		CHAR(30),
	id_veh		INT  ,
	id_lig		INT  ,
	PRIMARY KEY 	(id_dep),
	FOREIGN KEY	(id_veh)  REFERENCES Vehicule (id_veh),
	FOREIGN KEY	(id_lig)  REFERENCES Ligne (id_lig)
);	


CREATE TABLE Client (
	id_cli		INT  ,
	nom			CHAR(30),
	prenom		CHAR(30),
	datenaiss	DATE,
	adresse		CHAR(30),
	moypaiment  CHAR(30),
	dateabo		DATE,
	PRIMARY KEY 	(id_cli)
);

CREATE TABLE Deplacement (
	id_deplacement	INT  ,	
	dated			DATE  ,
	id_lig 			INT,
	id_bus 			INT,
	PRIMARY KEY		(id_deplacement),
	FOREIGN KEY 	(id_lig) 			REFERENCES  Ligne (id_lig)	,				
	FOREIGN KEY 	(id_bus)	REFERENCES Bus (id_bus)			
);

CREATE TABLE Sedeplacer (
	id_cli				INT  ,	
	id_deplacement		INT  ,
	FOREIGN KEY 		(id_cli) 			REFERENCES  Client (id_cli)	,				
	FOREIGN KEY 		(id_deplacement)	REFERENCES Deplacement (id_deplacement)			
);
	 
CREATE TABLE Pointsvente (
	id_point	INT  ,
	nom			CHAR(30),
	prenom		CHAR(30),
	adresse		CHAR(30),			
	PRIMARY KEY 	(id_point)
);

CREATE TABLE Abonnement (
	id_abo		INT  ,
	datea		DATE ,
	id_cli		INT  ,
	type 		CHAR(30) check (type in ('mensuel','trimestriel','annuel') ),
	prix		INT ,
	PRIMARY KEY 	(id_abo) ,
	FOREIGN KEY 	(id_cli) REFERENCES Client (id_cli)
);

CREATE TABLE Vend (
	id_point	INT  ,	
	id_abo		INT  ,
	FOREIGN KEY 	(id_point) REFERENCES  Pointsvente (id_point)	,				
	FOREIGN KEY 	(id_abo)	REFERENCES Abonnement (id_abo)			
);






				


