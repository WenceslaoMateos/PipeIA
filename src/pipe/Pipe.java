package pipe;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author wenceslao
 */
public class Pipe {

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
            case 7:
                aux = "images/izq.png";
                break;
            case 8:
                aux = "images/der.png";
                break;
            case 9:
                aux = "images/arriba.png";
                break;
            case 10:
                aux = "images/abajo.png";
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
        return 50 * x + 5;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return 50 * y + 5;
    }

    public void setY(int y) {
        this.y = y;
    }
}
