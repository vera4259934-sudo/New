import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Screen_fact extends JPanel {

  private LayersContainer layersContainer;

  @Override
  public void addNotify() {
    super.addNotify();
    this.layersContainer = (LayersContainer)SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
  }


public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setTitle("Recipe Challenge");
    f.getContentPane().add(new Screen_fact());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(1330, 770);
    f.setVisible(true);
}

Image fon = Toolkit.getDefaultToolkit().createImage("src/img/Screen_fact.png");
Ch KnopkaTheRecipe = new Ch("src/img/To_the_recipe.png",1000,400);

/*Screen_fact()
{
    getContentPane().add(new JLabel(new ImageIcon("src/img/Screen_fact.png")));
    pack();
    setVisible(true);
    setTitle("To the recipe");
    addKeyListener(keyListener);
    addMouseListener(mouseListener);
}*/
public Screen_fact() {
    //setTitle("Recipe Challenge");
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //setSize(1330, 770);
    this.layersContainer = layersContainer;
    addKeyListener(keyListener);
    addMouseListener(mouseListener);
    //setVisible(true);
    //startTimer();
}

public void paint(Graphics g)
{
    g.drawImage(fon,0,0,this);

    g.drawImage(KnopkaTheRecipe._image, KnopkaTheRecipe.x, KnopkaTheRecipe.y,this);

}

private KeyListener keyListener = new KeyAdapter() {
};

private MouseListener mouseListener = new MouseAdapter() {

    @Override
    public void mouseClicked(MouseEvent qwerty) {
        if (qwerty.getX() >= KnopkaTheRecipe.x && qwerty.getX() <= (KnopkaTheRecipe.x + 420) && qwerty.getY() >= KnopkaTheRecipe.y && qwerty.getY() <= (KnopkaTheRecipe.y + 220)) {
            System.out.println("Начинаем игру");
           // dispose();
            JFrame Recipe = new JFrame("Recipe");
            //Recipe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Recipe.add(new ScreenRecipe());
           // Recipe.pack();
            //Recipe.setVisible(true);
            qwerty.consume();
            layersContainer.showLayer(LayersContainer.Layer.RECIPE);
        }
    }
};
}
