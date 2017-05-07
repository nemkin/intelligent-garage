// Agent Valet in project Garage.mas2j

/* Initial beliefs and rules */
position(8,5).

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.");
				   !position(A,B).
+!position(A,B) : true <- .print("----------------------------------Itt vagyok: ",A,",",B).
+!getRoute(X,Y,U,V) : true <- .print("Asking for route from navigator agent");
							  .send(navigator,achieve,calculateRoute(X,Y,U,V)).
+!goOnPosition(X,Y,U,V) : position(A,B) <- .print("goOnPosition is in progress");
								  !getRoute(A,B,X,Y);
								  !getRoute(X,Y,U,V).
