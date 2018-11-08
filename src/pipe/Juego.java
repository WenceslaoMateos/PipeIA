package pipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 *
 * @author wenceslao
 */
public class Juego {

    private static Juego juego;
    private VistaPipe vista;

    private Juego(VistaPipe vista) {
        this.vista = vista;
    }

    public ArrayList<Pipe> comenzar(int xMax, int yMax, int xO, int yO, int xD, int yD, HashMap<Integer, Integer> cantidades) {
        String lista = "";
        Iterator<Integer> claves = cantidades.keySet().iterator();
        int i;
        int aux;
        int cant;
        String pipe = "";
        while (claves.hasNext()) {
            aux = claves.next();
            cant = cantidades.get(aux);
            if (cant > 0) {
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
                lista += "pieza(" + cant + ", " + pipe + "), ";
            }
        }
        if (!lista.equals("")) {
            lista = lista.substring(0, lista.length() - 2);
            lista = "[" + lista + "]";
        }
        Query.hasSolution("consult('pipe.pl')");
        String query = "resolver(extremo(" + xO + ", " + yO + ", " + this.orientar(xO, yO) + "), extremo(" + xD + ", " + yD + ", "+ this.orientar(xD, yD) +"), " + lista + ", Sol).";
        System.out.println(query);
        Query sol = new Query(query);
        ArrayList<Pipe> aux2 = null;
        if (sol.hasSolution()) {
            aux2 = this.transformar(sol);
        } else {
            System.out.println("La query sol = " + query + " no tiene solución.");
        }
        return aux2;
    }

    private ArrayList<Pipe> transformar(Query q) {
        Map<String, Term> solutions = q.oneSolution();
        String aux2 = "";
        ArrayList<Pipe> solucion = new ArrayList<Pipe>();
        Pipe pieza;
        Term term = solutions.get("Sol");
        for (Term oneTerm : term.toTermArray()) {
            ArrayList<String> oris = new ArrayList<String>();
            for (Term aux : oneTerm.arg(3).toTermArray()) {
                oris.add(aux.toString());
            }
            pieza = new Pipe(Integer.parseInt(oneTerm.arg(1).toString()), Integer.parseInt(oneTerm.arg(2).toString()), oris.get(0), oris.get(1));
            solucion.add(pieza);
        }
        System.out.println(aux2);
        return solucion;
    }

    public String orientar(int x, int y) {
        String aux = "";
        if (x == 0) {
            aux = "der";
        } else {
            if (x == 6) {
                aux = "izq";
            } else {
                if (y == 0) {
                    aux = "abajo";
                } else {
                    if (y == 6) {
                        aux = "arriba";
                    }
                }
            }
        }
        return aux;
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
