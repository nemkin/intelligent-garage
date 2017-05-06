// Agent Navigator in project Garage.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+road(X,Y) : true <- .print("road: ",X,",",Y).
+wall(X,Y) : true <- .print("wall: ",X,",",Y).
+parkingspot(X,Y) : true <- .print("parkingspot: ",X,",",Y).
+gate(X,Y) : true <- .print("gate: ",X,",",Y).
+none(X,Y) : true <- .print("none: ",X,",",Y).
