// Agent Valet in project Garage.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true
<-
.print("hello world");
up;
up;
left;
left.

+position(A,B) : true
<-
.print("position ",A,",",B).

+!getRoute(X,Y,U,V) : true
<-
.print("asking for route from navigator");
.send(navigator,achieve,calculateRoute(X,Y,U,V)).

+!goOnPosition(X,Y,U,V) : position(A,B)
<-
.print("goOnPosition is in progress");
!getRoute(A,B,X,Y);
!getRoute(X,Y,U,V).
