import javax.swing.*;
import java.awt.*;

public class Main {

    // game window variables
    // the frame and panel
    private static JFrame frame;
    private static JPanel panel;
    // window size
    private static final int width = 800;
    private static final int height = 600;

    // color pallet
    private static final Color c1 = new Color(190, 219, 187);
    private static final Color c2 = new Color(141, 181, 150);
    private static final Color c3 = new Color(146, 129, 122);
    private static final Color c4 = new Color(112, 112, 112);

    /**
     * main method
     * @param args - arguments
     */
    public static void main(String[] args) {

        // initialize the frame
        frame = new JFrame();

        // initialize and set up the panel
        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(c4);
                g.fillRect(50, 50, 50, 50);
            }
        };
        panel.setSize(new Dimension(width, height));
        panel.setBackground(c1);
        panel.repaint();

        // set up the frame
        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // add the components to the frame
        frame.add(panel);

        // display the frame
        frame.setVisible(true);
    }
}