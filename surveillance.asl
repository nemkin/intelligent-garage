// Agent Surveillance in project Garage.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true 
<-
.print("hello world").


+gate(X,Y) : true
<-
.print("gate: ",X,",",Y).


+takenparkingspot(X,Y) : true
<-
.print("takenparkingspot ",X,",",Y).


+emptyparkingspot(X,Y) : true
<-
.print("emptyparkingspot ",X,",",Y).


+carArrived(X,Y) : emptyparkingspot(U,V)
<-
.print("carArrived ",X,",",Y);
!callValet(X,Y,U,V).


+carLeaving(X,Y) : true
<-
.print("carLeaving ",X,",",Y).


+!callValet(X,Y,U,V) : true 
<- 
.print("Calling valet to pick up the car from (",X,",",Y,") and carry it to the parking spot at (",U,",",V,")");
.send(valet,achieve,goOnPosition(X,Y,U,V)).
