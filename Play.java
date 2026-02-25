import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Play extends JPanel implements MouseListener {

    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer)SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    private final Icon backgroundIcon;

    public Play() {
        backgroundIcon = new ImageIcon("src/img/background.png");

        Icon playButtonIcon = new ImageIcon("src/img/play.png");
        int playButtonWidth = playButtonIcon.getIconWidth();
        int playButtonHeight = playButtonIcon.getIconHeight();
        JLabel playButton = new JLabel(playButtonIcon);
        int x = (backgroundIcon.getIconWidth() - playButtonWidth) / 2; // center
        playButton.setBounds(x, 230, playButtonWidth, playButtonHeight);

        setLayout(null);
        add(playButton);
        playButton.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        backgroundIcon.paintIcon(this, g, 0, 0);
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if(SwingUtilities.isLeftMouseButton(event)) {
            System.out.println("Let's start the game...");
            if(layersContainer != null) { // only for testing via main
                layersContainer.showLayer(LayersContainer.Layer.FACT);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Recipe Challenge");
        Play play = new Play();
        f.getContentPane().add(play);
        int width = play.backgroundIcon.getIconWidth();
        int height = play.backgroundIcon.getIconHeight();
        System.out.println(String.format("%d x %d", width, height));
        f.setSize(width, height);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}