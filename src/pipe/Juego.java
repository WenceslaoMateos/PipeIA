package pipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jpl7.Query;
import org.jpl7.Term;

public class Juego {

    private static Juego juego;
    private VistaPipe vista;
    private Query sol;

    private Juego(VistaPipe vista) {
        this.vista = vista;
    }

    public Map<String, Term> comenzar(int xO, int yO, int xD, int yD, HashMap<Integer, Integer> cantidades) throws NoSolutionException {
        String lista = "";
        Iterator<Integer> claves = cantidades.keySet().iterator();
        int i;
        int aux;
        int cant;
        while (claves.hasNext()) {
            aux = claves.next();
            cant = cantidades.get(aux);
            if (cant > 0) {
                lista += "tipo(" + cant + ", " + aux + "), ";
            }
        }
        if (!lista.equals("")) {
            lista = lista.substring(0, lista.length() - 2);
            lista = "[" + lista + "]";
        }
        Query.hasSolution("consult('pipe.pl')");
        String query = "resolver(extremo(" + xO + ", " + yO + ", " + this.orientar(xO, yO) + "), extremo(" + xD + ", " + yD + ", " + this.orientar(xD, yD) + "), " + lista + ", Sol).";
        this.sol = new Query(query);
        System.out.println(query);
        return this.resolver();
    }

    public Map<String, Term> resolver() throws NoSolutionException {
        if (this.sol.hasMoreSolutions())
            return this.sol.nextSolution();
        else
            throw new NoSolutionException("No se han encontrado más soluciones.");
    }

    public ArrayList<Pipe> transformar(Map<String, Term> solutions) {
        String aux2 = "";
        ArrayList<Pipe> solucion = new ArrayList<Pipe>();
        Pipe pieza;
        Term term = solutions.get("Sol");
        for (Term oneTerm : term.toTermArray()) {
            pieza = new Pipe(Integer.parseInt(oneTerm.arg(1).toString()), Integer.parseInt(oneTerm.arg(2).toString()), Integer.parseInt(oneTerm.arg(3).toString()));
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
