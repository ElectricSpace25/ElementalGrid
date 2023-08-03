import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main extends PApplet {

    public static PApplet processing;

    public static Element activeElement = Element.NONE;

    static int challengeRatio;
    static Element challengeElement1;
    static Element challengeElement2;
    static boolean challengeComplete = false;

    //Buttons
    static PVector buttonsStart = new PVector(10, 10);
    static PVector buttonSize = new PVector(100, 100);

    static Button fire = new Button(Element.FIRE, new PVector(buttonsStart.x, buttonsStart.y), buttonSize, true);
    static Button ice = new Button(Element.ICE, new PVector(buttonsStart.x, buttonsStart.y + buttonSize.x), buttonSize, true);
    static Button earth = new Button(Element.EARTH, new PVector(buttonsStart.x, buttonsStart.y + (buttonSize.x * 2)), buttonSize, true);
    static Button air = new Button(Element.AIR, new PVector(buttonsStart.x, buttonsStart.y + (buttonSize.x * 3)), buttonSize, true);

    static ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(fire, ice, earth, air));
    static ArrayList<Tile> tiles = new ArrayList<>();

    //Grid settings
    static PVector gridStart = new PVector(buttonsStart.x + buttonSize.x + 50, buttonsStart.y);
    static PVector tileSize = new PVector(100, 100);
    static int rows = 3;
    static int cols = 3;

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings() {
        size(600, 450);
    }

    public void setup() {
        processing = this;

        //Create grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles.add(new Tile(new PVector(gridStart.x + (tileSize.x * j), gridStart.y + (tileSize.y * i)), new PVector(tileSize.x, tileSize.y)));
            }
        }

        //Determine tile neighbors
        for (Tile tile : tiles) {
            int index = tiles.indexOf(tile);

            int row = index / cols;
            int col = index % cols;

            System.out.println("Tile " + index);
            System.out.println("Row: " + row);
            System.out.println("Col: " + col);
            System.out.println();

            // Check top neighbor
            if (row > 0) {
                tile.up = tiles.get(index - cols);
            }

            // Check bottom neighbor
            if (row < rows - 1) {
                tile.down = tiles.get(index + cols);
            }

            // Check left neighbor
            if (col > 0) {
                tile.left = tiles.get(index - 1);
            }

            // Check right neighbor
            if (col < cols - 1) {
                tile.right = tiles.get(index + 1);

            }
        }

        //Challenge
        challengeRatio = randomNum(1, 3);
        Element[] elements = Element.values();
        challengeElement1 = elements[randomNum(1, elements.length - 1)];
        challengeElement2 = elements[randomNum(1, elements.length - 1)];
        while (challengeElement1 == challengeElement2) challengeElement2 = elements[randomNum(1, elements.length - 1)];

    }

    public void draw() {
        background(200);

        //Draw buttons
        for (Button b : buttons) {
            b.display();
        }

        //Draw grid tiles
        for (Tile t : tiles) {
            t.display();
        }

        //Draw values
        textSize(20);
        textAlign(LEFT);
        fill(Element.FIRE.r - 70, Element.FIRE.g - 70, Element.FIRE.b - 70);
        text("Fire: " + Element.FIRE.value, gridStart.x + (cols * tileSize.x) + 10, gridStart.y + 20);
        fill(Element.ICE.r - 70, Element.ICE.g - 70, Element.ICE.b - 70);
        text("Ice: " + Element.ICE.value, gridStart.x + (cols * tileSize.x) + 10, gridStart.y + 50);
        fill(Element.EARTH.r - 70, Element.EARTH.g - 70, Element.EARTH.b - 70);
        text("Earth: " + Element.EARTH.value, gridStart.x + (cols * tileSize.x) + 10, gridStart.y + 80);
        fill(Element.AIR.r - 70, Element.AIR.g - 70, Element.AIR.b - 70);
        text("Air: " + Element.AIR.value, gridStart.x + (cols * tileSize.x) + 10, gridStart.y + 110);

        //Draw challenge
        textSize(20);
        textAlign(LEFT);
        if (!challengeComplete) fill(255, 0, 0);
        else fill(0, 200, 0);
        text("Challenge: 1:" + challengeRatio + " ratio of " + challengeElement1.name + " and " + challengeElement2.name, gridStart.x, gridStart.y + (tileSize.y * rows) + 30);
    }

    public void mousePressed() {
        //Buttons
        for (Button b : buttons) {
            if (b.selected()) b.toggle(mouseButton);
        }

        //Grid tiles
        for (Tile t : tiles) {
            if (t.selected()) t.toggle(mouseButton);
        }
    }

    public static void calculateAllValues() {
        Element.FIRE.value = 0;
        Element.ICE.value = 0;
        Element.EARTH.value = 0;
        Element.AIR.value = 0;
        for (Tile t : tiles) {
            t.calculateValue();
        }

        challengeComplete = challengeElement1.value != 0 && challengeElement2.value != 0 &&
                (double) challengeElement2.value / (double) challengeElement1.value == challengeRatio;
    }

    public static int randomNum(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }


}
