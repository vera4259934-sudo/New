import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class NewProduct extends JComponent {

    public final String name;
    private boolean draggable;
    private int initialX;
    private int initialY;

    private final Icon fullSizeRegularIcon;
    private final Icon fullSizeSelectedIcon;
    private final Icon halfSizeRegularIcon;
    private final Icon halfSizeSelectedIcon;

    // model with manual UI update
    private boolean selected = false;
    private boolean fullSize = true;

    private static final String FULLSIZE_REGULAR = "fullRegular";
    private static final String FULLSIZE_SELECTED = "fullSelected";
    private static final String HALFSIZE_REGULAR = "halfRegular";
    private static final String HALFSIZE_SELECTED = "halfSelected";

    protected final CardLayout cardLayout = new CardLayout();

    public NewProduct(String name, boolean draggable,
        String fullSizeRegularImagePath, String fullSizeSelectedImagePath,
        String halfSizeRegularImagePath, String halfSizeSelectedImagePath) {

        this.name = name;
        this.draggable = draggable;
        fullSizeRegularIcon = new ImageIcon(getClass().getClassLoader().getResource(fullSizeRegularImagePath));
        fullSizeSelectedIcon = new ImageIcon(getClass().getClassLoader().getResource(fullSizeSelectedImagePath));
        halfSizeRegularIcon = new ImageIcon(getClass().getClassLoader().getResource(halfSizeRegularImagePath));
        halfSizeSelectedIcon = new ImageIcon(getClass().getClassLoader().getResource(halfSizeSelectedImagePath));

        setLayout(cardLayout);
        add(new JLabel(fullSizeRegularIcon), FULLSIZE_REGULAR);
        add(new JLabel(fullSizeSelectedIcon), FULLSIZE_SELECTED);
        add(new JLabel(halfSizeRegularIcon), HALFSIZE_REGULAR);
        add(new JLabel(halfSizeSelectedIcon), HALFSIZE_SELECTED);

        if(draggable) {
            enableEvents(AWTEvent.MOUSE_EVENT_MASK);
            enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        }
    }

    public String getName() {
        return name;
    }

    private JLayeredPane parent;

    @Override
    public void addNotify() {
        super.addNotify();
        parent = (JLayeredPane)SwingUtilities.getAncestorOfClass(JLayeredPane.class, this);
        initialX = getX();
        initialY = getY();
    }

    public void returnToOriginalLocation() {
        selected = false;
        // manual UI update:
        cardLayout.show(this, fullSize ? FULLSIZE_REGULAR : HALFSIZE_REGULAR);
        setLocation(initialX, initialY);
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
        if(draggable) {
            enableEvents(AWTEvent.MOUSE_EVENT_MASK);
            enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        }
        else {
            x = y = -1;
            if(selected) {
                selected = false;
                // manual UI update:
                cardLayout.show(this, fullSize ? FULLSIZE_REGULAR : HALFSIZE_REGULAR);
            }
            disableEvents(AWTEvent.MOUSE_EVENT_MASK);
            disableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        }
    }

    public void setFullSize() {
        fullSize = true;
        // manual UI update:
        cardLayout.show(this, selected ? FULLSIZE_SELECTED : FULLSIZE_REGULAR);
    }

    public void setPartialSize() {
        fullSize = false;
        // manual UI update:
        cardLayout.show(this, selected ? HALFSIZE_SELECTED : HALFSIZE_REGULAR);
    }

    private int x;
    private int y;

    @Override
    protected void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event); // let listeners consume it first
        if(((Level_1)parent).quantityPopupShown) {
            // do nothing
        }
        else if(event.isConsumed()) {
            // do nothing
        }
        else if(event.getID() == MouseEvent.MOUSE_PRESSED) {
            if(SwingUtilities.isLeftMouseButton(event)) {
            	x = event.getX();
            	y = event.getY();
            }
        }
        else if(event.getID() == MouseEvent.MOUSE_RELEASED) {
            if(SwingUtilities.isLeftMouseButton(event)) {
            	x = y = -1;
                returnToOriginalLocation();
            }
        }
        else if(event.getID() == MouseEvent.MOUSE_ENTERED) {
            selected = true;
            // manual UI update:
            cardLayout.show(this, fullSize ? FULLSIZE_SELECTED : HALFSIZE_SELECTED);
        }
        else if(event.getID() == MouseEvent.MOUSE_EXITED) {
            selected = false;
            // manual UI update:
            cardLayout.show(this, fullSize ? FULLSIZE_REGULAR : HALFSIZE_REGULAR);
        }
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        if(((Level_1)parent).quantityPopupShown) {
            // do nothing
        }
        else if(event.getID() == MouseEvent.MOUSE_DRAGGED) {
            if(SwingUtilities.isLeftMouseButton(event)) {
                parent.moveToFront(this);
                int deltaX = event.getX() - x;
                int deltaY = event.getY() - y;
                setLocation(getX() + deltaX, getY() + deltaY);
            }
        }
        super.processMouseMotionEvent(event);
    }

    @Override
    public String toString() {
        return String.format("%s: %s-size %s\n" +
            "\tfull-size regular: %s: %d x %d\n" +
            "\tfull-size selected: %s: %d x %d\n" +
            "\thalf-size regular: %s: %d x %d\n" +
            "\thalf-size selected: %s: %d x %d",
            name, fullSize ? "full" : "half", selected ? "selected" : "regular",
            String.valueOf(fullSizeRegularIcon), fullSizeRegularIcon.getIconWidth(), fullSizeRegularIcon.getIconHeight(),
            String.valueOf(fullSizeSelectedIcon), fullSizeSelectedIcon.getIconWidth(), fullSizeSelectedIcon.getIconHeight(),
            String.valueOf(halfSizeRegularIcon), halfSizeRegularIcon.getIconWidth(), halfSizeRegularIcon.getIconHeight(),
            String.valueOf(halfSizeSelectedIcon), halfSizeSelectedIcon.getIconWidth(), halfSizeSelectedIcon.getIconHeight()
        );
    }
}