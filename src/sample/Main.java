package sample;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    int buttSize = 100;
    Scene scene1;
    static double vRadius = 40;
    static ArrayList<Vertex> list = new ArrayList<>();
    static ArrayList<Vertex> leftList = new ArrayList<>();
    static ArrayList<Vertex> rightList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World");
        StackPane layout1 = new StackPane();
        scene1 = new Scene(layout1, 800, 800);

        Button button = new Button("Add vertex");
        button.setTranslateX(-200);
        button.setTranslateY(-160);
        button.setMaxWidth(buttSize);

        TextField vertexValue = new TextField();
        vertexValue.setTranslateX(-200);
        vertexValue.setTranslateY(-200);
        vertexValue.setMaxWidth(buttSize);

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText("Vertex can contain only integers");

        Text text = new Text("12");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Integer.parseInt(vertexValue.getText());

                    Vertex vertex = createCircle(vertexValue.getText());
                    layout1.getChildren().addAll(vertex.getCircle(), vertex.getText());
                } catch (NumberFormatException e) {
                    errorAlert.showAndWait();
                    vertexValue.clear();
                }
            }
        });

        layout1.getChildren().addAll(button, vertexValue);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Vertex createCircle(String str) {
        if (list.isEmpty()) {
            Vertex v = new Vertex(Integer.parseInt(str),  0);
            v.setCoordX(50);
            list.add(v);
            leftList.add(v);
            rightList.add(v);
            return v;
        }

        int tempParsed = Integer.parseInt(str);

        for (Vertex v : list) {

                if(leftList.get(0).getValue() > tempParsed) {
                    if (leftList.get(leftList.size() - 1).getValue() > tempParsed) {
                        Vertex add = new Vertex(tempParsed, maxLeftTier(tempParsed) + 1);
                        add.setParent(leftList.get(leftList.size() - 1));

                        if (add.getParent().getColor().equals(Color.RED)) {
                            add.setColor(Color.BLACK);
                        }

                        add.setCoordX(add.getParent().getCoordX() + 50);
                        add.setTier(add.getParent().getTier() + 1);
                        add.setCoordY(add.getTier());

                        leftList.add(add);

                        return add;
                    }

                    if (leftList.get(leftList.size() - 1).getValue() < tempParsed) {
                        Vertex add = new Vertex(tempParsed, maxLeftTier(tempParsed) + 1);
                        add.setParent(leftList.get(leftList.size() - 1));

                        if (add.getParent().getColor().equals(Color.RED)) {
                            add.setColor(Color.BLACK);
                        }

                        add.setCoordX(add.getParent().getCoordX() - 50);
                        add.setTier(add.getParent().getTier() + 1);
                        add.setCoordY(add.getTier());

                        leftList.add(add);

                        return add;
                    }
                }

                /*
                if(leftList.get(leftList.size()-1).getValue() > tempParsed) {
                    Vertex add = new Vertex(tempParsed, maxLeftTier(tempParsed) + 1);
                    add.setParent(leftList.get(leftList.size()-1));

                    if(add.getParent().getColor().equals(Color.RED)){
                        add.setColor(Color.BLACK);
                    }

                    add.setCoordX(add.getParent().getCoordX() + 50);

                    rightList.add(add);

                    return add;
                }

                if(leftList.get(leftList.size()-1).getValue() < tempParsed) {
                    Vertex add = new Vertex(tempParsed, maxLeftTier(tempParsed) + 1);
                    add.setParent(leftList.get(leftList.size()-1));

                    if(add.getParent().getColor().equals(Color.RED)){
                        add.setColor(Color.BLACK);
                    }

                    add.setCoordX(add.getParent().getCoordX() - 50);
                    rightList.add(add);

                    return add;
                }
                */

        }
        return null;
    }

    public static int maxLeftTier(int value) {
        int tier = 0;
        for (Vertex v : leftList) {
            if (v.getTier() > tier && v.getValue() > value) {
                tier = v.getTier();
            }
        }
        return tier;
    }

    public static int maxRightTier(int value) {
        int tier = 0;
        for (Vertex v : rightList) {
            if (v.getTier() > tier && v.getValue() < value) {
                tier = v.getTier();
            }
        }
        return tier;
    }
}
