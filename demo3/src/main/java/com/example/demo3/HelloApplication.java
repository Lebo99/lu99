package com.example.demo3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    String css = getClass().getResource("style.css").toExternalForm().toString();
    int currentImageIndex = 0;
    @Override
    public void start(Stage stage) throws Exception {
        // HEADING
        Text text = new Text("WELCOME---TO---MY---GALLERY");
        text.setFont(Font.font("ARIAL", FontWeight.BOLD, FontPosture.REGULAR, 30));
        text.setFill(Color.GRAY);
        text.setStrokeWidth(2);
        text.setStroke(Color.BLACK);
        text.setUnderline(true);

        // HEADING CONTAINER
        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(10));
        stackPane.getChildren().addAll(text);

        // IMAGE CONTAINER
        GridPane pane = new GridPane();
        pane.setVgap(5);
        pane.setHgap(10);
        pane.setPadding(new Insets(40, 50, 40, 50));


        // IMAGES
        String[] imagePaths = new String[]{
                "11.jpg",
                "8.jpg",
                "1.jpg",
                "11.jpg",
                "8.jpg",
                "1.jpg",
                "11.jpg",
                "8.jpg",
                "1.jpg"
        };


        for (int i = 0; i < 9; i++) {
            Circle circle = new Circle(50, 50, 50);
            circle.setStroke(Color.PLUM);
            circle.setId("vv");

            Image image = new Image(getClass().getResourceAsStream(imagePaths[i]));
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setPreserveRatio(true);

            circle.setFill(new ImagePattern(image));
            circle.setEffect(new DropShadow(+30d, 10d, +2d, Color.BLACK));

            int finalI = i;
            circle.setOnMouseClicked(event -> displayFullImage(imagePaths[finalI]));

            pane.add(circle, i % 3, i / 3);
        }

        // BUTTON
        Button button = new Button("Next");
        button.setId("b");
        button.setWrapText(true);
        button.setPadding(new Insets(10, 30, 10, 10));
        button.setOnAction(event -> {
            // Increment the current image index
            currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
            // Display the full image
            displayFullImage(imagePaths[currentImageIndex]);
        });


        // LAYOUT
        VBox vbox = new VBox(stackPane, pane, button);
        vbox.setId("vb");
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        // SCENE
        //var cssFile=getClass().getResource("style.css");
        Scene scene = new Scene(vbox, 500, 650);
        scene.getStylesheets().addAll(css);

        // STAGE
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    //DISPLAYING FULL IMAGE
    private void displayFullImage(String imagePath) {
        Stage stage = new Stage();
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        StackPane pane = new StackPane(imageView);

        // Add exit button
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> stage.close());
        exitButton.setTranslateY(-250);
        pane.getChildren().add(exitButton);

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(css);
        stage.setScene(scene);
        stage.setTitle("Full Image Display");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
