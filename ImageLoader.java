import java.awt.*;
import java.net.URL;

public class ImageLoader {

  public static final ImageLoader getInstance() {
    return INSTANCE;
  }
  private static final ImageLoader INSTANCE = new ImageLoader();

  public Image loadImage(String path, Component c) {
    MediaTracker tracker = new MediaTracker(c);

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    URL resource = getClass().getClassLoader().getResource(path);
    Image image = toolkit.createImage(resource);
    tracker.addImage(image, 0);
    try {
      tracker.waitForID(0);
    }
    catch (InterruptedException oops) {
    }
    return image;
  }
}