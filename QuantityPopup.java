import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class QuantityPopup extends JComponent implements ActionListener {

    public static final String ACTIONCOMMAND_OK = "ok";
    public static final String ACTIONCOMMAND_CANCEL = "cancel";

    private final String name;
    private final Icon backgroundIcon;
    private final Rectangle incArea;
    private final Rectangle decArea;
    private final Rectangle okArea;
    private final ActionListener actionListener;
    private final ActionEvent actionEvent = new ActionEvent(this, 
        ActionEvent.ACTION_PERFORMED, ACTIONCOMMAND_OK);
    private final int minValue;
    private final int maxValue;
    private int value;
    private int step;
    private int iterationCounter;

    public QuantityPopup(String name,
        int minValue, int maxValue, int initialValue,
        String backgroundImagePath,
        Rectangle incArea, Rectangle decArea, Rectangle okArea,
        ActionListener actionListener) {

        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        value = initialValue;

        this.incArea = incArea;
        this.decArea = decArea;
        this.okArea = okArea;
        this.actionListener = actionListener;

        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource(backgroundImagePath));
        setSize(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());

        setLayout(null);
        JLabel l;
        add(l = new JLabel(backgroundIcon));
        l.setBounds(0, 0, 405, 200);

        int minWidth = backgroundIcon.getIconWidth();
        int minHeight = backgroundIcon.getIconWidth();
        setMinimumSize(new Dimension(minWidth, minHeight));

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }

    public int getValue() {
        return value;
    }

    private final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    private final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

    private final Timer incTimer = new Timer(100, this);
    private final Timer decTimer = new Timer(100, this);

    @Override
    protected void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        if(event.getID() == MouseEvent.MOUSE_PRESSED) {
            if(SwingUtilities.isLeftMouseButton(event)) {
                if(incArea.contains(event.getX(), event.getY())) {
                    //System.out.println("inc pressed");
                    step = 1;
                    iterationCounter = 0;
                    incTimer.setInitialDelay(500);
                    incTimer.start();
                }
                else if(decArea.contains(event.getX(), event.getY())) {
                    //System.out.println("dec pressed");
                    step = 1;
                    iterationCounter = 0;
                    decTimer.setInitialDelay(500);
                    decTimer.start();
                }
            }
        }
        else if(event.getID() == MouseEvent.MOUSE_RELEASED) {
            if(SwingUtilities.isLeftMouseButton(event)) {
                incTimer.stop();
                decTimer.stop();
            }
        }
        else if(event.getID() == MouseEvent.MOUSE_CLICKED) {
            if(SwingUtilities.isLeftMouseButton(event)) {
                if(incArea.contains(event.getX(), event.getY())) {
                    //System.out.println("inc clicked");
                    step = 1;
                    iterationCounter = 0;
                    if(incValue()) {
                        repaint();
                    }
                }
                else if(decArea.contains(event.getX(), event.getY())) {
                    //System.out.println("dec clicked");
                    step = 1;
                    iterationCounter = 0;
                    if(decValue()) {
                        repaint();
                    }
                }
                else if(okArea.contains(event.getX(), event.getY())) {
                    actionListener.actionPerformed(actionEvent);
                }
            }
        }
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        super.processMouseMotionEvent(event);
        if(incArea.contains(event.getX(), event.getY())) {
            setCursor(HAND_CURSOR);
        }
        else if(decArea.contains(event.getX(), event.getY())) {
            setCursor(HAND_CURSOR);
        }
        else if(okArea.contains(event.getX(), event.getY())) {
            setCursor(HAND_CURSOR);
        }
        else {
            setCursor(DEFAULT_CURSOR);
            incTimer.stop();
            decTimer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if(incTimer == source) {
            //System.out.println("incTimer " + SwingUtilities.isEventDispatchThread());
            if(incValue()) {
                repaint();
            }
        }
        else if(decTimer == source) {
            //System.out.println("decTimer " + SwingUtilities.isEventDispatchThread());
            if(decValue()) {
                repaint();
            }
        }
    }

    private boolean incValue() {
        if(value + step <= maxValue) {
            value += step;
            iterationCounter += 1;
            if(iterationCounter > 10 && step < 100) {
                iterationCounter = 0;
                step *= 10;
            }
            return true;
        }
        else {
            return false;
        }
    }

    private boolean decValue() {
        if(value - step >= minValue) {
            value -= step;
            iterationCounter += 1;
            if(iterationCounter > 10 && step < 100) {
                iterationCounter = 0;
                step *= 10;
            }
            return true;
        }
        else {
            return false;
        }
    }

    private static Font VALUE_FONT = new Font("Arial", Font.BOLD + Font.ITALIC, 35);
    private static Color VALUE_COLOR = new Color(51, 36, 36);

    @Override
    public void paintComponent(Graphics g) {
        backgroundIcon.paintIcon(this, g, 0, 0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.drawImage(((ImageIcon)backgroundIcon).getImage(), 0, 0, null);
        //backgroundIcon.paintIcon(this, g, 0, 0);
        g.setFont(VALUE_FONT);
        //g.setColor(VALUE_COLOR);
        g.setColor(Color.RED);
        g.drawString(String.valueOf(value), 150, 125);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle(QuantityPopup.class.getSimpleName() + " test");
        QuantityPopup qp = new QuantityPopup("milk",
            10, 1000, 200,
            "src/img/milk_quantity.png",
            new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
            new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
            new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1),
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println(event.getActionCommand());
                }
            });
        f.getContentPane().add(qp);
        int width = qp.backgroundIcon.getIconWidth();
        int height = qp.backgroundIcon.getIconHeight();
        System.out.println(String.format("%d x %d", width, height));
        //f.setSize(width, height);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}