import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    /**
     * game window variables
     */
    // the frame and panel
    private static JFrame frame;
    private static JPanel panel;
    // window size
    private static final int width = 800;
    private static final int height = 600;

    /**
     * color pallet
     */
    private static final Color c1 = new Color(190, 219, 187);
    private static final Color c2 = new Color(141, 181, 150);
    private static final Color c3 = new Color(146, 129, 122);
    private static final Color c4 = new Color(112, 112, 112);

    /**
     * player variables
     */
    private static boolean up = false;
    private static boolean down = false;
    private static boolean left = false;
    private static boolean right = false;
    // player motion variables
    private static final int maxVelocity = 5;
    // player current position
    private static int playerX = 0;
    private static int playerY = 0;
    // player size
    private static int playerWidth = 20;
    private static int playerHeight = 20;
    // collision variables
    private static boolean northSouthCollision = false;
    private static boolean eastWestCollision = false;

    /**
     * platform variables
     */
    private static Platform platform;

    /**
     * game variables
     */
    private static boolean windowShouldClose = false;
    private static boolean gameOver = false;
    private static long startTime = System.currentTimeMillis();
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

                // attempt to draw the player
                g.setColor(c2);
                g.fillRect(playerX, playerY, playerWidth, playerHeight);

                // attempt to draw the platform
                g.setColor(c4);
                g.fillRect(platform.getX1(), platform.getY1(), platform.getWidth(), platform.getHeight());
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
                }
            }
        };

        // set up the frame
        frame.setSize(new Dimension(width, height));
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
        platform = new Platform(width/4, height/4, width/4, height/4);

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

                movePlayer();
                panel.repaint();

                startTime = System.currentTimeMillis();
                try {
                    Thread.sleep(FPS);
                } catch (InterruptedException ignored) {
                }
            }
            startTime = System.currentTimeMillis();
            try {
                Thread.sleep(FPS);
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * moves the player and checks collision
     */
    public static void movePlayer() {
        // motion in the y direction
        //motion speed
        int dy = 0;
        if (up && !down) {
            dy = -maxVelocity;
        } else if (down && !up) {
            dy = maxVelocity;
        }

        // motion in the x direction
        int dx = 0;
        if (left && !right) {
            dx = -maxVelocity;
        } else if (right && !left) {
            dx = maxVelocity;
        }

        // attempt to move the player, check collision in the x and y direction
        int newX = playerX + dx;
        int newY = playerY + dy;
        // check collision
        if (!platform.intersects(newX, playerY, newX + playerWidth, playerY + playerHeight)) {
            playerX = newX;
        } else {
            if (dx > 0) {
                playerX = platform.getX1() - playerWidth;
            } else if (dx < 0) {
                playerX = platform.getX2();
            }
        }
        if (!platform.intersects(playerX, newY, playerX + playerWidth, newY + playerHeight)) {
            playerY = newY;
        } else {
            if (dy > 0) {
                playerY = platform.getY1() - playerWidth;
            } else if (dy < 0) {
                playerY = platform.getY2();
            }
        }
    }
}