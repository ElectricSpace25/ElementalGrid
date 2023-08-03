import processing.core.PApplet;
import processing.core.PVector;

public class Button extends PApplet {
    PVector pos;
    PVector size;
    Element element;
    boolean clickable;

    public Button(Element element, PVector pos, PVector size, boolean clickable) {
        this.element = element;
        this.pos = pos;
        this.size = size;
        this.clickable = clickable;
    }

    public void display() {
        //Choose stroke color
        Main.processing.strokeWeight(2);
        if (selected()) Main.processing.stroke(255);
        else Main.processing.stroke(0);

        //Choose fill color
        if (Main.activeElement == element) Main.processing.fill(element.r, element.g, element.b);
        else Main.processing.fill(255);

        //Draw
        Main.processing.rect(pos.x, pos.y, size.x, size.y);
        Main.processing.fill(0);
        Main.processing.textSize(20);
        Main.processing.textAlign(CENTER);
        Main.processing.text(element.name, (pos.x + (size.x / 2)), (pos.y + 4 * (size.y / 7)));

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
            if (Main.activeElement == element) Main.activeElement = Element.NONE;
            else Main.activeElement = element;
        }
    }

}
