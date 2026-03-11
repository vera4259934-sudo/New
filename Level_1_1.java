import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Level_1_1 extends JLayeredPane implements MouseListener, ActionListener {

    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer)SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    private final Icon backgroundIcon;
    private final NewProduct flour;
    private final NewProduct milk;
    private final NewProduct eggs;
    private final NewProduct cheese;
    private final NewProduct cheese2;
    private final NewProduct tomato;
    private final NewProduct ketchup;
    private final NewProduct oil;

    private NewProduct mixer;
    private NewProduct board;
    private NewProduct grater;
    private NewProduct dish;
    private NewProduct spoon;
    private NewProduct knife;
    private NewProduct boardWithCheese;
    private NewProduct boardWithCutCheese;

    private boolean tomatoInMixer = false;
    private boolean cheeseCutOnBoard = false;
    private boolean cheeseInGrater = false;
    private boolean milkInDish = false;
    private boolean eggsInDish = false;
    private boolean flourInDish = false;
    /*---------------------------------------------------------------------------------------*/
// Множество продуктов, для которых требуется окно ввода количества.
    private final Set<NewProduct> productsWithQuantityPopup = new HashSet<>();
    // Неизменяемая карта, связывающая продукты с их всплывающими окнами.
    private final Map<NewProduct, QuantityPopup> quantityPopups;
    boolean quantityPopupShown;

    public Level_1_1() {
        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("src/img/level1background.png"));

        flour = new NewProduct("flour", true,
                "src/img/flour.png", "src/img/flour_selected.png",
                "src/img/flourPartial.png", "src/img/flourPartial_selected.png");
        milk = new NewProduct("milk", true,
                "src/img/milkFull.png", "src/img/milkFullSelected.png",
                "src/img/milkPartial.png", "src/img/milkPartialSelected.png");
        eggs = new NewProduct("eggs", true,
                "src/img/egg.png", "src/img/egg_selected.png",
                "src/img/eggPartical.png", "src/img/eggPartical_selected.png");
        cheese = new NewProduct("cheese", true,
                "src/img/cheese_1.png", "src/img/cheese_1_selected.png",
                "src/img/cheese_1.png", "src/img/cheese_1_selected.png");
        cheese2 = new NewProduct("cheese2", true,
                "src/img/cheese_2.png", "src/img/cheese_2_selected.png",
                "src/img/cheese_2.png", "src/img/cheese_2_selected.png");
        tomato = new NewProduct("tomato", true,
                "src/img/tomato.png", "src/img/tomato_selected.png",
                "src/img/tomato.png", "src/img/tomato_selected.png");
        ketchup = new NewProduct("ketchup", true,
                "src/img/ketchup.png", "src/img/ketchup_selected.png",
                "src/img/ketchup.png", "src/img/ketchup_selected.png");
        oil = new NewProduct("oliveOil", true,
                "src/img/oil.png", "src/img/oil_selected.png",
                "src/img/oil.png", "src/img/oil_selected.png");

        // non-draggable:
        mixer = new NewProduct("mixer", false,
                "src/img/mixer.png", "src/img/mixer.png",
                "src/img/mixer.png", "src/img/mixer.png");
        board = new NewProduct("board", false,
                "src/img/board.png","src/img/board.png",
                "src/img/board.png","src/img/board.png");
        dish = new NewProduct("dish", false,
                "src/img/dish.png","src/img/dish.png",
                "src/img/dish.png","src/img/dish.png");
        grater = new NewProduct("grater", false,
                "src/img/grater.png", "src/img/grater.png",
                "src/img/grater.png", "src/img/grater.png");
        // draggable:
        spoon = new NewProduct("spoon", true,
                "src/img/spoon.png","src/img/spoon_selected.png",
                "src/img/spoon.png","src/img/spoon_selected.png");
        knife = new NewProduct("knife", true,
                "src/img/kneef.png","src/img/kneef_selected.png",
                "src/img/kneef.png","src/img/kneef_selected.png");
        boardWithCutCheese = new NewProduct("board_cheese_12", false,
                "src/img/board_cheese_12.png","src/img/board_cheese_12.png",
                "src/img/board_cheese_12.png","src/img/board_cheese_12.png");

        productsWithQuantityPopup.add(flour);
        productsWithQuantityPopup.add(milk);
        productsWithQuantityPopup.add(eggs);
        productsWithQuantityPopup.add(cheese);
        productsWithQuantityPopup.add(cheese2);
        productsWithQuantityPopup.add(tomato);
        productsWithQuantityPopup.add(knife);

        // Создание карты всплывающих окон.
        Map<NewProduct, QuantityPopup> quantityPopupsMap = new HashMap<>();

        QuantityPopup milkQuantityPopup = new QuantityPopup("milk",
                10, 1000, 200,
                "src/img/milk_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1),
                this);
        quantityPopupsMap.put(milk, milkQuantityPopup);

        QuantityPopup flourQuantityPopup = new QuantityPopup("flour",
                10, 1000, 200,
                "src/img/flour_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1),
                this);
        quantityPopupsMap.put(flour, flourQuantityPopup);

        QuantityPopup eggsQuantityPopup = new QuantityPopup("eggs",
                1, 6, 1,
                "src/img/egg_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1),
                this);
        quantityPopupsMap.put(eggs, eggsQuantityPopup);

        QuantityPopup knifeQuantityPopup = new QuantityPopup("eggs",
                1, 6, 1,
                "src/img/egg_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1),
                this);
        quantityPopupsMap.put(eggs, knifeQuantityPopup);

        // создание сыра
        QuantityPopup cheeseQuantityPopup = new QuantityPopup("cheese",
                1, 1000, 150, "src/img/cheese_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1),
                this);
        quantityPopupsMap.put(cheese, cheeseQuantityPopup);

        QuantityPopup cheese2QuantityPopup = new QuantityPopup("cheese2",
                1, 1000, 125, "src/img/cheese2_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1),
                this);
        quantityPopupsMap.put(cheese2, cheese2QuantityPopup);

        QuantityPopup tomatoQuantityPopup = new QuantityPopup("tomato",
                1, 20, 1, "src/img/tomato_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1),
                this);
        quantityPopupsMap.put(tomato, tomatoQuantityPopup);

        // Карта для всплывающих окон делается неизменяемой.
        this.quantityPopups = Collections.unmodifiableMap(quantityPopupsMap);

        //    System.out.println(milk);

        //setLayout(null);
        Dimension backgroundSize = new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());
        setPreferredSize(backgroundSize);

        add(flour);
        flour.setBounds(50, 470, 250, 250);
        flour.addMouseListener(this);

        add(milk);
        milk.setBounds(230, 360, 170, 192);
        milk.addMouseListener(this);

        add(eggs);
        eggs.setBounds(950, 400, 300, 116);
        eggs.addMouseListener(this);

        add(cheese);
        cheese.setBounds(370, 390, 200, 108);
        cheese.addMouseListener(this);

        add(cheese2);
        cheese2.setBounds(725, 390, 170, 150);
        cheese2.addMouseListener(this);

        add(tomato);
        tomato.setBounds(1150, 370, 222, 208);
        tomato.addMouseListener(this);

        add(ketchup);
        ketchup.setBounds(500, 310, 155, 220);

        add(oil);
        oil.setBounds(550, 290, 220, 226);


        add(mixer);
        mixer.setBounds(25, 300, 220, 290);

        add(board);
        board.setBounds(1000, 570, 370, 230);

        add(grater);
        grater.setBounds(750, 300, 370, 230);

        add(dish);
        dish.setBounds(540, 505, 245, 230);

        add(spoon);
        spoon.setBounds(830, 600, 160, 160);

        add(knife);
        knife.setBounds(305, 630, 260, 50);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        knife.addMouseListener(this);

        //-------------------------------------------------------------------------------------------------------------
        Object source = event.getSource();
        // Ищет, какое всплывающее окно сгенерировало событие.
        for (Map.Entry<NewProduct, QuantityPopup> entry : quantityPopups.entrySet()) {
            NewProduct product = entry.getKey();
            QuantityPopup quantityPopup = entry.getValue();
            if (source == quantityPopup) {
                remove(quantityPopup); // Удаляет всплывающее окно.
                quantityPopupShown = false;
                repaint();// Сбрасывает флаг.
                product.setPartialSize(); // Устанавливает частичный размер продукта.
                product.returnToOriginalLocation(); // Возвращает продукт на место.
                System.out.println("Selected quantity for " + product.getName() + ": " + quantityPopup.getValue());


                if (product == cheese2) {
                    remove(board); // Удаляет старую доску.
                    // Создает новую доску с изображением сыра.
                    boardWithCheese = new NewProduct("board_cheese_1", false,
                            "src/img/board_cheese_1.png", "src/img/board_cheese_1.png",
                            "src/img/board_cheese_1.png", "src/img/board_cheese_1.png");
                    boardWithCheese.setBounds(board.getBounds()); // Сохраняет положение и размер.
                    add(boardWithCheese); // Добавляет новую доску.
                    this.board = boardWithCheese; // Обновляет ссылку на доску.
                    repaint(); // Перерисовывает компонент.
                }

                if (product == cheese) {
                    remove(grater); // Удаляет старую доску.
                    NewProduct boardWithCheese = new NewProduct("grater_cheese", false,
                            "src/img/grater_cheese.png", "src/img/grater_cheese.png",
                            "src/img/grater_cheese.png", "src/img/grater_cheese.png");
                    boardWithCheese.setBounds(grater.getBounds()); // Сохраняет положение и размер.
                    add(boardWithCheese); // Добавляет новую доску.
                    this.grater = boardWithCheese; // Обновляет ссылку на доску.
                    cheeseInGrater = true; // Устанавливаем флаг
                    checkAndSwitchLevel();// Обновляет ссылку на доску.
                    repaint(); // Перерисовывает компонент.
                }


                if (product == tomato) {
                    remove(mixer); // Удаляет старую доску.
                    // Создает новую доску с изображением сыра.
                    NewProduct boardWithCheese = new NewProduct("mixer_tomato", false,
                            "src/img/mixer_tomato.png", "src/img/mixer_tomato.png",
                            "src/img/mixer_tomato.png", "src/img/mixer_tomato.png");
                    boardWithCheese.setBounds(mixer.getBounds()); // Сохраняет положение и размер.
                    add(boardWithCheese); // Добавляет новую доску.
                    this.mixer = boardWithCheese;
                    tomatoInMixer = true; // Устанавливаем флаг
                    checkAndSwitchLevel();// Обновляет ссылку на доску.
                    repaint(); // Перерисовывает компонент.
                }

                return; // Прерывает цикл после обработки.
            }
        }
        //---------------------------------------------------------------------------------



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
    }

    @Override
    public void mouseReleased(MouseEvent event) {

        //-------------------------------------------------------------------------------------
        Object source = event.getSource();

        if (source == knife) {
            // Проверяем, есть ли уже доска с нарезанным сыром (boardWithCutCheese).
            // Если да, и нож пересекает ее, ничего не делаем.
            if (this.boardWithCutCheese != null && this.boardWithCutCheese.getBounds().intersects(knife.getBounds())) {
                // Нож прошел по уже нарезанному сыру, ничего не меняем.
                return; // Выходим, чтобы не проверять другие условия
            }

            // Если доска с нарезанным сыром еще не создана,
            // Проверяем, является ли текущая доска "board_cheese_1" (доска с целым сыром).
            if (this.board != null && this.board.getName().equals("board_cheese_1")) {
                Rectangle knifeBounds = knife.getBounds();
                Rectangle boardBounds = this.board.getBounds();

                if (knifeBounds.intersects(boardBounds)) {
                    // Если нож пересек доску с целым сыром, удаляем ее.
                    remove(this.board);
                    // Создаем новую доску с нарезанным сыром и добавляем ее.
                    NewProduct boardWithCutCheese = new NewProduct("board_cheese_2", false, "src/img/board_cheese_12.png", "src/img/board_cheese_12.png", "src/img/board_cheese_12.png", "src/img/board_cheese_12.png");
                    boardWithCutCheese.setBounds(boardBounds);
                    add(boardWithCutCheese);
                    this.board = boardWithCutCheese;
                    cheeseCutOnBoard = true; // Устанавливаем флаг
                    checkAndSwitchLevel();// Обновляем ссылку на текущую доску
                    repaint();
                } else {
                    // Если нож не попал по доске, возвращаем его на место.
                    knife.returnToOriginalLocation();
                    repaint();
                }
            } else {
                // Если это обычная доска или чего-то нет, нож возвращается на место.
                knife.returnToOriginalLocation();
                repaint();
            }
        }

        if (source instanceof NewProduct) {
            NewProduct product = (NewProduct) source;
            // Вычисляем абсолютные координаты курсора.
            int x = event.getX() + product.getX();
            int y = event.getY() + product.getY();

            if (source == eggs && dish.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(eggs, x, y, event);
                eggsInDish= true;
                checkAndSwitchLevel();
            }

            if (source == flour && dish.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(flour, x, y, event);
                flourInDish= true;
                checkAndSwitchLevel();
            }

            if (source == milk && dish.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(milk, x, y, event);
                milkInDish= true;
                checkAndSwitchLevel();
            }
//---------------------------------------------------------------------------------
            /*if ( source==knife)
            {
                System.out.println("knife");
                if (boardWithCheese.name == "board_cheese_1") {
                    System.out.println("cheese");
                    if (boardWithCheese.getBounds().contains(x, y)) {
                        System.out.println("intersect");
                    }
                }
                //showQuantityPopup(knife, x, y, event);
                //System.out.println("111111111111111111111111111");
            }*/



//-----------------------------------------------------------------------------------

            if (source == tomato && mixer.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(tomato, x, y, event);
            }

           /* if (source == knife && board_cheese_1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(milk, x, y, event);
            }*/

            if (source == cheese && grater.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(cheese, x, y, event);
                // System.out.println("14144444");
            }

            // Проверка, если сыр отпущен на доске.
            if (source == cheese2 && board.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(cheese2, x, y, event);
            }
        }
    }

    // Вспомогательный метод для показа всплывающего окна.
    private void showQuantityPopup(NewProduct product, int x, int y, MouseEvent event) {
        if (!quantityPopupShown) {
            QuantityPopup quantityPopup = quantityPopups.get(product);
            quantityPopup.setLocation(300, 150); // Устанавливаем позицию окна.
            add(quantityPopup);
            moveToFront(quantityPopup); // Перемещаем на передний план.
            quantityPopupShown = true;
            event.consume(); // Потребляем событие.
        }

    }


    // Проверяет, выполнены ли условия для перехода на новый уровень
    private void checkAndSwitchLevel() {
        if (tomatoInMixer && cheeseCutOnBoard&& cheeseInGrater&& milkInDish&& flourInDish&& eggsInDish) { // Оба условия выполнены
            if (layersContainer != null) {
                // Переключаемся на новый слой
                layersContainer.showLayer(LayersContainer.Layer.RECIPE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle(Level_1_1.class.getSimpleName() + " test");
        Level_1_1 level = new Level_1_1();
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
