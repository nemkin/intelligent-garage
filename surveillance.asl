// Agent Surveillance in project Garage.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+gate(X,Y) : true <- .print("gate: ",X,",",Y).
+parkingspot(X,Y) : true <- .print("parkingspot: ",X,",",Y).
+carArrived(X,Y) : true <- .print("carArrived: ",X,",",Y).
+carLeaving(X,Y) : true <- .print("carLeaving: ",X,",",Y).

