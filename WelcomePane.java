import java.io.*;
import java.awt.*;
import java.util.*;

import javax.swing.*;

public class WelcomePane extends JPanel {
  
  private final Image backgroundImage; 
  // 1360 x 770
  private final int backgroundImageWith;
  private final int backgroundImageHeight;

  private final Image buttonImage;
  // 460 x 195
  private final int buttonImageWidth;
  private final int buttonImageHeight;
  private final int buttonX;
  private final int buttonY;
  //private final Rectangle buttonRect = new Rectangle(buttonX, buttonY, buttonImageWidth, buttonImageHeight);

  protected final Properties p = new Properties();

  public WelcomePane() {
    String filename = WelcomePane.class.getSimpleName() + ".properties";
    InputStream in = WelcomePane.class.getResourceAsStream(filename);
    try {
      p.load(in);
    }
    catch(IOException oops) {
      oops.printStackTrace(System.err);
    }

    ImageLoader imageLoader = ImageLoader.getInstance();

    String backgroundImagePath = p.getProperty("background-image.image-path");
    backgroundImage = imageLoader.loadImage(backgroundImagePath, this);
    backgroundImageWith = backgroundImage.getWidth(null);
    backgroundImageHeight = backgroundImage.getHeight(null);
    System.out.println(String.format("%s: %d vs %d",
      backgroundImagePath, backgroundImageWith, backgroundImageHeight));

    String buttonImagePath = p.getProperty("play-button.image-path");
    buttonImage = imageLoader.loadImage(buttonImagePath, this);
    buttonImageWidth = buttonImage.getWidth(null);
    buttonImageHeight = buttonImage.getHeight(null);
    buttonX = Integer.parseInt(p.getProperty("play-button.x"));
    buttonY = Integer.parseInt(p.getProperty("play-button.y"));
    System.out.println(String.format("%s: %d vs %d @ %dx%d",
      buttonImagePath, buttonImageWidth, buttonImageHeight, buttonX, buttonY));

    //addMouseListener(this);
    //enableEvents(AWTEvent.MOUSE_EVENT_MASK);
  }
      /*
  public void paintComponent(Graphics g) {
    double xRatio = getWidth();
    xRatio /= backgroundImageWith;

    double yRatio = getHeight();
    yRatio /= backgroundImageHeight;
    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

    int buttonWidth = (int)(this.buttonImageWidth * xRatio);
    int buttonHeight = (int)(this.buttonImageHeight * yRatio);
    int buttonX = (int)(this.buttonX * xRatio);
    int buttonY = (int)(this.buttonY * yRatio);
    buttonRect.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
    g.drawImage(buttonImage, buttonX, buttonY, buttonWidth, buttonHeight, this);
  }

  @Override
  protected void processMouseEvent(MouseEvent event) {
    if(event.getID() == MouseEvent.MOUSE_CLICKED) {
      if(SwingUtilities.isLeftMouseButton(event)) {
        if(buttonRect.contains(event.getX(), event.getY())) {
          System.out.println("Let's start the game...");
          event.consume();
          layersContainer.showLayer(LayersContainer.Layer.LEVEL_1);
        }
      }
    }
  }
  */

  public static void main(String...args) {
    new WelcomePane();
  }
}