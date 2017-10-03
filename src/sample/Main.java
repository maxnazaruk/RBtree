package sample;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    int buttSize = 100;
    Scene scene1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World");
        StackPane layout1 = new StackPane();
        scene1 = new Scene(layout1, 800, 800);

        Button button = new Button("Add vertex");
        button.setTranslateX(-200);
        button.setTranslateY(-200);
        button.setMaxWidth(buttSize);

        Circle circle = new Circle();
        circle.setTranslateX(100);
        circle.setTranslateY(100);
        circle.setRadius(50);
        circle.setFill(Color.web("#990000"));

        Circle circle1 = new Circle();
        circle1.setCenterX(-300);
        circle1.setCenterY(10);
        circle1.setRadius(10);
        circle1.setFill(Color.BLACK);

        Text text = new Text("12");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Circle temp = createCircle();
                text.setTranslateX(temp.getTranslateX());
                text.setTranslateY(temp.getTranslateY());
                text.setFill(Color.WHITE);
                text.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                Line line = new Line();

                line.setStartX(temp.getTranslateX());
                //line.setTranslateX(temp.getTranslateX());
                //line.setStartY(temp.getTranslateY());
                //line.setTranslateY(temp.getTranslateY() + temp.getRadius());

                line.setEndX(200);
                //line.setEndY(200);

                layout1.getChildren().addAll(createCircle(), text, line);
            }
        });

        layout1.getChildren().addAll(button);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Circle createCircle() {
        Circle circle = new Circle();
        circle.setTranslateX(50);
        circle.setTranslateY(-100);
        circle.setRadius(40);
        circle.setFill(Color.web("#990000"));
        return circle;
    }
}
