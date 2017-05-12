// Agent Valet in project Garage.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true
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

+msg(X)[source(A)] : X\==[]
<-
!route(X).

+!route([H|T]): H == "U" <- up; !route(T). 
+!route([H|T]): H == "D" <- down; !route(T). 
+!route([H|T]): H == "L" <- left; !route(T). 
+!route([H|T]): H == "R" <- right; !route(T).
+!route([H|T]): true <- print("Cant do ", H); !route(T).
