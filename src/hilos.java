import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class hilos extends JFrame {

    private JProgressBar jProgressBar1, jProgressBar2, jProgressBar3;
    private JLabel jLabel1, jLabel2, jLabel3;
    private int n1, n2, n3;
    private String primero, segundo, tercero;
    private final Random aleatorio = new Random();

    public hilos() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Carrera de Hilos");
        setSize(400, 200);
        setLayout(new GridLayout(4, 2));

        jLabel1 = new JLabel("0%");
        jProgressBar1 = new JProgressBar(0, 100);
        jLabel2 = new JLabel("0%");
        jProgressBar2 = new JProgressBar(0, 100);
        jLabel3 = new JLabel("0%");
        jProgressBar3 = new JProgressBar(0, 100);
        JButton jButton1 = new JButton("Iniciar Carrera");

        jButton1.addActionListener(this::jButton1ActionPerformed);

        add(new JLabel("Barra 1:"));
        add(jProgressBar1);
        add(jLabel1);
        add(new JLabel("Barra 2:"));
        add(jProgressBar2);
        add(jLabel2);
        add(new JLabel("Barra 3:"));
        add(jProgressBar3);
        add(jLabel3);
        add(new JPanel()); // Empty panel for spacing
        add(jButton1);
    }

    public void dormir() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        n1 = 0;
        n2 = 0;
        n3 = 0;
        primero = "";
        segundo = "";
        tercero = "";

        jLabel1.setText("0%");
        jProgressBar1.setValue(0);
        jLabel2.setText("0%");
        jProgressBar2.setValue(0);
        jLabel3.setText("0%");
        jProgressBar3.setValue(0);

        barra1 objeto1 = new barra1();
        Thread hilo1 = new Thread(objeto1);
        hilo1.start();

        barra2 objeto2 = new barra2();
        Thread hilo2 = new Thread(objeto2);
        hilo2.start();

        barra3 objeto3 = new barra3();
        Thread hilo3 = new Thread(objeto3);
        hilo3.start();
    }

    public class barra1 implements Runnable {
        public void run() {
            while (n1 <= 100) {
                SwingUtilities.invokeLater(() -> {
                    jLabel1.setText(n1 + "%");
                    jProgressBar1.setValue(n1);
                });
                n1 += aleatorio.nextInt(2);
                dormir();
            }
            SwingUtilities.invokeLater(() -> {
                jProgressBar1.setValue(100);
                jLabel1.setText("100%");
            });

            synchronized (hilos.this) {
                if (primero.isEmpty()) {
                    primero = "1er Lugar Barra 1\n";
                } else if (segundo.isEmpty()) {
                    segundo = "2do Lugar Barra 1\n";
                } else if (tercero.isEmpty()) {
                    tercero = "3er Lugar Barra 1\n";
                    JOptionPane.showMessageDialog(null, "" + primero + segundo + tercero);
                }
            }
        }
    }

    public class barra2 implements Runnable {
        public void run() {
            while (n2 <= 100) {
                SwingUtilities.invokeLater(() -> {
                    jLabel2.setText("" + n2 + "%");
                    jProgressBar2.setValue(n2);
                });
                n2 += aleatorio.nextInt(2);
                dormir();
            }
            SwingUtilities.invokeLater(() -> {
                jProgressBar2.setValue(100);
                jLabel2.setText("100%");
            });

            synchronized (hilos.this) {
                if (primero.isEmpty()) {
                    primero = "1er Lugar Barra 2\n";
                } else if (segundo.isEmpty()) {
                    segundo = "2do Lugar Barra 2\n";
                } else if (tercero.isEmpty()) {
                    tercero = "3er Lugar Barra 2\n";
                    JOptionPane.showMessageDialog(null, "" + primero + segundo + tercero);
                }
            }
        }
    }

    public class barra3 implements Runnable {
        public void run() {
            while (n3 <= 100) {
                SwingUtilities.invokeLater(() -> {
                    jLabel3.setText("" + n3 + "%");
                    jProgressBar3.setValue(n3);
                });
                n3 += aleatorio.nextInt(2);
                dormir();
            }
            SwingUtilities.invokeLater(() -> {
                jProgressBar3.setValue(100);
                jLabel3.setText("100%");
            });

            synchronized (hilos.this) {
                if (primero.isEmpty()) {
                    primero = "1er Lugar Barra 3\n";
                } else if (segundo.isEmpty()) {
                    segundo = "2do Lugar Barra 3\n";
                } else if (tercero.isEmpty()) {
                    tercero = "3er Lugar Barra 3\n";
                    JOptionPane.showMessageDialog(null, "" + primero + segundo + tercero);
                }
            }
        }
    }

    static void main() {
        SwingUtilities.invokeLater(() -> new hilos().setVisible(true));
    }
}