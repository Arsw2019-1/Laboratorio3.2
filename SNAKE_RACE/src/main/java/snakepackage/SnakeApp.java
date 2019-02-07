package snakepackage;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import enums.GridSize;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author jd-
 *
 */
public class SnakeApp extends JFrame implements ActionListener {

    Boolean ver = false;
    JButton iniciar;
    JButton pausar;
    JButton reanudar;

    private static SnakeApp app;
    public static final int MAX_THREADS = 8;
    Snake[] snakes = new Snake[MAX_THREADS];
    private static final Cell[] spawn = {
        new Cell(1, (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(GridSize.GRID_WIDTH - 2,
        3 * (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(3 * (GridSize.GRID_WIDTH / 2) / 2, 1),
        new Cell((GridSize.GRID_WIDTH / 2) / 2, GridSize.GRID_HEIGHT - 2),
        new Cell(1, 3 * (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(GridSize.GRID_WIDTH - 2, (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell((GridSize.GRID_WIDTH / 2) / 2, 1),
        new Cell(3 * (GridSize.GRID_WIDTH / 2) / 2,
        GridSize.GRID_HEIGHT - 2)};
    private JFrame frame;
    private static Board board;
    int nr_selected = 0;
    Thread[] thread = new Thread[MAX_THREADS];

    public SnakeApp() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("The Snake Race");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(618, 640);
        frame.setSize(GridSize.GRID_WIDTH * GridSize.WIDTH_BOX + 17,
                GridSize.GRID_HEIGHT * GridSize.HEIGH_BOX + 40);
        frame.setLocation(dimension.width / 2 - frame.getWidth() / 2,
                dimension.height / 2 - frame.getHeight() / 2);
        board = new Board();

        frame.add(board, BorderLayout.CENTER);
        iniciar = new JButton("Iniciar");
        pausar = new JButton("Pausar");
        reanudar = new JButton("Reanudar");
        JPanel actionsBPabel = new JPanel();
        actionsBPabel.setLayout(new FlowLayout());
        actionsBPabel.add(iniciar);
        actionsBPabel.add(pausar);
        actionsBPabel.add(reanudar);
        iniciar.addActionListener(this);
        pausar.addActionListener(this);
        reanudar.addActionListener(this);
        //actionsBPabel.add(new JButton("Iniciar "));
        frame.add(actionsBPabel, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {

        //SnakeApp n1=new SnakeApp();
        app = new SnakeApp();
        app.init();
        //app.setVisible(true);

    }

    private void init() {
        //if(ver){

        for (int i = 0; i != MAX_THREADS; i++) {

            snakes[i] = new Snake(i + 1, spawn[i], i + 1);
            snakes[i].addObserver(board);
            thread[i] = new Thread(snakes[i]);
            //thread[i].start();
        }

        frame.setVisible(true);

        while (true) {
            int x = 0;
            for (int i = 0; i != MAX_THREADS; i++) {
                if (snakes[i].isSnakeEnd() == true) {
                    x++;
                }
            }
            if (x == MAX_THREADS) {
                break;
            }
        }

        System.out.println("Thread (snake) status:");
        for (int i = 0; i != MAX_THREADS; i++) {
            System.out.println("[" + i + "] :" + thread[i].getState());
        }

        // }
    }

    public static SnakeApp getApp() {
        return app;
    }

    public void actionPerformed(ActionEvent e) {
        /**
         * System.out.println("el del 1 :"+e.toString()+" y el otro
         * :"+iniciar.getText()); String u=e.toString();
         *
         *
         */
        String t1 = e.getActionCommand();
        String t2 = iniciar.getText();

        Boolean t = (t1.equals(t2));
        System.out.println("hola" + t);
        System.out.println("mire : " + e.getActionCommand());
        System.out.println("miro: " + iniciar.getText());
        t1 = (String) t1;
        //System.out.println("lolol : " + t1.equals((String) iniciar.getText()));
        //if((e.getSource().toString().equals( iniciar.get toString()))){
        System.out.println("lalalala " + (e.getSource() == reanudar));
        //if(t1.equals((String)iniciar.getText())){
        if ((e.getSource() == iniciar)) {

            for (int i = 0; i != MAX_THREADS; i++) {
                thread[i].start();
            }

        } else if (e.getSource() == pausar) {
            for (int p = 0; p != MAX_THREADS; p++) {
                //thread[p].sleep(1000000);
                thread[p].suspend();
            }

        } else if (e.getSource() == reanudar) {
            for (int u = 0; u != MAX_THREADS; u++) {
                thread[u].notify();
            }
            

        }
    }

}
