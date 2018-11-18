% pieza(id, bocas).
pieza(1, [arriba, abajo]).
pieza(2, [izq, der]).
pieza(3, [abajo, der]).
pieza(4, [abajo,izq]).
pieza(5, [der, arriba]).
pieza(6, [izq, arriba]).

% El tablero es de 5 * 5
ubicacion_valida(X, Y) :-
    X > 0,
    Y > 0,
    X < 6,
    Y < 6.

% pieza_ub(PosX, PosY, ID)
% Un espacio esta disponible si ninguna pieza esta sobre ella
disponible(_, _, []).
disponible(X, Y, [pieza_ub(XA, YA, _) | Cola]) :-
    (X \= XA; Y \= YA),
    disponible(X, Y, Cola).

compatibles(izq, der).
compatibles(der, izq).
compatibles(arriba, abajo).
compatibles(abajo, arriba).

% extremo(PosX, PosY, BocaLibre)
ubicar_al_lado(extremo(XE, YE, izq), X, YE) :-
    X is XE - 1.
ubicar_al_lado(extremo(XE, YE, der), X, YE) :-
    X is XE + 1.
ubicar_al_lado(extremo(XE, YE, arriba), XE, Y) :-
    Y is YE - 1.
ubicar_al_lado(extremo(XE, YE, abajo), XE, Y) :-
    Y is YE + 1.

% Este predicado llama a res_aux iniciando con una lista auxiliar vacia
resolver(Origen, Destino, Piezas, Sol) :-
    res_aux(Origen, Destino, Piezas, [], Sol).

% Origen es el extremo de partida, Destino es el extremo que se busca alcanzar, Piezas es una lista de
% estructuras tipo(Cantidad, ID), siendo Cantidad cuantas piezas de dicho tipo, al que le corresponde un
% determinado id, hay presentes; Aux es la lista auxiliar que va llevando las piezas ubicadas hasta el momento
% y Sol es donde se devuelve la lista final.

% Para hallar una solucion, origen y destino deben ser compatibles y estar uno al lado del otro
res_aux(extremo(XO, YO, BO), extremo(XD, YD, BD), _, Aux, Aux) :-
    compatibles(BO, BD),
    ubicar_al_lado(extremo(XO, YO, BO), XD, YD).
% Primero, el origen determina cual sera la proxima posicion en la que se debe ubicar una pieza.
% Luego, se valida dicha ubicacion verificando que este dentro de los limites del tablero y que
% no esté ocupada ya por otra pieza.
% Una vez hecho esto, se selecciona una pieza acorde al origen, lo que ademas determina cual sera la boca
% disponible del origen siguiente.
% Finalmente, se descompone recursivamente el problema a partir de dicho origen con las piezas aun no ubicadas.
res_aux(Origen, Destino, Piezas, Aux, Sol) :-
    OrigenSig = extremo(X, Y, _),
    ubicar_al_lado(Origen, X, Y),
    ubicacion_valida(X, Y),
    disponible(X, Y, Aux),
    !,
    seleccionar_pieza(Origen, Piezas, Pieza, Resto, OrigenSig),
    append(Aux, [pieza_ub(X, Y, Pieza)], Aux1),
    res_aux(OrigenSig, Destino, Resto, Aux1, Sol).

% La pieza a seleccionar debe tener una boca compatible con la del origen, tras encontrar una acorde se
% quita una unidad de la cantidad disponible de ese tipo de pieza de la lista de disponibles y finalmente
% se obtiene la boca que quedara libre de la pieza seleccionada como parte del origen siguiente.
seleccionar_pieza(extremo(_, _, BO), Piezas, Pieza, Resto, extremo(_, _, BS)) :-
    Tipo = tipo(_, Pieza),
    compatibles(BO, BP),
    select(Tipo, Piezas, R),
    pieza(Pieza, Bocas), % Recupera la lista de bocas de la pieza desde la base de datos
    select(BP, Bocas, Aux),
    decrementar_cantidad(Tipo, R, Resto),
    member(BS, Aux).

decrementar_cantidad(tipo(1, _), Piezas, Piezas) :-
    !.
decrementar_cantidad(tipo(Cant1, Id), Piezas, [tipo(Cant2, Id) | Piezas]) :-
    Cant2 is Cant1 - 1.