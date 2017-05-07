// Agent Surveillance in project Garage.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.");
				  +carArrived(99,99).
+!callValet(X,Y,U,V) : true <- .print("surv calling valet with destination koords");
							   .send(valet,achieve,goOnPosition(X,Y,U,V)).

+gate(X,Y) : true <- .print("gate: ",X,",",Y).
+carArrived(X,Y) : true <- .print("carArrived: ",X,",",Y);
							!callValet(X,Y,U,V).
+parkingspot(X,Y) : true <- .print("parkingspot: ",X,",",Y).
+carLeaving(X,Y) : true <- .print("carLeaving: ",X,",",Y).
