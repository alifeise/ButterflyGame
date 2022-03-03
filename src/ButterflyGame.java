import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import acm.graphics.*;
import acm.program.GraphicsProgram;

// Alison Feise
//
// Core Topics: Arrays, KeyEvents, random numbers, graphics
//
// This program will allow the user to play a simple game using the ACM Graphics window. The user will try to "catch"
// 5 butterflies using a movable net. The butterfliesare randomly placed on the screen, and the game will end when
// all butterflies are caught.


public class ButterflyGame extends GraphicsProgram {

    public static final int APPLICATION_HEIGHT = 500;
    public static final int APPLICATION_WIDTH = 800;

    public static final int FLY_HEIGHT = 50; // constants for butterfly size
    public static final int FLY_WIDTH = 40;

    GImage net; // make butterfly net visible in all methods

    public void run() {

        // set canvas background color to light blue
        setBackground(Color.decode("#B2DEF2"));

        // create net object
        drawNet();

        // create butterfly objects
        GImage[] butterflies = drawButterflies(5);

        // add key Listener for key press events
        addKeyListeners();

        // animate butterflies
        animateButterflies(butterflies, 5);

        }

    /**
     * This method creates a net image object which the user will be able to move using up and down arrows.
     * The image is added to the left side of the x-axis and middle y-axis in the window.
     */
    public void drawNet(){

        // assign the png to net image declared in main
        net = new GImage("net.png");

        // add to window
        add(net, APPLICATION_WIDTH - (2 * net.getWidth()), APPLICATION_HEIGHT / 2.0 - (net.getHeight() / 2)) ;

    } // end of method

    /**
     * This method creates an array of butterfly GImage objects and randomly assigns their location in the window space.
     * The array size is passed in as a parameter and an array of GImage objects is returned
     * @param size of objects in array
     * randomCoordinate method is called
     * @return butterfly, an array of GImage objects
     */
    public GImage[] drawButterflies(int size){

        // create a null array to hold butterfly images
        GImage[] butterfly = new GImage[size];

        // add each
        for (int i = 0; i < butterfly.length; i++) {

            butterfly[i] = new GImage("butterfly.png");
            butterfly[i].setBounds(randomCoordinate('x'), randomCoordinate('y'), FLY_HEIGHT, FLY_WIDTH);

            add(butterfly[i]); // add to window
        }
        return butterfly; // return butterfly array with images
    } // end of method

    /**
     * This method moves the net up, down. If the net is moved out of the window by the user, the net location is reset
     * to the upper or lower bounds of the window.
     *
     * @param e KeyEvent for key pressed
     */
    public void keyPressed(KeyEvent e) {

        // if up arrow is pressed and net is still in the window (y coordinate > 4) , move upwards
        if (e.getKeyCode() == KeyEvent.VK_UP && (net.getY() > 4)) {
            // move net upwards
            net.move(0, -10);
            //if (net.getY() <= 0) {
//                    net.setLocation(APPLICATION_WIDTH - (2 * net.getWidth()), 0) ;
//                }

        // if down arrow is pressed, check if net is still in window (excluding handle) and move down.
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            if (net.getY() < APPLICATION_HEIGHT - 10) { // allow handle to disappear so net can "catch" butterflies at bottom of window
                net.move(0, 10);
                // if net is "below" window, move upwards
            } else
                net.setLocation(APPLICATION_WIDTH - (2 * net.getWidth()), APPLICATION_HEIGHT - net.getHeight());
        }

    } // end of method

    public void animateButterflies(GImage[] toCatch, int size) {

        int collided = 0; // how many catches collided or were "caught" by net

        // continue iterating until all butterflies (determined by size), have been "caught"
        while (collided < size) {
            // go through the array of butterflies (toCatch) - with a for each loop
            for (GImage b : toCatch) {

                // move butterflies to the right
                b.move(10, 0);

                // if butterfly goes offscreen, randomly place butterfly in new window space
                if (b.getX() > APPLICATION_WIDTH) {
                    b.setLocation(randomCoordinate('x'), randomCoordinate('y'));
               }

                // create rectangle around the net, not including the net handle. Use this to intersect with butterflies.
                GRect onlyNet = new GRect(net.getX(), net.getY(), net.getWidth() - 20, net.getHeight() - 125);
                // add(onlyNet);

                // if catch collides with net AND catch is visible, increment collided count, make catch invisible
                if (onlyNet.getBounds().intersects(b.getBounds()) && b.isVisible()) { // if rectangle around net (no handle) intersects with rectangle around butterfly
                    collided ++;
                    b.setVisible(false); // make invisible
                }

                // pause 50 ms so human eye can see movement
                pause(50);
            } // end of for loop
        }

        // display a label when all butterflies are caught
        GLabel done = new GLabel("Game Over!", 10, 100);
        done.setFont("Helvetica-42");
        remove(net);
        add(done);

    } //  end of method

    /** This method generates an integer coordinate location that is in the current
     * window space. A 'x' or 'y' character is accepted as a coordinate parameter.
     * If 'x' or 'y' is not passed an IllegalArgumentException is thrown.
     * @param coordinate indicates whether x or y coordinate is desired
     * @return an integer coordinate location
     */
    public int randomCoordinate(char coordinate) {

        if (coordinate != 'x' && coordinate != 'y') {
            throw new IllegalArgumentException();
        }

        if (coordinate == 'x') {
            return new Random().nextInt(APPLICATION_WIDTH - FLY_WIDTH); // make sure part of butterfly will not be cut off by subtracting "FLY_WIDTH"else if (coordinate == 'y') {
        }
        return new Random().nextInt(APPLICATION_HEIGHT - FLY_HEIGHT / 2); // - (int) net.getHeight()

        //return 0;
    } // end of method

    /**
     * Opens an empty application window
     *
     * @param args not used
     */
    public static void main(String[] args) {
        new ButterflyGame().start();
    }
}
