package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by Max on 10/4/2017.
 */
public class Vertex {
    int value;
    Color color = Color.DARKRED;
    double coordX;
    double coordY;
    static double vRadius = 40;

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    Vertex parent = null;

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    int tier;

    public Text getText() {
        this.text.setText("" + value);
        this.text.setTranslateX(this.coordX);
        this.text.setTranslateY(this.coordY);
        this.text.setFill(Color.WHITE);
        this.text.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        return text;
    }

    Text text = new Text();

    public Circle getCircle() {
        circle.setTranslateX(this.getCoordX());
        circle.setTranslateY(this.getCoordY());
        circle.setFill(this.getColor());
        circle.setRadius(vRadius);
        return circle;
    }

    Circle circle = new Circle();

    public Vertex(int value, int tier) {
        this.value = value;
        this.tier = tier;
        this.coordY =(-100) + (100) * this.tier;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(int tier) {
        this.coordY = (-100) + (100) * tier;
    }
}
