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

    public Pipe(int x, int y, String or1, String or2) {
        String aux = "";
        if (or1.equals("izq")) {
            if (or2.equals("der")) {
                aux = "images/hor.png";
            }
            if (or2.equals("arriba")) {
                aux = "images/arrizq.png";
            }
            if (or2.equals("abajo")) {
                aux = "images/abizq.png";
            }
        }
        if (or1.equals("der")) {
            if (or2.equals("izq")) {
                aux = "images/hor.png";
            }
            if (or2.equals("arriba")) {
                aux = "images/arrder.png";
            }
            if (or2.equals("abajo")) {
                aux = "images/abder.png";
            }
        }
        if (or1.equals("arriba")) {
            if (or2.equals("der")) {
                aux = "images/arrder.png";
            }
            if (or2.equals("izq")) {
                aux = "images/arrizq.png";
            }
            if (or2.equals("abajo")) {
                aux = "images/vert.png";
            }
        }
        if (or1.equals("abajo")) {
            if (or2.equals("der")) {
                aux = "images/abder.png";
            }
            if (or2.equals("arriba")) {
                aux = "images/vert.png";
            }
            if (or2.equals("izq")) {
                aux = "images/abizq.png";
            }
        }
        this.imagen = new ImageIcon(aux).getImage();
        this.x = x;
        this.y = y;
    }

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
        return 105 * x + 10;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return 105 * y + 10;
    }

    public void setY(int y) {
        this.y = y;
    }
}
