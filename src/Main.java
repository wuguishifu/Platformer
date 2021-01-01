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
    public static final int width = 800;
    public static final int height = 600;

    // the color pallet
    public static final Color c1 = new Color(190, 219, 187);
    public static final Color c2 = new Color(141, 181, 150);
    public static final Color c3 = new Color(146, 129, 122);
    public static final Color c4 = new Color(112, 112, 112);

    // the player
    private static Player player;
    private static boolean up = false, down = false, left = false, right = false, jump = false;

    // the platforms
    private static ArrayList<Platform> platforms = new ArrayList<>();

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

                // draw the platform
                for (Platform p : platforms) {
                    p.paint(g);
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
                        up = true;
                        break;
                    case KeyEvent.VK_A:
                        left = true;
                        break;
                    case KeyEvent.VK_S:
                        down = true;
                        break;
                    case KeyEvent.VK_D:
                        right = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        jump = true;
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
                        up = false;
                        break;
                    case KeyEvent.VK_A:
                        left = false;
                        break;
                    case KeyEvent.VK_S:
                        down = false;
                        break;
                    case KeyEvent.VK_D:
                        right = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        jump = false;
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

                // update the game objects
                update();

                // repaint the window
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

    /**
     * updates the game objects
     */
    private static void update() {
        // create a list of hitboxes of every other game object in the level
        ArrayList<Hitbox> hitboxes = new ArrayList<>();
        for (Platform p : platforms) {
            hitboxes.add(p.getHitbox());
        }

        // update the player
        // create a flag for moving the player
        int flag = up ? Player.FLAG_MOVE_UP : 0;
        flag = down ? flag | Player.FLAG_MOVE_DOWN : flag;
        flag = left ? flag | Player.FLAG_MOVE_LEFT : flag;
        flag = right ? flag | Player.FLAG_MOVE_RIGHT : flag;
        flag = jump ? flag | Player.FLAG_JUMP : flag;
        player.enablePlayerMotion(flag);
        // move the player
        player.update(hitboxes);

        for (Platform p : platforms) {
            p.update();
        }
    }
}