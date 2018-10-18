/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipe;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Este es un pipe particular, va a tener posicion y un par de boludeces mas
 *
 * @author wenceslao
 */
public class Pipe {

    /**
     * @param args the command line arguments
     *
     * }
     */
    private Image imagen;
    private int x;
    private int y;

    public Pipe(int x, int y, int op) {
        String aux;
        switch (op) {
            case 1:
                aux = "images/vert.png";
                break;
            case 2:
                aux = "images/hor.png";
                break;
            case 3:
                aux = "images/abder.png";
                break;
            case 4:
                aux = "images/abizq.png";
                break;
            case 5:
                aux = "images/arrder.png";
                break;
            case 6:
                aux = "images/arrizq.png";
                break;
            default:
                aux = "";
                break;
        }
        this.imagen = new ImageIcon(aux).getImage();
        this.x = x;
        this.y = y;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
