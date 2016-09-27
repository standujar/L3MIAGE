
insert into Personnel(id_per,nom,prenom,numeroSS,datenai)
values (089903,'MOREL','Luca',15402560250052,to_date('26/02/1954','dd/mm/yyyy'));

insert into Personnel(id_per,nom,prenom,numeroSS,datenai)
values (089845,'LAMBERT','Camille',26405520250052,to_date('20/05/1964','dd/mm/yyyy'));

insert into Personnel(id_per,nom,prenom,numeroSS,datenai)
values (089804,'BERTRAND','Enzo',17708430250052 ,to_date('18/08/1977','dd/mm/yyyy'));

insert into Personnel(id_per,nom,prenom,numeroSS,datenai)
values (089914,'BONNET','Lea',26802500250052,to_date('20/02/1968','dd/mm/yyyy'));

insert into Personnel(id_per,nom,prenom,numeroSS,datenai)
values (089953,'MARTINEZ','SAMI',18504800250052,to_date('05/04/1985','dd/mm/yyyy'));

insert into Personnel(id_per,nom,prenom,numeroSS,datenai)
values (089922,'YOUNESS','Amine',19501800250052,to_date('26/01/1995','dd/mm/yyyy'));

insert into Personnel(id_per,nom,prenom,numeroSS,datenai)
values (089999,'ANDUJAR','Stan',19311800250052,to_date('11/11/1993','dd/mm/yyyy'));

insert into Personnel(id_per,nom,prenom,numeroSS,datenai)
values (089980,'SOUIKI','Ziad',19512690250052,to_date('10/12/1995','dd/mm/yyyy'));

insert into Vehicule(id_veh)
values (1);

insert into Vehicule(id_veh)
values (2);

insert into Bus(id_bus,plaque,typemoteur,typebus,id_veh)
values (1000,'AA4440','OM',0,1);

insert into Bus(id_bus,plaque,typemoteur,typebus,id_veh)
values (1001,'AA4441','OM',1,1);

insert into Bus(id_bus,plaque,typemoteur,typebus,id_veh)
values (1002,'AA4442','OM',1,1);

insert into Bus(id_bus,plaque,typemoteur,typebus,id_veh)
values (1003,'AA4443','OM',1,1);


insert into Chauffeur(Datepermis,id_per,id_veh,id_bus)
values (to_date('10/12/2016','dd/mm/yyyy'),089903,1,1000);

insert into Chauffeur(Datepermis,id_per,id_veh,id_bus)
values (to_date('10/10/2017','dd/mm/yyyy'),089845,1,1001);

insert into Chauffeur(Datepermis,id_per,id_veh,id_bus)
values (to_date('11/10/2017','dd/mm/yyyy'),089804,1,1002);

insert into Chauffeur(Datepermis,id_per,id_veh,id_bus)
values (to_date('13/01/2016','dd/mm/yyyy'),089914,1,1003);

insert into Technicien(id_tec,id_per,id_veh)
values (770,089953,1);

insert into Technicien(id_tec,id_per,id_veh)
values (771,089922,1);

insert into Technicien(id_tec,id_per,id_veh)
values (772,089999,2);

insert into Administrateur(id_per)
values (089980);

insert into Centre_reparation(id_cen,id_tec,id_bus,piece)
values (10,770,1000,'pneu');

insert into Centre_reparation(id_cen,id_tec,id_bus,piece)
values (11,771,1001,'vitre');

insert into Metro(id_met,id_veh)
values (2000,2);

insert into Metro(id_met,id_veh)
values (2001,2);

insert into Ligne(id_lig,arret,terminus,temps,id_bus)
values (840,'arret 1','terminus 1',10,1001);

insert into Ligne(id_lig,arret,terminus,temps,id_bus)
values (841,'arret 2','terminus 2',10,1002);

insert into Ligne(id_lig,arret,terminus,temps,id_bus)
values (842,'arret 3','terminus 2',10,1003);

insert into Ligne(id_lig,arret,terminus,temps,id_bus)
values (843,'arret 4','terminus 2',10,1003);

insert into Depot(id_dep,adresse,id_veh,id_lig)
values (111111,'33 Rue Motte',1,840);

insert into Depot(id_dep,adresse,id_veh,id_lig)
values (111112,'5 Rue StLeu',1,841);

insert into Depot(id_dep,adresse,id_veh,id_lig)
values (111113,'13 Rue Archers',1,842);

insert into Client(id_cli,nom,prenom,datenaiss,adresse,moypaiment,dateabo)	
values (999990,'Pit','Irene',to_date('26/02/1997','dd/mm/yyyy'),'70 Rue des Jacobins', 'carte',to_date('20/11/2014','dd/mm/yyyy'));

insert into Client(id_cli,nom,prenom,datenaiss,adresse,moypaiment,dateabo)	
values (999991,'Lampard','Youc',to_date('26/02/1995','dd/mm/yyyy'),'74 Rue des Mars', 'carte',to_date('20/11/2014','dd/mm/yyyy'));

insert into Client(id_cli,nom,prenom,datenaiss,adresse,moypaiment,dateabo)	
values (999992,'Venderre','Melanie',to_date('26/12/1990','dd/mm/yyyy'),'71 Rue des Jacobins', 'carte',to_date('12/01/2015','dd/mm/yyyy'));

insert into Client(id_cli,nom,prenom,datenaiss,adresse,moypaiment,dateabo)	
values (999993,'vanmark','Thibot',to_date('20/04/1989','dd/mm/yyyy'),'70 Rue Motte', 'cheque',to_date('09/03/2013','dd/mm/yyyy'));

insert into Client(id_cli,nom,prenom,datenaiss,adresse,moypaiment,dateabo)	
values (999994,'Fitt','Irina',to_date('23/02/1991','dd/mm/yyyy'),'30 Rue Stleu', 'cheque',to_date('11/11/2014','dd/mm/yyyy') );

insert into Client(id_cli,nom,prenom,datenaiss,adresse,moypaiment,dateabo)	
values (999995,'Amir','Malik',to_date('26/02/1989','dd/mm/yyyy'),'22 Rue des Jacobins', 'cheque',to_date('27/01/2014','dd/mm/yyyy'));

insert into Client(id_cli,nom,prenom,datenaiss,adresse,moypaiment,dateabo)	
values (999996,'Boucar','Lea',to_date('22/10/1987','dd/mm/yyyy'),'2 Rue terrain', 'carte',to_date('11/01/2014','dd/mm/yyyy'));


insert into Deplacement(id_deplacement,dated,id_lig,id_bus)
values (0001,to_date('11/11/2014','dd/mm/yyyy'),840,1000);

insert into Deplacement(id_deplacement,dated,id_lig,id_bus)
values (0002,to_date('10/11/2014','dd/mm/yyyy'),841,1001);

insert into Deplacement(id_deplacement,dated,id_lig,id_bus)
values (0003,to_date('15/11/2014','dd/mm/yyyy'),842,1002);

insert into Deplacement(id_deplacement,dated,id_lig,id_bus)
values (0004,to_date('14/11/2014','dd/mm/yyyy'),840,1003);

insert into Deplacement(id_deplacement,dated,id_lig,id_bus)
values (0005,to_date('20/11/2014','dd/mm/yyyy'),841,1001);

insert into Deplacement(id_deplacement,dated,id_lig,id_bus)
values (0006,to_date('19/11/2014','dd/mm/yyyy'),842,1000);

insert into Deplacement(id_deplacement,dated,id_lig,id_bus)
values (0007,to_date('10/11/2014','dd/mm/yyyy'),841,1003);

insert into Deplacement(id_deplacement,dated,id_lig,id_bus)
values (0008,to_date('05/11/2014','dd/mm/yyyy'),840,1002);

insert into Sedeplacer(id_cli,id_deplacement)
values (999990,0001);

insert into Sedeplacer(id_cli,id_deplacement)
values (999990,0005);

insert into Sedeplacer(id_cli,id_deplacement)
values (999991,0006);

insert into Sedeplacer(id_cli,id_deplacement)
values (999992,0003);

insert into Sedeplacer(id_cli,id_deplacement)
values (999992,0001);

insert into Sedeplacer(id_cli,id_deplacement)
values (999993,0004);

insert into Sedeplacer(id_cli,id_deplacement)
values (999994,0008);

insert into Sedeplacer(id_cli,id_deplacement)
values (999995,0006);

insert into Sedeplacer(id_cli,id_deplacement)
values (999996,0007);


insert into Pointsvente(id_point,nom,prenom,adresse)
values (2220,'Bouchar','Luca','Rue Lorem');

insert into Pointsvente(id_point,nom,prenom,adresse)
values (2221,'Luxus','steph','Rue Pronam');

insert into Pointsvente(id_point,nom,prenom,adresse)
values (2222,'Good','Lamine','Rue Stleu');

insert into Pointsvente(id_point,nom,prenom,adresse)
values (2223,'Tira','Elise','Rue Motte');

insert into Abonnement(id_abo,id_cli,type,prix)
values (4440,999990,'mensuel',25);

insert into Abonnement(id_abo,id_cli,type,prix)
values (4441,999991,'mensuel',25);

insert into Abonnement(id_abo,id_cli,type,prix)
values (4442,999992,'trimestriel',70);

insert into Abonnement(id_abo,id_cli,type,prix)
values (4443,999993,'trimestriel',70);

insert into Abonnement(id_abo,id_cli,type,prix)
values (4444,999994,'trimestriel',70);

insert into Abonnement(id_abo,id_cli,type,prix)
values (4445,999995,'annuel',250);

insert into Abonnement(id_abo,id_cli,type,prix)
values (4446,999996,'annuel',250);

insert into Vend(id_point,id_abo)
values (2220,4440);

insert into Vend(id_point,id_abo)
values (2221,4441);

insert into Vend(id_point,id_abo)
values (2222,4442);

insert into Vend(id_point,id_abo)
values (2223,4443);

insert into Vend(id_point,id_abo)
values (2223,4443);

insert into Vend(id_point,id_abo)
values (2222,4443);



















































