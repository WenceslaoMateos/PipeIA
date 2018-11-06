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
%formato de pieza ubicada: pieza_ub(X, Y, [listado de bocas])

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

ubicar_al_lado(extremo(XE, YE, izq), X, YE) :-
    X is XE - 1.
ubicar_al_lado(extremo(XE, YE, der), X, YE) :-
    X is XE + 1.
ubicar_al_lado(extremo(XE, YE, arriba), XE, Y) :-
    Y is YE - 1.
ubicar_al_lado(extremo(XE, YE, abajo), XE, Y) :-
    Y is YE + 1.


resolver(Origen, Destino, Piezas, Sol) :-
    res_aux(Origen, Destino, Piezas, [], Sol).

res_aux(extremo(XO, YO, BO), extremo(XD, YD, BD), _, Aux, Aux) :-
    compatibles(BO, BD),
    ubicar_al_lado(extremo(XO, YO, BO), XD, YD).
res_aux(Origen, Destino, Piezas, Aux, Sol) :-
    Nuevo = pieza_ub(X, Y, Pieza),
    OrigenSig = extremo(X, Y, _),
    ubicar_al_lado(Origen, X, Y),
    disponible(X, Y, Aux),
    seleccionar_pieza(Origen, Piezas, Pieza, Resto, OrigenSig),
    append(Aux, [Nuevo], Aux1),
    res_aux(OrigenSig, Destino, Resto, Aux1, Sol).

seleccionar_pieza(extremo(_, _, BO), Piezas, Pieza, Resto, extremo(_, _, BS)) :-
    compatibles(BO, BP),
    select(Pieza, Piezas, Resto),
    select(BP, Pieza, Aux),
    member(BS, Aux).

%al momento en que origen y destino es el mismo, quiere decir que llegaste.

%el resultado es un listado de estructuras conformadas por piezas ubicadas.
