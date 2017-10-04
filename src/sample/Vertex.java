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
    Color color = Color.RED;
    double coordX;
    double coordY;
    static double vRadius = 40;

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

    public Vertex(int value, double coordX, double coordY) {
        this.value = value;
        this.coordX = coordX;
        this.coordY = coordY;
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

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }
}
