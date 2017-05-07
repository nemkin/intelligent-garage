// Agent Navigator in project Garage.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+obstacle(X,Y) : true <- .print("obstacle: ",X,",",Y).
+~obstacle(X,Y) : true <- .print("walkable: ",X,",",Y).
