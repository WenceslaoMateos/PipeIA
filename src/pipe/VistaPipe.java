package pipe;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

public class VistaPipe extends JPanel {

    ArrayList<Pipe> pipes = new ArrayList<Pipe>();

    public VistaPipe() {
        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Iterator<Pipe> it = this.pipes.iterator();
        Pipe aux;
        while (it.hasNext()) {
            aux = it.next();
            g.drawImage((Image) aux.getImagen(), aux.getX(), aux.getY(), null);
        }
    }

    public void agregaPipe(int x, int y, int op) {
        pipes.add(new Pipe(x, y, op));
        this.repaint();
    }

    public void agregaPipe(Pipe aux) {
        pipes.add(aux);
        this.repaint();
    }

    public void reiniciar() {
        this.pipes = new ArrayList<Pipe>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
