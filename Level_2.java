import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;


public class Level_2 extends JLayeredPane implements MouseListener, ActionListener {

    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer)SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    private final Icon backgroundIcon;
    private final NewProduct ice;
    private final NewProduct milk;
    private final NewProduct sugar;
    private final NewProduct tapioka;
    private final NewProduct tea;
    private final NewProduct vanil;
    private final NewProduct cup;

    //private final NewProduct for2;

    private final Map<NewProduct, QuantityPopup> quantityPopups;
    boolean quantityPopupShown;

    public Level_2() {
        backgroundIcon = new ImageIcon("src/img/level1background.png");;

        ice = new NewProduct("ice", true,
                "src/img/ice.png", "src/img/ice_selected.png",
                "src/img/ice.png", "src/img/ice_selected.png");
        milk = new NewProduct("milk", true,
                "src/img/milkFull.png", "src/img/milkFullSelected.png",
                "src/img/milkPartial.png", "src/img/milkPartialSelected.png");
        sugar = new NewProduct("sugar", true,
                "src/img/sugar.png", "src/img/sugar_selected.png",
                "src/img/sugar.png", "src/img/sugar_selected.png");
        tapioka = new NewProduct("topioka", true,
                "src/img/topioka.png", "src/img/topioka_selected.png",
                "src/img/topioka.png", "src/img/topioka_selected.png");
        tea = new NewProduct("tea", true,
                "src/img/tea.png", "src/img/tea_selected.png",
                "src/img/tea.png", "src/img/tea_selected.png");
        vanil = new NewProduct("vanil", true,
                "src/img/vanil.png", "src/img/vanil_selected.png",
                "src/img/vanil.png", "src/img/vanil_selected.png");
        cup = new NewProduct("cup", true,
                "src/img/cup.png", "src/img/cup.png",
                "src/img/cup.png", "src/img/cup.png");


      /*  for2 = new NewProduct("for2", true,
                "src/img/po.png", "src/img/po.png",
                "src/img/po.png", "src/img/po.png");*/

        // non-draggable:
        /*ice = new NewProduct("ice", false,
                "src/img/ice.png", "src/img/ice.png",
                "src/img/ice.png", "src/img/ice.png");
        milk = new NewProduct("milk", false,
                "src/img/milk.png","src/img/milk.png",
                "src/img/milk.png","src/img/milk.png");
        sugar = new NewProduct("sugar", false,
                "src/img/sugar.png","src/img/sugar.png",
                "src/img/sugar.png","src/img/sugar.png");
        // draggable:
        tapioka = new NewProduct("tapioka", true,
                "src/img/tapioka.png","src/img/tapioka_selected.png",
                "src/img/tapioka.png","src/img/tapioka_selected.png");
        tea = new NewProduct("tea", true,
                "src/img/tea.png","src/img/tea_selected.png",
                "src/img/tea.png","src/img/tea_selected.png");
        vanil = new NewProduct("tea", true,
                "src/img/vanil.png","src/img/vanil_selected.png",
                "src/img/vanil.png","src/img/vanil_selected.png");
*/
        // on_end = new Ch("src/img/po.png", 480, 230); // Инициализация кнопки "on_end"


        Map<NewProduct, QuantityPopup> quantityPopups = new HashMap<>();
        QuantityPopup milkQuantityPopup = new QuantityPopup("milk",
                10, 1000, 200,
                "src/img/milk_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1),
                this);
        quantityPopups.put(milk, milkQuantityPopup);

        QuantityPopup iceQuantityPopup = new QuantityPopup("ice",
                10, 1000, 200,
                "src/img/ice_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1),
                this);
        quantityPopups.put(ice, iceQuantityPopup);
        this.quantityPopups = Collections.unmodifiableMap(quantityPopups);

        System.out.println(milk);

        //setLayout(null);
        Dimension backgroundSize = new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());
        setPreferredSize(backgroundSize);

        add(vanil);
        vanil.setBounds(50, 450, 250, 250);
        vanil.addMouseListener(this);

        add(milk);
        milk.setBounds(250, 370, 170, 192);
        milk.addMouseListener(this);



/*
        add(for2);
        for2.setBounds(1250, 700, 75, 75);*/


        add(ice);
        ice.setBounds(500, 350, 220, 220);

       /* add(milk);
        milk.setBounds(1000, 570, 230, 230);*/

        add(sugar);
        sugar.setBounds(1100, 350, 230, 230);

        add(tapioka);
        tapioka.setBounds(450, 600, 160, 160);

        add(tea);
        tea.setBounds(1000, 550, 180, 182);

        add(cup);
        cup.setBounds(670, 550, 180, 182);


    }

    /*public void paint(Graphics g)
    {

        g.drawImage(for_end._image, for_end.x, for_end.y,this);

    }*/

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        for(NewProduct product : quantityPopups.keySet()) {
            QuantityPopup quantityPopup = quantityPopups.get(product);
            if(source == quantityPopup) {
                remove(quantityPopup);
                quantityPopupShown = false;
                repaint();
                product.setPartialSize();
                product.returnToOriginalLocation();
                System.out.println(quantityPopup.getValue());
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        backgroundIcon.paintIcon(this, g, 0, 0);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Проверка, что источником события является 'on_end' и находящийся в пределах его границ.
      /*  if (event.getSource() == on_end && event.getX() >= 0 && event.getX() <= 70 && event.getY() >= 0 && event.getY() <= 70) {
            System.out.println("Интересный факт про страну, блюдо, которой ты будешь готовить");
            System.out.println("Let's start the game...");
            event.consume();
            layersContainer.showLayer(LayersContainer.Layer.END_THE_LEVEL_1);
        }*/
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        Object source = event.getSource();
        if(source instanceof NewProduct) {
            NewProduct product = (NewProduct)source;
            int x = event.getX() + product.getX();
            int y = event.getY() + product.getY();

            if(sugar.getBounds().contains(x, y)) {
                if(quantityPopups.containsKey(product)) {
                    QuantityPopup quantityPopup = quantityPopups.get(product);
                    quantityPopup.setLocation(300, 150);
                    add(quantityPopup);
                    moveToFront(quantityPopup);
                    quantityPopupShown = true;

                    event.consume();
                }
            }


        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle(Level_2.class.getSimpleName() + " test");
        Level_2 level = new Level_2();
        f.getContentPane().add(level);
        int width = level.backgroundIcon.getIconWidth();
        int height = level.backgroundIcon.getIconHeight();
        System.out.println(String.format("%d x %d", width, height));
        //f.setSize(width, height);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}