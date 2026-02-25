import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Demo implements ActionListener {

  public static void main(String...args) {
    new Demo().demo1();
  }

  private interface ActionCommand {
    public static final String SHOW_DIALOG = "show dialog";
    public static final String SHOW_MODAL_DIALOG = "show modal dialog";
    public static final String SHOW_WINDOW = "show window";
    public static final String SHOW_OPTION_PANE = "show option pane";
    public static final String SHOW_INTERNAL_FRAME = "show internal frame";
  }

  private JDesktopPane desktop = new JDesktopPane(); 

  private void demo1() {
    //JFrame.setDefaultLookAndFeelDecorated(false);
    JFrame f = new JFrame("Demo-1");
    f.getContentPane().add(desktop);
    JPanel buttonPane = new JPanel();
    f.getContentPane().add(buttonPane, BorderLayout.NORTH);

    JButton showDialogButton = new JButton("Show dialog");
    showDialogButton.setActionCommand(ActionCommand.SHOW_DIALOG);
    buttonPane.add(showDialogButton);
    showDialogButton.addActionListener(this);

    JButton showModalDialogButton = new JButton("Show modal dialog");
    showModalDialogButton.setActionCommand(ActionCommand.SHOW_MODAL_DIALOG);
    buttonPane.add(showModalDialogButton);
    showModalDialogButton.addActionListener(this);

    JButton showWindowButton = new JButton("Show window");
    showWindowButton.setActionCommand(ActionCommand.SHOW_WINDOW);
    buttonPane.add(showWindowButton);
    showWindowButton.addActionListener(this);

    JButton showOptionPaneButton = new JButton("Show option pane");
    showOptionPaneButton.setActionCommand(ActionCommand.SHOW_OPTION_PANE);
    buttonPane.add(showOptionPaneButton);
    showOptionPaneButton.addActionListener(this);

    JButton showInternalFrameButton = new JButton("Show internal frame");
    showInternalFrameButton.setActionCommand(ActionCommand.SHOW_INTERNAL_FRAME);
    buttonPane.add(showInternalFrameButton);
    showInternalFrameButton.addActionListener(this);

    f.pack();
    f.setLocationRelativeTo(null);
    f.setVisible(true);
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();
    String actionCommand = event.getActionCommand();
    System.out.println(actionCommand);
    if(ActionCommand.SHOW_DIALOG.equals(actionCommand)) {
      JFrame f = (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, (Component)source);
      JDialog dialog = new JDialog(f, "Dialog", false);
      Icon icon = new ImageIcon("src/img/cheese_2_quantity.png");
      dialog.getContentPane().add(new JLabel(icon));
      dialog.pack();
      dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
      dialog.setLocationRelativeTo(f);
      dialog.setVisible(true);
    }
    else if(ActionCommand.SHOW_MODAL_DIALOG.equals(actionCommand)) {
      JFrame f = (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, (Component)source);
      //JDialog.setDefaultLookAndFeelDecorated(true);
      JDialog modalDialog = new JDialog(f, "Modal dialog", true);
      Icon icon = new ImageIcon("src/img/cheese_2_quantity.png");
      modalDialog.getContentPane().add(new JLabel(icon));
      modalDialog.pack();
      modalDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
      modalDialog.setLocationRelativeTo(f);
      modalDialog.setVisible(true);
    }
    else if(ActionCommand.SHOW_WINDOW.equals(actionCommand)) {
      JFrame f = (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, (Component)source);
      JWindow window = new JWindow(f);
      Icon icon = new ImageIcon("src/img/cheese_2_quantity.png");
      window.getContentPane().add(new JLabel(icon));
      window.pack();
      //window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
      window.setLocationRelativeTo(f);
      window.setVisible(true);
    }
    else if(ActionCommand.SHOW_OPTION_PANE.equals(actionCommand)) {
      JFrame f = (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, (Component)source);
      Icon icon = new ImageIcon("src/img/cheese_2_quantity.png");
      Object result = JOptionPane.showInputDialog(f, "Enter value:", "Standard input dialog",
        JOptionPane.QUESTION_MESSAGE, icon,
        new Object[]{null, "121 ml", "122 ml", "123 ml"}, null);
      //Object result = JOptionPane.showInputDialog(f, "Enter value:");
      System.out.println("Selected: " + result);
    }
    else if(ActionCommand.SHOW_INTERNAL_FRAME.equals(actionCommand)) {
      JFrame f = (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, (Component)source);
      boolean resizable = true;
      boolean closable = true;
      boolean maximizable = true;
      boolean iconifiable = true;
      JInternalFrame internalFrame = new JInternalFrame("Internal frame",
        resizable, closable, maximizable, iconifiable);
      Icon icon = new ImageIcon("src/img/cheese_2_quantity.png");
      internalFrame.getContentPane().add(new JLabel(icon));
      internalFrame.pack();
      internalFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
      desktop.add(internalFrame);
      internalFrame.setVisible(true);
    }
  }
}