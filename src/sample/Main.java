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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
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

        Button button1 = new Button("Clear");
        button1.setTranslateX(-200);
        button1.setTranslateY(-120);
        button1.setMaxWidth(buttSize);

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
                    if(rebuild()){
                        for (Vertex v : leftList) {
                            if(v.getTier() == 0){
                                continue;
                            }
                            layout1.getChildren().removeAll(v.getCircle(), v.getText());
                        }
                        for (Vertex v : rightList) {
                            if(v.getTier() == 0){
                                continue;
                            }
                            layout1.getChildren().removeAll(v.getCircle(), v.getText());
                        }
                        if(leftList.size() > 1) {
                            for (Vertex v : leftList) {
                                if(v.getTier() == 0){
                                    continue;
                                }
                                Line line = new Line();

                                layout1.getChildren().addAll(v.getCircle(), v.getText());
                            }
                        }
                        if(rightList.size() > 1){
                            for (Vertex v : rightList) {
                                if(v.getTier() == 0){
                                    continue;
                                }
                                layout1.getChildren().addAll(v.getCircle(), v.getText());
                            }
                        }
                    }else {
                        if(vertex.getParent() != null) {
                            Line l = new Line();
                            l.setTranslateX(vertex.getParent().getCoordX());
                            l.setTranslateY(vertex.getParent().getCoordY() + vertex.getCircle().getRadius());
                            l.setStartX(0);

                            l.setEndX(50);

                            l.setStroke(Color.DARKRED);

                            layout1.getChildren().addAll(l);
                        }
                        layout1.getChildren().addAll(vertex.getCircle(), vertex.getText());
                    }

                    vertexValue.clear();
                } catch (NumberFormatException e) {
                    errorAlert.showAndWait();
                    vertexValue.clear();
                }
            }
        });

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Vertex v : leftList) {
                    layout1.getChildren().removeAll(v.getCircle(), v.getText());
                }
                for (Vertex v : rightList) {
                    layout1.getChildren().removeAll(v.getCircle(), v.getText());
                }
                list.clear();
                leftList.clear();
                rightList.clear();
            }
        });

        layout1.getChildren().addAll(button, vertexValue, button1);
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

                        if (add.getParent().getColor().equals(Color.DARKRED)) {
                            add.setColor(Color.BLACK);
                        }

                        add.setCoordX(add.getParent().getCoordX() - 50);
                        add.setTier(add.getParent().getTier() + 1);
                        add.setCoordY(add.getTier());

                        leftList.add(add);

                        return add;
                    }

                    if (leftList.get(leftList.size() - 1).getValue() < tempParsed) {
                        Vertex add = new Vertex(tempParsed, maxLeftTier(tempParsed) + 1);
                        add.setParent(leftList.get(leftList.size() - 1));

                        if (add.getParent().getColor().equals(Color.DARKRED)) {
                            add.setColor(Color.BLACK);
                        }

                        add.setCoordX(add.getParent().getCoordX() + 50);
                        add.setTier(add.getParent().getTier() + 1);
                        add.setCoordY(add.getTier());

                        leftList.add(add);

                        return add;
                    }
                }

            if(rightList.get(0).getValue() < tempParsed) {
                if (rightList.get(rightList.size() - 1).getValue() < tempParsed) {
                    Vertex add = new Vertex(tempParsed, maxRightTier(tempParsed) + 1);
                    add.setParent(rightList.get(rightList.size() - 1));

                    if (add.getParent().getColor().equals(Color.DARKRED)) {
                        add.setColor(Color.BLACK);
                    }

                    add.setCoordX(add.getParent().getCoordX() + 50);
                    add.setTier(add.getParent().getTier() + 1);
                    add.setCoordY(add.getTier());
                    rightList.add(add);

                    return add;
                }

                if (rightList.get(rightList.size() - 1).getValue() > tempParsed) {
                    Vertex add = new Vertex(tempParsed, maxRightTier(tempParsed) + 1);
                    add.setParent(rightList.get(rightList.size() - 1));

                    if (add.getParent().getColor().equals(Color.DARKRED)) {
                        add.setColor(Color.BLACK);
                    }

                    add.setCoordX(add.getParent().getCoordX() - 50);
                    add.setTier(add.getParent().getTier() + 1);
                    add.setCoordY(add.getTier());
                    rightList.add(add);


                    return add;
                }
            }


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

    public static boolean rebuild(){
        for (Vertex vL : leftList){
            if(vL.tier == 0){
                continue;
            }
            for (Vertex vR : rightList){
                if(vL.getCoordX()>= vR.getCoordX() && vL.getTier() == vR.getTier()){
                    transform();
                    return true;
                }
            }
        }
        return false;
    }

    public static void transform(){
        for (Vertex vL : leftList){
            if(vL.tier == 0){
                continue;
            }
            vL.setCoordX(vL.getCoordX() - 50);
        }

        for (Vertex vR : rightList){
            if(vR.tier == 0){
                continue;
            }
            vR.setCoordX(vR.getCoordX() + 50);
        }
    }
}
