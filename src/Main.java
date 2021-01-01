import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main {

    // the frame and panel
    private static JFrame frame;
    private static JPanel panel;

    // window size
    private static final int width = 800;
    private static final int height = 600;

    // the color pallet
    public static final Color c1 = new Color(190, 219, 187);
    public static final Color c2 = new Color(141, 181, 150);
    public static final Color c3 = new Color(146, 129, 122);
    public static final Color c4 = new Color(112, 112, 112);

    // the player
    private static Player player;

    // the platforms
    private static ArrayList<Platform> platforms = new ArrayList<>();

    // the player

    /**
     * game variables
     */
    private static boolean windowShouldClose = false;
    private static boolean gameOver = false;
    private static final long FPS = 20;

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

                // paint the player
                player.paint(g);

                // attempt to draw the platform
                g.setColor(c4);
                for (Platform platform : platforms) {
                    g.fillRect(platform.getX1(), platform.getY1(), platform.getWidth(), platform.getHeight());
                }
            }
        };
        panel.setSize(new Dimension(width, height));
        panel.setBackground(c1);

        // initialize and set up the key listener
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        player.setPlayerMotion(Player.FLAG_MOVE_UP, true);
                        break;
                    case KeyEvent.VK_A:
                        player.setPlayerMotion(Player.FLAG_MOVE_LEFT, true);
                        break;
                    case KeyEvent.VK_S:
                        player.setPlayerMotion(Player.FLAG_MOVE_DOWN, true);
                        break;
                    case KeyEvent.VK_D:
                        player.setPlayerMotion(Player.FLAG_MOVE_RIGHT, true);
                        break;
                    case KeyEvent.VK_SPACE:
                        player.setPlayerMotion(Player.FLAG_JUMP, true);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        windowShouldClose = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        player.setPlayerMotion(Player.FLAG_MOVE_UP, false);
                        break;
                    case KeyEvent.VK_A:
                        player.setPlayerMotion(Player.FLAG_MOVE_LEFT, false);
                        break;
                    case KeyEvent.VK_S:
                        player.setPlayerMotion(Player.FLAG_MOVE_DOWN, false);
                        break;
                    case KeyEvent.VK_D:
                        player.setPlayerMotion(Player.FLAG_MOVE_RIGHT, false);
                        break;
                }
            }
        };

        // set up the frame
        frame.setSize(new Dimension(width, height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add the components to the frame
        frame.add(panel);
        frame.addKeyListener(keyListener);

        // display the frame
        frame.setVisible(true);

        // fix the frame size
        Dimension actualSize = frame.getContentPane().getSize();
        int extraW = width - actualSize.width;
        int extraH = height - actualSize.height;
        frame.setSize(width + extraW, height + extraH);

        // set up game objects
        platforms.add(new Platform(0, 5*height/6, width/4, height/6));
        platforms.add(new Platform(0, 17*height/18, width, height/18+10));
        player = new Player(0, 0);

        // run the main game loop
        run();

        // clean up afterwards
        frame.dispose();
    }

    /**
     * handles the main game loop
     */
    public static void run() {
        while (!windowShouldClose) {
            while (!gameOver && !windowShouldClose) {

                // update the player
                player.update(platforms);
                panel.repaint();

                try {
                    Thread.sleep(FPS); // delay 20 ms
                } catch (InterruptedException ignored) {
                }
            }
            try {
                Thread.sleep(FPS); // delay 20 ms
            } catch (InterruptedException ignored) {
            }
        }
    }
}