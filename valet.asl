// Agent Valet in project Garage.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : position(A,B)
<-
.print("hello world").

+position(A,B) : true
<-
.print("position ",A,",",B).

+!getRoute(X,Y,U,V) : true
<-
.print("asking for route from navigator");
.send(navigator,ask,route(X,Y,U,V)).

+!goOnPosition(X,Y,U,V) : position(A,B)
<-
.print("goOnPosition is in progress");
!getRoute(A,B,X,Y);
!getRoute(X,Y,U,V).

+msg(X)[source(A)] : A==navigator
<-
!route(X).

+!route([H|T]): true <- go(H); !route(T).
+!route(T) : T==[] & car <- dropcar; .print("Dropped car!"). 
+!route(T) : T==[] & ~car <- pickupcar; .print("Picked up car!"). 
