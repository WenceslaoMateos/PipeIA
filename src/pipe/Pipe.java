/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipe;

import org.jpl7.Query;

/**
 *
 * @author wenceslao
 */
public class Pipe {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Query.hasSolution("consult('eintein.pl')");
        String X=Query.oneSolution("pez(X)").get("X").toString();
        System.out.println(X);
    }
}
