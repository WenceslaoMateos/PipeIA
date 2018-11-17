%base de datos representada de la forma "id, listado de bocas"
pieza(1, [arriba, abajo]).
pieza(2, [izq, der]).
pieza(3, [abajo, der]).
pieza(4, [abajo,izq]).
pieza(5, [der, arriba]).
pieza(6, [izq, arriba]).

%define el tamaño de la matriz de celdas 
ubicacion_valida(X, Y) :-
    X > 0,
    Y > 0,
    X < 6,
    Y < 6.

%una ubicación está disponible si la lista de piezas ubicadas está vacia
disponible(_, _, []).
% verifica que una ubicación esté disponible en el listado de piezas ubicadas
disponible(X, Y, [pieza_ub(XA, YA, _) | Cola]) :-
    (X \= XA; Y \= YA),
    disponible(X, Y, Cola).

%establece compatibilidad de bocas libres para la selección de la proxima pieza a ubicar
compatibles(izq, der).
compatibles(der, izq).
compatibles(arriba, abajo).
compatibles(abajo, arriba).

%dado un extremo y la posición actual de la ultima pieza ubicada, establece la siguiente posición
%en la que se va a ubicar una pieza teniendo en cuenta la orientación de la boca libre
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
    OrigenSig = extremo(X, Y, _),
    ubicar_al_lado(Origen, X, Y),
    ubicacion_valida(X, Y),
    disponible(X, Y, Aux),
    !,
    seleccionar_pieza(Origen, Piezas, Pieza, Resto, OrigenSig),
    append(Aux, [pieza_ub(X, Y, Pieza)], Aux1),
    res_aux(OrigenSig, Destino, Resto, Aux1, Sol).

seleccionar_pieza(extremo(_, _, BO), Piezas, Pieza, Resto, extremo(_, _, BS)) :-
    Tipo = tipo(_, Pieza),
    compatibles(BO, BP),
    select(Tipo, Piezas, R),
    pieza(Pieza, Bocas),
    select(BP, Bocas, Aux),
    decrementar_cantidad(Tipo, R, Resto),
    member(BS, Aux).

decrementar_cantidad(tipo(1, _), Piezas, Piezas) :-
    !.
decrementar_cantidad(tipo(Cant1, Bocas), Piezas, [tipo(Cant2, Bocas) | Piezas]) :-
    Cant2 is Cant1 - 1.