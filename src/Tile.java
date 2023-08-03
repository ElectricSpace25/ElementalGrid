import processing.core.PApplet;
import processing.core.PVector;

public class Tile extends PApplet {
    PVector pos;
    PVector size;
    Element element;
    int value;

    Tile up;
    Tile down;
    Tile left;
    Tile right;

    public Tile(PVector pos, PVector size) {
        this.pos = pos;
        this.size = size;
        element = Element.NONE;
        value = 1;
    }

    public void display() {

        //Choose stroke color
        if (selected()) {
            Main.processing.strokeWeight(4);
            Main.processing.stroke(240);
        }
        else {
            Main.processing.strokeWeight(2);
            Main.processing.stroke(0);
        }

        //Choose fill color
        Main.processing.fill(element.r, element.g, element.b);

        //Draw
        Main.processing.rect(pos.x, pos.y, size.x, size.y);
        Main.processing.fill(0);

        //Text
        Main.processing.textSize(20);
        Main.processing.textAlign(CENTER);
        if (element != Element.NONE) Main.processing.text(value, (pos.x + (size.x / 2)), (pos.y + 4 * (size.y / 7)));

    }

    public boolean selected() {
        if (Main.processing.mouseX >= pos.x && Main.processing.mouseX <= pos.x + size.x) {
            if (Main.processing.mouseY >= pos.y && Main.processing.mouseY <= pos.y + size.y)
                return true;
        }

        return false;
    }

    public void toggle(int mouseButton) {
        if (mouseButton == LEFT) {
            element = Main.activeElement;
        } else if (mouseButton == RIGHT) {
            element = Element.NONE;
        }
        Main.calculateAllValues();
    }

    public void calculateValue() {
        value = 1;

        try {
            if (element.dislikes.contains(up.element)) value--;
            if (element.likes.contains(up.element)) value++;
        } catch (NullPointerException e) {};

        try {
            if (element.dislikes.contains(down.element)) value--;
            if (element.likes.contains(down.element)) value++;
        } catch (NullPointerException e) {};

        try {
            if (element.dislikes.contains(left.element)) value--;
            if (element.likes.contains(left.element)) value++;
        } catch (NullPointerException e) {};

        try {
            if (element.dislikes.contains(right.element)) value--;
            if (element.likes.contains(right.element)) value++;
        } catch (NullPointerException e) {};

        element.value += value;

    }

}
