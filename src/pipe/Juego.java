/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jpl7.Query;
import org.jpl7.Term;

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
        int aux;
        String pipe = "";
        while (claves.hasNext()) {
            aux = claves.next();
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
        }
        if (!lista.equals("")) {
            lista = lista.substring(0, lista.length() - 2);
            lista = "[" + lista + "]";
        }
        Query.hasSolution(
                "consult('pipe.pl')");
        //String query = "resolver(pieza_ub(" + xO + ", " + yO + ", [der]), pieza_ub(" + xD + ", " + yD + ", [izq]), " + lista + ", Sol).";
        //System.out.println(query);
        Query sol = new Query(query);
        if (sol.hasSolution()) {
            this.transformar(sol);
        } else {
            System.out.println("La query sol = " + query + " no tiene solución.");
        }
    }

    private void transformar(Query q) {
        Map<String, Term>[] solutions = q.allSolutions();
        ArrayList<String> categories = new ArrayList<String>();
        String aux2 = "";
        for (int i = 0; i < solutions.length; i++) {
            aux2 += "Sol = " + solutions[i].get("Sol") + "\n";
            Term term = solutions[i].get("Sol");
            for (Term oneTerm : term.toTermArray()) {
                categories.add(oneTerm.toString());
                aux2 += "Ficha: " + oneTerm.arg(1).toString() + ", " + oneTerm.arg(2).toString() + ", [";
                for (Term aux : oneTerm.arg(3).toTermArray()) {
                    aux2 += aux.toString() + ", ";
                }
                aux2 = aux2.substring(0, aux2.length() - 2);
                aux2 += "]\n";
            }
            System.out.println(aux2);
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
