import java.util.ArrayList;
import java.util.Arrays;

public enum Element{
    NONE, FIRE, ICE, EARTH, AIR;

    public String name;
    public int r;
    public int g;
    public int b;
    public ArrayList<Element> likes;
    public ArrayList<Element> dislikes;
    int value;

    static {
        NONE.name = "None";
        NONE.r = 255;
        NONE.g = 255;
        NONE.b = 255;
        NONE.likes = new ArrayList<Element>(Arrays.asList());
        NONE.dislikes = new ArrayList<Element>(Arrays.asList());

        FIRE.name = "Fire";
        FIRE.r = 255;
        FIRE.g = 0;
        FIRE.b = 0;
        FIRE.likes = new ArrayList<Element>(Arrays.asList(FIRE, AIR));
        FIRE.dislikes = new ArrayList<Element>(Arrays.asList(EARTH));

        ICE.name = "Ice";
        ICE.r = 0;
        ICE.g = 230;
        ICE.b = 255;
        ICE.likes = new ArrayList<Element>(Arrays.asList(ICE, AIR));
        ICE.dislikes = new ArrayList<Element>(Arrays.asList(FIRE));

        EARTH.name = "Earth";
        EARTH.r = 150;
        EARTH.g = 80;
        EARTH.b = 0;
        EARTH.likes = new ArrayList<Element>(Arrays.asList(EARTH, FIRE));
        EARTH.dislikes = new ArrayList<Element>(Arrays.asList(ICE, AIR));

        AIR.name = "Air";
        AIR.r = 200;
        AIR.g = 230;
        AIR.b = 255;
        AIR.likes = new ArrayList<Element>(Arrays.asList(AIR, FIRE));
        AIR.dislikes = new ArrayList<Element>(Arrays.asList(EARTH));

    }
}
