1) Quels sont les num�ros des clients qui ont pris un abonnement apr�s la date 01/01/2014 ?

select id_cli from Client where dateabo>= '01/01/2014' ;

2) Quels sont les noms des clients qui ont pris un abonnement entre la date 01/10/2014  et 01/01/2015 ?

select prenom , nom from Client where dateabo between '01/10/2014' and '01/01/2015 ' ;

3) Quels sont les noms des point de vente qui ont vendu des abonnements trimestriel ?

select distinct nom from Pointsvente p , Vend v , Abonnement a where a.type='trimestriel' and p.id_point=v.id_point and v.id_abo= a.id_abo ;

4) Quel sont les id des_bus qui sont conduit par un chauffeur dont le bus est en r�paration ?

select c.id_bus from Chauffeur c, Bus b, Centre_reparation r where c.id_bus=b.id_bus and b.id_bus= r.id_bus ;

5) Quels sont les noms des techniciens par ordre d�croissant de leur �ge. 

select nom from Personnel p, Technicien t where p.id_per=t.id_per  order by datenai desc ;

6) Quels sont les bus qui passe par l'arr�t = 1 ;

Select  distinct  id_bus from Bus  minus   Select  id_bus from(select id_bus,  arret from (select id_bus from
Bus),(select arret from Ligne  where arret = 'arret 1')  minus  select  id_bus,arret from Ligne) ;




 