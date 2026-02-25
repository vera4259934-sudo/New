/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class ScreenRecipe extends JFrame {

    Image recipeFon = Toolkit.getDefaultToolkit().createImage("src/img/ScreenRecipe.png");
    Ch knopkaReady = new Ch("src/img/knopkaReady.png", 1015, 500);
    private Timer timer;
    private int timeLeft = 120;
    private String timeString;

    public ScreenRecipe() {
        setTitle("Recipe Challenge");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1330, 770);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        setVisible(true);
        startTimer();
    }

    public void paint(Graphics g) {
        g.drawImage(recipeFon, 0, 0, this);
        g.drawImage(knopkaReady._image, knopkaReady.x, knopkaReady.y, this);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.setColor(Color.RED);
        g.drawString("Время: " + timeLeft, 1085, 300);

    }

    private void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                timeString = String.valueOf(timeLeft);
                repaint();
                if (timeLeft < 0) {
                    timer.cancel();
                    openTimeoutScreen();
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }



    private void openLevel1() {
        dispose();
        JFrame frame = new JFrame("Level 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Level_1 level1Screen = new Level_1();
        frame.getContentPane().add(level1Screen);
        frame.pack();
        frame.setVisible(true);
    }

    private void openTimeoutScreen() {
        System.out.println("Время вышло!");
        openLevel1();
    }

    private KeyListener keyListener = new KeyAdapter() {};

    private MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent qwerty) {
            if (qwerty.getX() >= knopkaReady.x && qwerty.getX() <= (knopkaReady.x + 420) && qwerty.getY() >= knopkaReady.y && qwerty.getY() <= (knopkaReady.y + 220)) {
                System.out.println("Пора бросить  настоящий кулинатрный вызов!");
                timer.cancel();
                openLevel1();
            }
        }
    };

    public static void main(String[] args) {
        new ScreenRecipe();
    }
}*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class ScreenRecipe extends JPanel {

    private LayersContainer layersContainer;

  @Override
  public void addNotify() {
    super.addNotify();
    this.layersContainer = (LayersContainer)SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
  }

    Image recipeFon = Toolkit.getDefaultToolkit().createImage("src/img/ScreenRecipe.png");
    Ch knopkaReady = new Ch("src/img/knopkaReady.png", 1015, 500);
    private Timer timer;
    private int timeLeft = 120;
    private String timeString;

    public ScreenRecipe() {
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        startTimer();
    }

    public void paint(Graphics g) {
        g.drawImage(recipeFon, 0, 0, this);
        g.drawImage(knopkaReady._image, knopkaReady.x, knopkaReady.y, this);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.setColor(Color.RED);
        g.drawString("Время: " + timeLeft, 1085, 300);
    }

    private void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                timeString = String.valueOf(timeLeft);
                repaint();
                if (timeLeft < 0) {
                    timer.cancel();
                    openTimeoutScreen();
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }


    private void openLevel1() {
        layersContainer.showLayer(LayersContainer.Layer.LEVEL_1);
        /*
        JFrame frame = new JFrame("Level 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Level_1 level1Screen = new Level_1();
        frame.getContentPane().add(level1Screen);
        frame.setSize(1330,770); // Задайте размер кадра
        frame.setVisible(true);
        */
    }

    private void openTimeoutScreen() {
        System.out.println("Время вышло!");
        openLevel1();
    }

    private KeyListener keyListener = new KeyAdapter() {};

    private MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent qwerty) {
            if (qwerty.getX() >= knopkaReady.x && qwerty.getX() <= (knopkaReady.x + 420) && qwerty.getY() >= knopkaReady.y && qwerty.getY() <= (knopkaReady.y + 220)) {
                System.out.println("Пора бросить  настоящий кулинатрный вызов!");
                timer.cancel();
                openLevel1();
            }
        }
    };

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Recipe Challenge");
        f.getContentPane().add(new ScreenRecipe());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1330, 770);
        f.setVisible(true);
    }
}

