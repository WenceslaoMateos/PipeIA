/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipe;

import java.util.HashMap;
import java.util.Iterator;
import org.jpl7.Query;

/**
 * La clase juego se encarga de la dirección del juego. Su logica interna.
 *
 * @author wenceslao
 */
public class Juego {

    private static Juego juego;
    private VistaPipe vista;

    private Juego(VistaPipe vista) {
        this.vista = vista;
    }

    public void comenzar(int xMax, int yMax, int xO, int yO, int xD, int yD, HashMap<Integer, Integer> cantidades) {
        String lista = "";
        Iterator<Integer> claves = cantidades.keySet().iterator();
        int i;
        int aux = claves.next();
        String pipe = "";
        while (claves.hasNext()) {
            switch (aux) {
                case 1:
                    pipe = "[arriba, abajo]";
                    break;
                case 2:
                    pipe = "[izq, der]";
                    break;
                case 3:
                    pipe = "[abajo, der]";
                    break;
                case 4:
                    pipe = "[abajo, izq]";
                    break;
                case 5:
                    pipe = "[arriba, der]";
                    break;
                case 6:
                    pipe = "[arriba, izq]";
                    break;
                default:
            }
            int cant = cantidades.get(aux);
            for (i = 0; i < cant; i++) {
                lista += pipe + ", ";
            }
            aux = claves.next();
        }
        if (!lista.equals("")) {
            lista = lista.substring(0, lista.length() - 2);
            lista = "[" + lista + "]";
        }
        Query.hasSolution(
                "consult('pipe.pl')");
        //Compound compuesto = new Compound()?
        String query = "resolver(pieza_ub(" + xO + ", " + yO + ", [der]), pieza_ub(" + xD + ", " + yD + ", [izq]), piezas_disponibles(" + lista + "), Sol).";
        System.out.println(query);
        Query sol = new Query(query);
        if (sol.hasSolution()) {
            System.out.println(sol.oneSolution(query).get("Sol").toString());
        } else {
            System.out.println("La query sol = " + query + " no tiene solución.");
        }
    }

    public static Juego getJuego(VistaPipe vista) {
        if (Juego.juego == null) {
            Juego.juego = new Juego(vista);
        }
        return Juego.juego;
    }

    void agregaPipe(int x, int y, int op) {
        this.vista.agregaPipe(x, y, op);
    }
}
