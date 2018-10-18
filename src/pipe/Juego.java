/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipe;

/**
 * La clase juego se encarga de la dirección del juego. Su logica interna.
 * @author wenceslao
 */
public class Juego {
    private static Juego juego;
    private VistaPipe vista;
/*
         * public static void main(String[] args) {
     * Query.hasSolution("consult('pipe.pl')"); String X =
     * Query.oneSolution("pez(X)").get("X").toString(); System.out.println(X);

    */    
    private Juego(VistaPipe vista){
        this.vista = vista;
    }
    
    public void comenzar(){
        
    }
    
    public static Juego getJuego(VistaPipe vista){
        if (Juego.juego == null){
            Juego.juego = new Juego(vista);
        }
        return Juego.juego;
    }

    void agregaPipe() {
        this.vista.agregaPipe();
    }
}
