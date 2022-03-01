import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import acm.graphics.*;
import acm.program.GraphicsProgram;

// Alison Feise
//
// Core Topics:
//
// This program will...


public class Butterflies extends GraphicsProgram {

    public static final int APPLICATION_HEIGHT = 800;
    public static final int APPLICATION_WIDTH = 1000;

    GImage net; // make butterfly visible in all methods
    public static final int FLY_HEIGHT = 50;
    public static final int FLY_WIDTH = 40;


    public void run() {

        // create net object
        drawNet();

        // create butterfly objects
        drawButterflies(10);

    }

    public void drawNet(){
        // assign the png to net image declared in main
        net = new GImage("net.png");

        // add to window
        add(net, APPLICATION_WIDTH - (2 * net.getWidth()), APPLICATION_HEIGHT / 2 - (net.getHeight() / 2)) ;
    }

    public GImage[] drawButterflies(int size){

        GImage[] butterfly = new GImage[size];

        for (int i = 0; i < butterfly.length; i++) {

            butterfly[i] = new GImage("butterfly.png");
            butterfly[i].setBounds(randomCoordinate('x'), randomCoordinate('y'), FLY_HEIGHT, FLY_WIDTH);
            //butterfly[i] = new GRect(randomCoordinate('x'), randomCoordinate('y'), TARGET_SIZE, TARGET_SIZE / 2);

            add(butterfly[i]);
        }

//        }



        return butterfly;
    }

    public void keyPressed(KeyEvent e) {

    }

    public void animateButteflies(GImage[] butterflies) {


    }

    public int randomCoordinate(char coordinate) {
        if (coordinate != 'x' && coordinate != 'y') {
            throw new IllegalArgumentException();
        }

        if (coordinate == 'x') {
            return new Random().nextInt(APPLICATION_WIDTH - FLY_WIDTH);
        } else if (coordinate == 'y') {
            return new Random().nextInt(APPLICATION_HEIGHT - FLY_HEIGHT / 2);
        }
        return 0;
    }



    public static void main(String[] args) {
        new Butterflies().start();
    }
}
