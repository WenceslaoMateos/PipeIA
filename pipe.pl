%representar las piezas como una lista de huecos
%pieza([arriba, abajo, izquierda, derecha]).
%se va a dar un listado de piezas (en formato lista, aserto no porque es horroroso al backtracking)
%Tambien se debe dar un origen y un destino
%una vez cargado el programa y se va a resolver con eso
%limites del tablero
ubicacion_valida(X, Y) :-
    X > 0,
    Y > 0,
    X < 6,
    Y < 6.
%formato de pieza ubicada: pieza_ub(X, Y, [listado de orientaciones])

disponible(_, _, []).
disponible(X, Y, [pieza_ub(XA, YA, _) | _]) :-
    X = XA,
    Y = YA,
    !,
    fail.
disponible(X, Y, [_ | Cola]):-
    disponible(X, Y, Cola).

compatibles(izq, der).
compatibles(der, izq).
compatibles(arriba, abajo).
compatibles(abajo, arriba).

al_lado(X1, Y1, X2, Y2) :-
    X1 = X2,
    Y1 is Y2 + 1.
al_lado(X1, Y1, X2, Y2) :-
    X1 = X2,
    Y1 is Y2 - 1.
al_lado(X1, Y1, X2, Y2) :-
    X1 is X2 + 1,
    Y1 = Y2.
al_lado(X1, Y1, X2, Y2) :-
    X1 is X2 - 1,
    Y1 = Y2.

resolver(Origen, Destino, Piezas, Sol) :-
    res_aux(Origen, Destino, Piezas, [], Sol).

res_aux(pieza_ub(XO, YO, [BO]), pieza_ub(XD, YD, [BD]), _, Aux, Aux) :-
    compatibles(BO, BD),
    al_lado(XO, YO, XD, YD).
res_aux(Origen, Destino, Piezas, Aux, Sol) :-
    select(P, Piezas, Resto),
    PS = pieza_ub(_, _, P),
    ubicar(PS, Origen, Aux, OrigenSig),
    append(Aux, [PS], Aux1),
    res_aux(OrigenSig, Destino, Resto, Aux1, Sol).

ubicar(pieza_ub(XP, YP, LP), pieza_ub(XO, YO, LO), Aux, pieza_ub(XP, YP, LOS)) :-
    member(arriba, LP),
    member(abajo, LO),
    XP is XO,
    YP is YO + 1,
    disponible(XP, YP, Aux),
    select(arriba, LP, LOS).
ubicar(pieza_ub(XP, YP, LP), pieza_ub(XO, YO, LO), Aux, pieza_ub(XP, YP, LOS)) :-
    member(izq, LP),
    member(der, LO),
    XP is XO + 1,
    YP is YO,
    disponible(XP, YP, Aux),
    select(izq, LP, LOS).
ubicar(pieza_ub(XP, YP, LP), pieza_ub(XO, YO, LO), Aux, pieza_ub(XP, YP, LOS)) :-
    member(abajo, LP),
    member(arriba, LO),
    XP is XO,
    YP is YO - 1,
    disponible(XP, YP, Aux),
    select(abajo, LP, LOS).
ubicar(pieza_ub(XP, YP, LP), pieza_ub(XO, YO, LO), Aux, pieza_ub(XP, YP, LOS)) :-
    member(der, LP),
    member(izq, LO),
    XP is XO - 1,
    YP is YO,
    disponible(XP, YP, Aux),
    select(der, LP, LOS).

%al momento en que origen y destino es el mismo, quiere decir que llegaste.

%el resultado es un listado de estructuras conformadas por piezas ubicadas.