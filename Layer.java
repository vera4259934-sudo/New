import javax.swing.*;

public abstract class Layer extends JPanel {

  protected LayersContainer layersContainer;

  @Override
  public void addNotify() {
    super.addNotify();
    this.layersContainer = (LayersContainer)SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
  }
}