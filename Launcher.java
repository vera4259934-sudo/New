import javax.swing.*;

public class Launcher implements Runnable {

  public static void main(String...args) {
    new Launcher(args).execute();
  }

  private final String[] args;

  public Launcher(String[] args) {
    this.args = args;
  }

  public void execute() {
    SwingUtilities.invokeLater(this);
  }

  @Override
  public void run() {
    JFrame f = new JFrame("Best game ever");

    f.getContentPane().add(new LayersContainer());

    f.setSize(1360, 770);

    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}