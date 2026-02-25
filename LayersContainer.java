import java.awt.CardLayout;

import javax.swing.*;

public class LayersContainer extends JPanel {

  public static enum Layer {
    WELCOME,
    FACT,
    RECIPE,
    LEVEL_1,
  }

  protected final CardLayout cardLayout = new CardLayout();

  public LayersContainer() {
    setLayout(cardLayout);
    Play play = new Play();
    Screen_fact screenFact = new Screen_fact();
    ScreenRecipe screenRecipe = new ScreenRecipe();

    add(play, String.valueOf(Layer.WELCOME));
    //add(new WelcomePane(), String.valueOf(Layer.WELCOME));
    add(screenFact, String.valueOf(Layer.FACT));
    add(screenRecipe, String.valueOf(Layer.RECIPE));
    add(new Level_1(), String.valueOf(Layer.LEVEL_1));
    //add(new LevelNumber1(), String.valueOf(Layer.LEVEL_1));
  }

  public void showLayer(Layer id) {
    cardLayout.show(this, String.valueOf(id));
  }
}