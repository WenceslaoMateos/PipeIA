%representar las piezas como una lista de huecos
%pieza([arriba, abajo, izquierda, derecha]).
%se va a dar un listado de piezas (en formato lista, aserto no porque es horroroso al backtracking)
% una vez cargado el programa y se va a resolver con eso
piezas_disponibles([[izq, der], [izq, der], [izq, der], [izq, der], [abajo, arriba], [abajo, arriba], [abajo, der], [izq, arriba]]).
origen(pieza_ub(0, 4, [der])).
destino(pieza_ub(6, 1, [izq])).
ocupado(0, 4).
%formato de pieza ubicada: pieza_ub(X, Y, [listado de orientaciones])

%limites del tablero
ubicacion_valida(X, Y) :-
    X > 0,
    Y > 0,
    X < 6,
    Y < 6.

disponible(X, Y) :-
    ocupado(X, Y),
    !,
    fail.
disponible(_, _).

resolver(O, D, _, []) :-
    ubicar(O, D, _).
resolver(Origen, Destino, Piezas, [SCab | SCol]) :-
    select(P, Piezas, Resto),
    SCab = pieza_ub(X, Y, P),
    ubicar(SCab, Origen, OrigenSig),
    miassert(ocupado(X, Y)),
    resolver(OrigenSig, Destino, Resto, SCol).

miassert(Ocupado):- assert(Ocupado).
miassert(Ocupado):- retract(Ocupado), fail.

ubicar(pieza_ub(XP, YP, LP), pieza_ub(XO, YO, LO), pieza_ub(XP, YP, LOS)) :-
    member(arriba, LP),
    member(abajo, LO),
    XP is XO,
    YP is YO + 1,
    disponible(XP, YP),
    select(arriba, LP, LOS).
ubicar(pieza_ub(XP, YP, LP), pieza_ub(XO, YO, LO), pieza_ub(XP, YP, LOS)) :-
    member(izq, LP),
    member(der, LO),
    XP is XO + 1,
    YP is YO,
    disponible(XP, YP),
    select(izq, LP, LOS).
ubicar(pieza_ub(XP, YP, LP), pieza_ub(XO, YO, LO), pieza_ub(XP, YP, LOS)) :-
    member(abajo, LP),
    member(arriba, LO),
    XP is XO,
    YP is YO - 1,
    disponible(XP, YP),
    select(abajo, LP, LOS).
ubicar(pieza_ub(XP, YP, LP), pieza_ub(XO, YO, LO), pieza_ub(XP, YP, LOS)) :-
    member(der, LP),
    member(izq, LO),
    XP is XO - 1,
    YP is YO,
    disponible(XP, YP),
    select(der, LP, LOS).

%al momento en que origen y destino es el mismo, quiere decir que llegaste.

%el resultado es un listado de estructuras conformadas por piezas ubicadas.